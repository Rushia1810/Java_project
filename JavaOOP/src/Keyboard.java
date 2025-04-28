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
            
            do {
                System.out.print("Enter Keyboard Name: ");
                keyboard.setproductName(scanner.nextLine());
                if (keyboard.getproductName().isEmpty()) {
                    System.out.println("Keyboard Name cannot be empty! Try again.");
                }
            } while (keyboard.getproductName().isEmpty());

            do {
                try{
                    System.out.print("Enter Keyboard Quantity: ");
                    keyboard.setproductQuantity(scanner.nextInt());
                    if (keyboard.getproductQuantity() <1) {
                        System.out.println("Quantity must be >= 1");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (keyboard.getproductQuantity() <1);
            scanner.nextLine();
            
        	do {
                System.out.print("Enter Keyboard Brand: ");
                keyboard.setproductBrand(scanner.nextLine());
                if (keyboard.getproductBrand().isEmpty()) {
                    System.out.println("Keyboard Brand cannot be empty! Try again.");
                }
            } while (keyboard.getproductBrand().isEmpty());

            do {
                try{
                    System.out.print("Enter Keyboard Price: ");
                    keyboard.setproductPrice(scanner.nextDouble());
                    if (keyboard.getproductPrice() <= 0) {
                        System.out.println("Price must be > 0");
                    }
                } catch (Exception e) {              
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (keyboard.getproductPrice() <= 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Keyboard Description: ");
                keyboard.setproductDesc(scanner.nextLine());
                if (keyboard.getproductDesc().isEmpty()) {
                    System.out.println("Keyboard Description cannot be empty! Try again.");
                }
            } while (keyboard.getproductDesc().isEmpty());

            do {
                try {
                    System.out.print("Enter Keyboard Warranty: ");
                    keyboard.setproductWarranty(scanner.nextInt());
                    if (keyboard.getproductWarranty() < 0) {
                        System.out.println("Warranty must be >= 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (keyboard.getproductWarranty() < 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Keyboard Layout: ");
                keyboard.setLayout(scanner.nextLine());
                if (keyboard.getLayout().isEmpty()) {
                    System.out.println("Keyboard Layout cannot be empty! Try again.");
                }
            } while (keyboard.getLayout().isEmpty());

            do {
                System.out.print("Enter Keyboard Type: ");
                keyboard.setType(scanner.nextLine());
                if (keyboard.getType().isEmpty()) {
                    System.out.println("Keyboard Type cannot be empty! Try again.");
                }
            } while (keyboard.getType().isEmpty());
			
            //comfirmation
            System.out.print("\n\n================================================");
            String comfirmAdding;
            do {
                System.out.print("\nComfirm Adding? (Y/N): ");
                comfirmAdding = scanner.nextLine();
                
                if (comfirmAdding.equalsIgnoreCase("y")){
                    writer.write(keyboard.toString());
                    Product.setproductCount(productCount + 1);
                    System.out.println("\n*Keyboard added successfully.");
                    writer.close();
                    App.adminmenu();
                } else if (comfirmAdding.equalsIgnoreCase("n")){
                    System.out.println("\n* Fail to add Keyboard.");
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
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Keyboard");
                	System.out.println(" ProductID\t: " + productinfo[0]);
                	System.out.println(" Name\t\t: " + productinfo[1]);
                	System.out.println(" Quantity \t: " + productinfo[2]);
                	System.out.println(" Brand\t\t: " + productinfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productinfo[4]);
              		System.out.println(" Description\t: " + productinfo[5]);
                    System.out.println(" Warranty\t: " + productinfo[6] + " months");
                    System.out.println(" Layout\t\t: " + productinfo[7]);
                    System.out.println(" Type\t\t: " + productinfo[8]);
                    System.out.print("\n\nDo you want to modify this product? (Y/N): ");
                    String choice;
                    do{
                        System.out.println("\nDo you want to delete this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("Keyboard with ID " + productID + " has been removed.");
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
    public static void modifyKeyboard(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productinfo = line.split(",");
                if (productinfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Keyboard");
                	System.out.println(" ProductID\t: " + productinfo[0]);
                	System.out.println(" Name\t\t: " + productinfo[1]);
                	System.out.println(" Quantity \t: " + productinfo[2]);
                	System.out.println(" Brand\t\t: " + productinfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productinfo[4]);
              		System.out.println(" Description\t: " + productinfo[5]);
                    System.out.println(" Warranty\t: " + productinfo[6] + " months");
                    System.out.println(" Layout\t\t: " + productinfo[7]);
                    System.out.println(" Type\t\t: " + productinfo[8]);
                    String choice;
                    do{
                        System.out.print("\n\nDo you want to modify this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            String oldproductName = productinfo[1];
                            System.out.print("Enter new Keyboard Name: ");
                            productinfo[1] = scanner.nextLine();
                            if (productinfo[1].isEmpty()) {
                                productinfo[1] = oldproductName; // Keep the old name if new name is empty     
                                
                            }
                            
                            String oldproductQuantity = productinfo[2];
                            do {
                                System.out.print("Enter new Keyboard Quantity: ");
                                productinfo[2] = scanner.nextLine();
                                try {
                                    if (Integer.parseInt(productinfo[2]) < 1) {
                                        System.out.println("Quantity must be >= 1");
                                    }
                                } catch (NumberFormatException e) {
                                    productinfo[2] = oldproductQuantity;
                                }
                            } while (Integer.parseInt(productinfo[2]) < 1);
                            
                            String oldproductBrand = productinfo[3];
                            System.out.print("Enter new Keyboard Brand: ");
                            productinfo[3] = scanner.nextLine();
                            if (productinfo[3].isEmpty()) {
                                productinfo[3] = oldproductBrand; // Keep the old brand if new brand is empty         
                            }
                            
                            String oldproductPrice = productinfo[4];
                            do {
                                try{
                                    System.out.print("Enter new Keyboard Price: ");
                                    productinfo[4] = scanner.nextLine();
                                    if (Double.parseDouble(productinfo[4]) <= 0) {
                                        System.out.println("Price must be > 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productinfo[4] = oldproductPrice;
                                }
                            } while (Double.parseDouble(productinfo[4]) <= 0);
                            
                            String oldproductDesc = productinfo[5];
                            System.out.print("Enter new Keyboard Description: ");
                            productinfo[5] = scanner.nextLine();
                            if (productinfo[5].isEmpty()) {
                                productinfo[5] = oldproductDesc; // Keep the old description if new description is empty     
                            }
                            
                            String oldproductWarranty = productinfo[6];
                            do {
                                try {
                                    System.out.print("Enter new Keyboard Warranty: ");
                                    productinfo[6] = scanner.nextLine();
                                    if (Integer.parseInt(productinfo[6]) < 0) {
                                        System.out.println("Warranty must be >= 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productinfo[6] = oldproductWarranty;
                                }
                            } while (Integer.parseInt(productinfo[6]) < 0);
                            
                            String oldLayout = productinfo[7];
                            System.out.print("Enter new Keyboard Layout: ");
                            productinfo[7] = scanner.nextLine();
                            if (productinfo[7].isEmpty()) {
                                productinfo[7] = oldLayout; // Keep the old layout if new layout is empty     
                            }
                            
                            String oldType = productinfo[8];
                            System.out.print("Enter new Keyboard Type: ");
                            productinfo[8] = scanner.nextLine();
                            if (productinfo[8].isEmpty()) {
                                productinfo[8] = oldType;
                                
                            }
                            String confirmModify;
                                do{
                                    System.out.println("Confirm Modifying? (Y/N)");
                                    confirmModify = scanner.nextLine();
                                    if (confirmModify.equalsIgnoreCase("y")) {
                                        writer.write(String.join(",", productinfo));
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
