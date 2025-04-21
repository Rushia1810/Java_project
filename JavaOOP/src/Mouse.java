import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mouse extends Product{
    private int weight;
    private String isWired;
    private static int mouseCount;
    public Mouse(){
        
    }
    public Mouse(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,int weight,String isWired){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.weight = weight;
        this.isWired = isWired;
        mouseCount++;
    }
    public int getWeight(){
        return weight;
    }
    public String getIsWired(){
        return isWired;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setIsWired(String isWired){
        this.isWired = isWired;
    }
    public static int getMouseCount(){
        return mouseCount;
    }
    public static void setMouseCount(int mouseCount){
        Mouse.mouseCount = mouseCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + weight + "," + isWired;
    }
    public static void addMouse(){
        Mouse mouse = new Mouse();
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList", true));
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            boolean isDuplicate;
            int productCount = Product.getproductCount();
            System.out.println("\nAdding Product " + (productCount+1) + ":");
            
            
            do {
                isDuplicate = false;
                do {
                    System.out.print("Enter MouseID (MSxxxx)('0' to exit): ");
                    mouse.setproductID(scanner.nextLine());
                    if (mouse.getproductID().equalsIgnoreCase("0")) {
                        System.out.println("Exiting adding process.");
                        writer.close(); // Close the writer before exiting
                        return; // Exit the method
                    }
                    if (!mouse.getproductID().matches("^MS.{4}$")){
                        System.out.println("Only 4 characters after MS are allowed.");
                    }
                } while (!mouse.getproductID().matches("^MS.{4}$"));
                
                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] productinfo = line.split(",");
                    if (productinfo.length >= 1 && productinfo[0].equals(mouse.getproductID())) {
                        System.out.println("MouseID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close();
            
            System.out.print("Enter Mouse Name: ");
            mouse.setproductName(scanner.nextLine());

            do {
                System.out.print("Enter Mouse Quantity: ");
                mouse.setproductQuantity(scanner.nextInt());
                if (mouse.getproductQuantity() <1) {
                    System.out.println("Quantity must be >= 1");
                }
            } while (mouse.getproductQuantity() <1);
            scanner.nextLine();
            
        	
            System.out.print("Enter Mouse Brand: ");
            mouse.setproductBrand(scanner.nextLine());

            do {
                System.out.print("Enter Mouse Price: ");
                mouse.setproductPrice(scanner.nextDouble());
                if (mouse.getproductPrice() <= 0) {
                    System.out.println("Price must be > 0");
                }
            } while (mouse.getproductPrice() <= 0);
            scanner.nextLine();

            System.out.print("Enter Mouse Description: ");
            mouse.setproductDesc(scanner.nextLine());

            do {
                System.out.print("Enter Mouse Warranty: ");
                mouse.setproductWarranty(scanner.nextInt());
                if (mouse.getproductWarranty() < 0) {
                    System.out.println("Warranty must be >= 0");
                }
            } while (mouse.getproductWarranty() < 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Mouse Weight: ");
                mouse.setWeight(scanner.nextInt());
                if (mouse.getWeight() <= 0) {
                    System.out.println("Weight must be > 0");
                    
                }
            } while (mouse.getWeight() <= 0);

            
            do {
                System.out.print("Is Mouse Wired?(Y/N) ");
                mouse.setIsWired(scanner.nextLine());
                if (mouse.getIsWired().equalsIgnoreCase("y")){
                    mouse.setIsWired("Wired");
                } else if (mouse.getIsWired().equalsIgnoreCase("n")){
                    mouse.setIsWired("Wireless");
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
            } while (!mouse.getIsWired().equalsIgnoreCase("y") || !mouse.getIsWired().equalsIgnoreCase("n"));
                
	
		//comfirmation
        System.out.print("\n\n================================================");
        System.out.print("\nComfirm Adding? (Y/N): ");
        String comfirmAdding = scanner.nextLine();
        
        if (comfirmAdding.equalsIgnoreCase("y")){
            writer.write(mouse.toString());
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
	    return super.display() + "\n Weight\t\t\t: " + weight + 
		                         "\n isWired\t\t\t: " + isWired;
	}
    public static void removeMouse(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productinfo = line.split(",");
                if (productinfo[0].equals(productID)) {
                    found = true;
                    System.out.println("Mouse with ID " + productID + " has been removed.");
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
            if (!found) {
                System.out.println("Mouse with ID " + productID + " not found.");
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
