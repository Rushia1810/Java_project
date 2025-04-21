import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Headset extends Product{
    private int driver;
    private String surroundSound;
    private static int headsetCount;
    public Headset(){
        
    }
    public Headset(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,int driver,String surroundSound){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.driver = driver;
        this.surroundSound = surroundSound;
        headsetCount++;
    }
    public int getDriver(){
        return driver;
    }
    public String getSurroundSound(){
        return surroundSound;
    }
    public static int getHeadsetCount(){
        return headsetCount;
    }
    public void setDriver(int driver){
        this.driver = driver;
    }
    public void setSurroundSound(String surroundSound){
        this.surroundSound = surroundSound;
    }
    public static void setHeadsetCount(int headsetCount){
        Headset.headsetCount = headsetCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + driver + "," + surroundSound;
    }
    public static void addHeadset(){
        Headset headset = new Headset();

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
                do {
                    System.out.print("Enter HeadsetID (HSxxxx)('0' to exit): ");
                    headset.setproductID(scanner.nextLine());
                    if (headset.getproductID().equalsIgnoreCase("0")) {
                        System.out.println("Exiting adding process.");
                        writer.close(); // Close the writer before exiting
                        return; // Exit the method
                    }
                    if (!headset.getproductID().matches("^HS.{4}$")){
                        System.out.println("Only 4 characters after HS are allowed.");
                    }
                } while (!headset.getproductID().matches("^HS.{4}$"));
                
                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] productinfo = line.split(",");
                    if (productinfo.length >= 1 && productinfo[0].equals(headset.getproductID())) {
                        System.out.println("HeadsetID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close();
            
            System.out.print("Enter Headset Name: ");
            headset.setproductName(scanner.nextLine());

            do {
                System.out.print("Enter Headset Quantity: ");
                headset.setproductQuantity(scanner.nextInt());
                if (headset.getproductQuantity() <1) {
                    System.out.println("Quantity must be >= 1");
                }
            } while (headset.getproductQuantity() <1);
            scanner.nextLine();
            
        	
            System.out.print("Enter Headset Brand: ");
            headset.setproductBrand(scanner.nextLine());

            do {
                System.out.print("Enter Headset Price: ");
                headset.setproductPrice(scanner.nextDouble());
                if (headset.getproductPrice() <= 0) {
                    System.out.println("Price must be > 0");
                }
            } while (headset.getproductPrice() <= 0);
            scanner.nextLine();

            System.out.print("Enter Headset Description: ");
            headset.setproductDesc(scanner.nextLine());

            do {
                System.out.print("Enter Headset Warranty: ");
                headset.setproductWarranty(scanner.nextInt());
                if (headset.getproductWarranty() < 0) {
                    System.out.println("Warranty must be >= 0");
                }
            } while (headset.getproductWarranty() < 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Headset Driver: ");
                headset.setDriver(scanner.nextInt());
                if (headset.getDriver() <=  0){
                    System.out.println("Driver must be >= 0");
                }
            } while (headset.getDriver() < 0);
            scanner.nextLine();

            System.out.print("Enter Headset Surroundsound: ");
            headset.setSurroundSound(scanner.nextLine());
			
		    //comfirmation
			System.out.print("\n\n================================================");
			System.out.print("\nComfirm Adding? (Y/N): ");
            String comfirmAdding = scanner.nextLine();
            
            if (comfirmAdding.equalsIgnoreCase("y")){
                writer.write(headset.toString());
                Product.setproductCount(productCount + 1);
                System.out.println("\n*Headset added successfully.");
                writer.close();
                App.adminmenu();
            } else if (comfirmAdding.equalsIgnoreCase("n")){
                System.out.println("\n* Fail to add Headset.");
                App.adminmenu();
            }
 		
        } catch (IOException e) {
            e.printStackTrace();
        }
	      	
    }
    public String display(){        
	    return super.display() + "\n Driver\t\t\t: " + driver + "MM" + 
		                         "\n Surroundsound\t\t: " + surroundSound;                  
	}
    public static void removeHeadset(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productinfo = line.split(",");
                if (productinfo[0].equals(productID)) {
                    found = true;
                    System.out.println("Headset with ID " + productID + " has been removed.");
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
            if (!found) {
                System.out.println("Headset with ID " + productID + " not found.");
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
    public static void modifyHeadset(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productinfo = line.split(",");
                if (productinfo[0].equals(productID)) {
                    found = true;
                    System.out.println("Modifying Headset with ID " + productID + ":");
                    // Add code to modify the headset details here
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
            
            if (!found) {
                System.out.println("Headset with ID " + productID + " not found.");
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
