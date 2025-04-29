import java.io.*;
import java.util.*;

public class Mouse extends Product{
    private int weight;
    private String connectionType;
    private static int mouseCount;
    public Mouse(){
        
    }
    public Mouse(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,int weight,String connectionType){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.weight = weight;
        this.connectionType = connectionType;
        mouseCount++;
    }
    public int getWeight(){
        return weight;
    }
    public String getConnectionType(){
        return connectionType;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setIsWired(String connectionType){
        this.connectionType = connectionType;
    }
    public static int getMouseCount(){
        return mouseCount;
    }
    public static void setMouseCount(int mouseCount){
        Mouse.mouseCount = mouseCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + weight + "," + connectionType + "\n";
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
                    String[] productInfo = line.split(",");
                    if (productInfo.length >= 1 && productInfo[0].equals(mouse.getproductID())) {
                        System.out.println("MouseID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close();
            
            do {
                System.out.print("Enter Mouse Name: ");
                mouse.setproductName(scanner.nextLine());
                if (mouse.getproductName().isEmpty()) {
                    System.out.println("Mouse name cannot be empty! Try again.");
                }
            } while (mouse.getproductName().isEmpty());

            do {
                try{
                    System.out.print("Enter Mouse Quantity: ");
                    mouse.setproductQuantity(scanner.nextInt());
                    if (mouse.getproductQuantity() <1) {
                        System.out.println("Quantity must be >= 1");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (mouse.getproductQuantity() <1);
            scanner.nextLine();
            
        	do {
                System.out.print("Enter Mouse Brand: ");
                mouse.setproductBrand(scanner.nextLine());
                if (mouse.getproductBrand().isEmpty()) {
                    System.out.println("Mouse brand cannot be empty! Try again.");
                }
            } while (mouse.getproductBrand().isEmpty());

            do {
                try {
                    System.out.print("Enter Mouse Price: ");
                    mouse.setproductPrice(scanner.nextDouble());
                    if (mouse.getproductPrice() <= 0) {
                        System.out.println("Price must be > 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (mouse.getproductPrice() <= 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Mouse Description: ");
                mouse.setproductDesc(scanner.nextLine());
                if (mouse.getproductDesc().isEmpty()) {
                    System.out.println("Mouse description cannot be empty! Try again.");
                }
            } while (mouse.getproductDesc().isEmpty());

            do {
                try {
                    System.out.print("Enter Mouse Warranty: ");
                    mouse.setproductWarranty(scanner.nextInt());
                    if (mouse.getproductWarranty() < 0) {
                        System.out.println("Warranty must be >= 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (mouse.getproductWarranty() < 0);
            scanner.nextLine();

            do {
                try {
                    System.out.print("Enter Mouse Weight: ");
                    mouse.setWeight(scanner.nextInt());
                    if (mouse.getWeight() <= 0) {
                        System.out.println("Weight must be > 0");
                    } 
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (mouse.getWeight() <= 0);
            scanner.nextLine();

            
            do {
                System.out.print("Is Mouse Wired?(Y/N) ");
                mouse.setIsWired(scanner.nextLine());
                if (mouse.getConnectionType().equalsIgnoreCase("y")){
                    mouse.setIsWired("Wired");
                    break;
                } else if (mouse.getConnectionType().equalsIgnoreCase("n")){
                    mouse.setIsWired("Wireless");
                    break;
                } else {
                    System.out.println("Invalid input! Please enter Y or N.");
                }
                
            } while (!mouse.getConnectionType().equalsIgnoreCase("Wired") && !mouse.getConnectionType().equalsIgnoreCase("Wiredless"));
                
        String comfirmAdding;
        do {
            System.out.print("\nComfirm Adding? (Y/N): ");
            comfirmAdding = scanner.nextLine();
            
            if (comfirmAdding.equalsIgnoreCase("y")){
                writer.write(mouse.toString());
                Product.setproductCount(productCount + 1);
                System.out.println("\n*Mouse added successfully.");
                writer.close();
                App.adminmenu();
            } else if (comfirmAdding.equalsIgnoreCase("n")){
                System.out.println("\n* Fail to add Mouse.");
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
	    return super.display() + "\n Weight\t\t\t: " + weight + "g" +
		                         "\n ConnectionType\t\t: " + connectionType;
	}
    public static void removeMouse(String productID){
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
                    System.out.println(" Product Type\t: Mouse");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Weight\t\t: " + productInfo[7] + "g");
                    System.out.println(" ConnectionType\t\t: " + productInfo[8]);
                    String choice;
                    do{
                        System.out.println("\nDo you want to delete this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("Mouse with ID " + productID + " has been removed.");
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
    public static void modifyMouse(String productID){
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
                    System.out.println(" Product Type\t: Mouse");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Weight\t\t: " + productInfo[7] + "g");
                    System.out.println(" ConnectionType\t\t: " + productInfo[8]);
                    String choice;
                    do{
                        System.out.println("\nDo you want to modify this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            String oldproductName = productInfo[1];
                            System.out.print("Enter new Mouse Name: ");
                            productInfo[1] = scanner.nextLine();
                            if (productInfo[1].isEmpty()) {
                                productInfo[1] = oldproductName; // Keep the old name if new name is empty     
                                
                            }
                            
                            String oldproductQuantity = productInfo[2];
                            do {
                                System.out.print("Enter new Mouse Quantity: ");
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
                            System.out.print("Enter new Mouse Brand: ");
                            productInfo[3] = scanner.nextLine();
                            if (productInfo[3].isEmpty()) {
                                productInfo[3] = oldproductBrand; // Keep the old brand if new brand is empty         
                            }
                            
                            String oldproductPrice = productInfo[4];
                            do {
                                try{
                                    System.out.print("Enter new Mouse Price: ");
                                    productInfo[4] = scanner.nextLine();
                                    if (Double.parseDouble(productInfo[4]) <= 0) {
                                        System.out.println("Price must be > 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[4] = oldproductPrice;
                                }
                            } while (Double.parseDouble(productInfo[4]) <= 0);
                            
                            String oldproductDesc = productInfo[5];
                            System.out.print("Enter new Mouse Description: ");
                            productInfo[5] = scanner.nextLine();
                            if (productInfo[5].isEmpty()) {
                                productInfo[5] = oldproductDesc; // Keep the old description if new description is empty     
                            }
                            
                            String oldproductWarranty = productInfo[6];
                            do {
                                try {
                                    System.out.print("Enter new Mouse Warranty: ");
                                    productInfo[6] = scanner.nextLine();
                                    if (Integer.parseInt(productInfo[6]) < 0) {
                                        System.out.println("Warranty must be >= 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[6] = oldproductWarranty;
                                }
                            } while (Integer.parseInt(productInfo[6]) < 0);
                            
                            String oldWeight = productInfo[7];
                            do {
                                System.out.print("Enter new Mouse Weight: ");
                                productInfo[7] = scanner.nextLine();
                                try{
                                    if (Integer.parseInt(productInfo[7]) <=0 ) {
                                        System.out.println("Weight must be > 0");
                                    }
                                } catch (NumberFormatException e){
                                    productInfo[7] = oldWeight;
                                }
                            } while (Integer.parseInt(productInfo[7]) <= 0);

                            String oldConnectionType = productInfo[8];
                            do {
                                System.out.print("Is Mouse Wired?(Y/N) ");
                                productInfo[8] = (scanner.nextLine());
                                if (productInfo[8].equalsIgnoreCase("y")){
                                    productInfo[8] = "Wired";
                                    break;
                                } else if (productInfo[8].equalsIgnoreCase("n")){
                                    productInfo[8] = "Wired";
                                    break;
                                } else if (productInfo[8].isEmpty()){
                                    productInfo[8] = oldConnectionType;
                                } else {
                                    System.out.println("Invalid input! Please enter Y or N.");
                                }
                            } while (!productInfo[8].equalsIgnoreCase("y") && !productInfo[8].equalsIgnoreCase("n") && !productInfo[8].isEmpty());
                         
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
