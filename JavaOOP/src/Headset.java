import java.io.*;
import java.util.*;;

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
        return super.toString() + "," + driver + "," + surroundSound + "\n";
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
                    String[] productInfo = line.split(",");
                    if (productInfo.length >= 1 && productInfo[0].equals(headset.getproductID())) {
                        System.out.println("HeadsetID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close();
            
            do {
                System.out.print("Enter Headset Name: ");
                headset.setproductName(scanner.nextLine());
                if (headset.getproductName().isEmpty()) {
                    System.out.println("Headset Name cannot be empty.");
                }
            } while (headset.getproductName().isEmpty());

            do {
                try{
                    System.out.print("Enter Headset Quantity: ");
                    headset.setproductQuantity(scanner.nextInt());
                    if (headset.getproductQuantity() <1) {
                        System.out.println("Quantity must be >= 1");
                    }
                }catch (Exception e){
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (headset.getproductQuantity() <1);
            scanner.nextLine();
            
        	do{
                System.out.print("Enter Headset Brand: ");
                headset.setproductBrand(scanner.nextLine());
                if (headset.getproductBrand().isEmpty()) {
                    System.out.println("Headset Brand cannot be empty.");
                }
            } while (headset.getproductBrand().isEmpty());

            do {
                try{
                    System.out.print("Enter Headset Price: ");
                    headset.setproductPrice(scanner.nextDouble());
                    if (headset.getproductPrice() <= 0) {
                        System.out.println("Price must be > 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (headset.getproductPrice() <= 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Headset Description: ");
                headset.setproductDesc(scanner.nextLine());
                if (headset.getproductDesc().isEmpty()) {
                    System.out.println("Headset Description cannot be empty.");
                }
            } while (headset.getproductDesc().isEmpty());

            do {
                try{
                    System.out.print("Enter Headset Warranty: ");
                    headset.setproductWarranty(scanner.nextInt());
                    if (headset.getproductWarranty() < 0) {
                        System.out.println("Warranty must be >= 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (headset.getproductWarranty() < 0);
            scanner.nextLine();

            do {
                try{
                    System.out.print("Enter Headset Driver: ");
                    headset.setDriver(scanner.nextInt());
                    if (headset.getDriver() <= 0){
                        System.out.println("Driver must be > 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (headset.getDriver() <= 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Headset Surroundsound: ");
                headset.setSurroundSound(scanner.nextLine());
                if (headset.getSurroundSound().isEmpty()) {
                    System.out.println("Surroundsound cannot be empty.");
                }
            } while (headset.getSurroundSound().isEmpty());
			
            String comfirmAdding;
            do{
                System.out.print("\nComfirm Adding? (Y/N): ");
                comfirmAdding = scanner.nextLine();
                
                if (comfirmAdding.equalsIgnoreCase("y")){
                    writer.write(headset.toString());
                    Product.setproductCount(productCount + 1);
                    System.out.println("\n*Headset added successfully.");
                    writer.close();
                    App.adminmenu();
                } else if (comfirmAdding.equalsIgnoreCase("n")){
                    System.out.println("\n* Fail to add Headset.");
                    App.adminmenu();
                } else {
                    System.out.println("Invalid input");
                }
            } while (!comfirmAdding.equalsIgnoreCase("y") && !comfirmAdding.equalsIgnoreCase("n"));
 		
        } catch (IOException e) {
            e.printStackTrace();
        }
	      	
    }
    public String display(){        
	    return super.display() + "\n Driver\t\t\t: " + driver + "mm" + 
		                         "\n Surroundsound\t\t: " + surroundSound;                  
	}
    public static void removeHeadset(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                if (productInfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Headset");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Driver\t\t: " + productInfo[7] + "mm");
                    System.out.println(" SurroundSound\t: " + productInfo[8]);
                    String choice;
                    do{
                        System.out.println("\nDo you want to delete this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("Headset with ID " + productID + " has been removed.");
                        } else if (choice.equalsIgnoreCase("n")) {
                            writer.write(line);
                            writer.newLine();
                        } else {
                            System.out.println("Invalid input");
                        }
                    } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
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
                String[] productInfo = line.split(",");
                if (productInfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Headset");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Driver\t\t: " + productInfo[7] + "mm");
                    System.out.println(" SurroundSound\t: " + productInfo[8]);
                    String choice;
                    do{
                        System.out.print("\n\nDo you want to modify this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            String oldproductName = productInfo[1];
                            System.out.print("Enter new Headset Name: ");
                            productInfo[1] = scanner.nextLine();
                            if (productInfo[1].isEmpty()) {
                                productInfo[1] = oldproductName; // Keep the old name if new name is empty     
                                
                            }
                            
                            String oldproductQuantity = productInfo[2];
                            do {
                                System.out.print("Enter new Headset Quantity: ");
                                productInfo[2] = scanner.nextLine();
                                try {
                                    if (Integer.parseInt(productInfo[2]) < 1) {
                                        System.out.println("Quantity must be >= 1");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[2] = oldproductQuantity;
                                }
                            } while (Integer.parseInt(productInfo[2]) < 1);

                            
                            
                            String oldproductBrand = productInfo[3];
                            System.out.print("Enter new Headset Brand: ");
                            productInfo[3] = scanner.nextLine();
                            if (productInfo[3].isEmpty()) {
                                productInfo[3] = oldproductBrand; // Keep the old brand if new brand is empty         
                            }
                            
                            String oldproductPrice = productInfo[4];
                            do {
                                try{
                                    System.out.print("Enter new Headset Price: ");
                                    productInfo[4] = scanner.nextLine();
                                    if (Double.parseDouble(productInfo[4]) <= 0) {
                                        System.out.println("Price must be > 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[4] = oldproductPrice;
                                }
                            } while (Double.parseDouble(productInfo[4]) <= 0);
                            
                            String oldproductDesc = productInfo[5];
                            System.out.print("Enter new Headset Description: ");
                            productInfo[5] = scanner.nextLine();
                            if (productInfo[5].isEmpty()) {
                                productInfo[5] = oldproductDesc; // Keep the old description if new description is empty     
                            }
                            
                            String oldproductWarranty = productInfo[6];
                            do {
                                try {
                                    System.out.print("Enter new Headset Warranty: ");
                                    productInfo[6] = scanner.nextLine();
                                    if (Integer.parseInt(productInfo[6]) < 0) {
                                        System.out.println("Warranty must be >= 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[6] = oldproductWarranty;
                                }
                            } while (Integer.parseInt(productInfo[6]) < 0);
                            
                            String oldDriver = productInfo[7];
                            do {
                                System.out.print("Enter new Headset Driver: ");
                                productInfo[7] = scanner.nextLine();
                                try {
                                    if (Integer.parseInt((productInfo[7])) <= 0) {
                                        System.out.println("Driver must be > 0"); // Keep the old layout if new layout is empty     
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[7] = oldDriver;
                                }
                            } while (Integer.parseInt(productInfo[7]) <= 0);
                            
                            String oldSurroundSound = productInfo[8];
                            System.out.print("Enter new Headset SurroundSound: ");
                            productInfo[8] = scanner.nextLine();
                            if (productInfo[8].isEmpty()) {
                                productInfo[8] = oldSurroundSound;     
                            }
                            String confirmModify;
                                do{
                                    System.out.println("Confirm Modifying? (Y/N)");
                                    confirmModify = scanner.nextLine();
                                    if (confirmModify.equalsIgnoreCase("y")) {
                                        writer.write(String.join(",", productInfo));
                                        writer.write("\n");
                                    }else if (confirmModify.equalsIgnoreCase("n")){
                                        System.out.println("Failed to modify Mouse");
                                    } else{
                                        System.out.println("Invalid input");
                                    }
                                } while (!confirmModify.equalsIgnoreCase("y") && !confirmModify.equalsIgnoreCase("n"));

                            } else if (choice.equalsIgnoreCase("n")){
                                writer.write(line);// Write the original line if not modifying
                                writer.newLine();
                                break;
                            }
                    } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
                    
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
