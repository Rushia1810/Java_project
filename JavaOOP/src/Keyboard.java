import java.io.*;
import java.util.*;

public class Keyboard extends Product{
    private String layout;
    private String type;
    private static int keyboardCount;
    public Keyboard(){
        
    }
    public Keyboard(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,String layout,String type){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.layout = layout;
        this.type = type;
        keyboardCount++;
    }
    public String getLayout(){
        return layout;
    }
    public String getType(){
        return type;
    }
    public void setLayout(String layout){
        this.layout = layout;
    }
    public void setType(String type){
        this.type = type;
    }
    public static int getKeyboardCount(){
        return keyboardCount;
    }
    public static void setKeyboardCount(int keyboardCount){
        Keyboard.keyboardCount = keyboardCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + layout + "," + type + "\n";
    }
    public static void addKeyboard(){
        Keyboard keyboard = new Keyboard();

        try{
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList", true));
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            boolean isDuplicate;
            int productCount = Product.getproductCount();
            System.out.println("\nAdding Product " + (productCount+1) + ":");
            
            
            do {
                isDuplicate = false;
                do{
                    System.out.print("Enter KeyboardID (KBxxxx)('0' to exit): ");
                    keyboard.setproductID(scanner.nextLine());
                    if (keyboard.getproductID().equalsIgnoreCase("0")) {
                        System.out.println("Exiting adding process.");
                        writer.close(); // Close the writer before exiting
                        return; // Exit the method
                    }
                    if (!keyboard.getproductID().matches("^KB.{4}$")){
                        System.out.println("Only 4 characters after KB are allowed.");
                    }
                } while (!keyboard.getproductID().matches("^KB.{4}$"));
                
                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] productinfo = line.split(",");
                    if (productinfo.length >= 1 && productinfo[0].equals(keyboard.getproductID())) {
                        System.out.println("KeyboardID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close();
            
            System.out.print("Enter Keyboard Name: ");
            keyboard.setproductName(scanner.nextLine());

            do {
                System.out.print("Enter Keyboard Quantity: ");
                keyboard.setproductQuantity(scanner.nextInt());
                if (keyboard.getproductQuantity() <1) {
                    System.out.println("Quantity must be >= 1");
                }
            } while (keyboard.getproductQuantity() <1);
            scanner.nextLine();
            
        	
            System.out.print("Enter Keyboard Brand: ");
            keyboard.setproductBrand(scanner.nextLine());

            do {
                System.out.print("Enter Keyboard Price: ");
                keyboard.setproductPrice(scanner.nextDouble());
                if (keyboard.getproductPrice() <= 0) {
                    System.out.println("Price must be > 0");
                }
            } while (keyboard.getproductPrice() <= 0);
            scanner.nextLine();

            System.out.print("Enter Keyboard Description: ");
            keyboard.setproductDesc(scanner.nextLine());

            do {
                System.out.print("Enter Keyboard Warranty: ");
                keyboard.setproductWarranty(scanner.nextInt());
                if (keyboard.getproductWarranty() < 0) {
                    System.out.println("Warranty must be >= 0");
                }
            } while (keyboard.getproductWarranty() < 0);
            scanner.nextLine();

            System.out.print("Enter Keyboard Layout: ");
            keyboard.setLayout(scanner.nextLine());

            System.out.print("Enter Keyboard Type: ");
            keyboard.setType(scanner.nextLine());
			
            //comfirmation
            System.out.print("\n\n================================================");
            System.out.print("\nComfirm Adding? (Y/N): ");
            String comfirmAdding = scanner.nextLine();
            
            if (comfirmAdding.equalsIgnoreCase("y")){
                writer.write(keyboard.toString());
                Product.setproductCount(productCount + 1);
                System.out.println("\n*Keyboard added successfully.");
                writer.close();
                App.adminmenu();
            } else if (comfirmAdding.equalsIgnoreCase("n")){
                System.out.println("\n* Fail to add Keyboard.");
                App.adminmenu();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	      	
    }
    public String display(){        
	    return super.display() + "\n Layout\t\t\t: " + layout + 
		                         "\n Type\t\t\t: " + type;                  
	}
    public static void removeKeyboard(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productinfo = line.split(",");
                if (productinfo[0].equals(productID)) {
                    found = true;
                    System.out.println("Keyboard with ID " + productID + " has been removed.");
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
            if (!found) {
                System.out.println("Keyboard with ID " + productID + " not found.");
            }
            
            reader.close();
            writer.close();
            
            // Rename the temporary file to the original file
            File originalFile = new File("ProductList");
            File tempFile = new File("ProductList_temp");
            originalFile.delete();
            tempFile.renameTo(originalFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
