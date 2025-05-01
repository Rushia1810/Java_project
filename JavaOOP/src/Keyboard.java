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
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList.txt", true));
            BufferedReader reader = new BufferedReader(new FileReader("ProductList.txt"));
            String line;
            boolean isDuplicate;
            int productCount = Product.getproductCount();
                       
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
                    String[] productInfo = line.split(",");
                    if (productInfo.length >= 1 && productInfo[0].equals(keyboard.getproductID())) {
                        System.out.println("KeyboardID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList.txt"));
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
	    return " ProductID\t\t: " + getproductID() + 
        "\n Name\t\t\t: " + getproductName() + 
        "\n Quantity\t\t: " + getproductQuantity() + 
        "\n Brand\t\t\t: " + getproductBrand() +
        "\n Price\t\t\t: RM" + getproductPrice() +
        "\n Description\t\t: " + getproductDesc() +
        "\n Warranty\t\t: " + getproductWarranty() + " months"+
        "\n Layout\t\t\t: " + layout + 
        "\n Type\t\t\t: " + type;                  
	}
    public static void removeKeyboard(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                if (productInfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Keyboard");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Layout\t\t: " + productInfo[7]);
                    System.out.println(" Type\t\t: " + productInfo[8]);
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
            File originalFile = new File("ProductList.txt");
            File tempFile = new File("ProductList_temp");
            originalFile.delete();
            tempFile.renameTo(originalFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void modifyKeyboard(String productID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("ProductList_temp"));
            String line;
            boolean found = false;
            
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                if (productInfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Keyboard");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Layout\t\t: " + productInfo[7]);
                    System.out.println(" Type\t\t: " + productInfo[8]);
                    String choice;
                    Scanner scanner = new Scanner(System.in);
                    do{
                        System.out.print("\n\nDo you want to modify this product? (Y/N): ");
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("Directly pressing ENTER keeps the old attribute.");
                            String oldproductName = productInfo[1];
                            System.out.print("Enter new Keyboard Name: ");
                            productInfo[1] = scanner.nextLine();
                            if (productInfo[1].isEmpty()) {
                                productInfo[1] = oldproductName; // Keep the old name if new name is empty     
                                
                            }
                            
                            String oldproductQuantity = productInfo[2];
                            do {
                                System.out.print("Enter new Keyboard Quantity: ");
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
                            System.out.print("Enter new Keyboard Brand: ");
                            productInfo[3] = scanner.nextLine();
                            if (productInfo[3].isEmpty()) {
                                productInfo[3] = oldproductBrand; // Keep the old brand if new brand is empty         
                            }
                            
                            String oldproductPrice = productInfo[4];
                            do {
                                try{
                                    System.out.print("Enter new Keyboard Price: ");
                                    productInfo[4] = scanner.nextLine();
                                    if (Double.parseDouble(productInfo[4]) <= 0) {
                                        System.out.println("Price must be > 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[4] = oldproductPrice;
                                }
                            } while (Double.parseDouble(productInfo[4]) <= 0);
                            
                            String oldproductDesc = productInfo[5];
                            System.out.print("Enter new Keyboard Description: ");
                            productInfo[5] = scanner.nextLine();
                            if (productInfo[5].isEmpty()) {
                                productInfo[5] = oldproductDesc; // Keep the old description if new description is empty     
                            }
                            
                            String oldproductWarranty = productInfo[6];
                            do {
                                try {
                                    System.out.print("Enter new Keyboard Warranty: ");
                                    productInfo[6] = scanner.nextLine();
                                    if (Integer.parseInt(productInfo[6]) < 0) {
                                        System.out.println("Warranty must be >= 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[6] = oldproductWarranty;
                                }
                            } while (Integer.parseInt(productInfo[6]) < 0);
                            
                            String oldLayout = productInfo[7];
                            System.out.print("Enter new Keyboard Layout: ");
                            productInfo[7] = scanner.nextLine();
                            if (productInfo[7].isEmpty()) {
                                productInfo[7] = oldLayout; // Keep the old layout if new layout is empty     
                            }
                            
                            String oldType = productInfo[8];
                            System.out.print("Enter new Keyboard Type: ");
                            productInfo[8] = scanner.nextLine();
                            if (productInfo[8].isEmpty()) {
                                productInfo[8] = oldType;
                                
                            }
                            String confirmModify;
                                do{
                                    System.out.print("\nConfirm Modifying? (Y/N):");
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
                System.out.println("Keyboard with ID " + productID + " not found.");
            }
            
            reader.close();
            writer.close();
            
            // Rename the temporary file to the original file
            File originalFile = new File("ProductList.txt");
            File tempFile = new File("ProductList_temp");
            originalFile.delete();
            tempFile.renameTo(originalFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
