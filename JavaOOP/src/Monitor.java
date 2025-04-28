import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Monitor extends Product{
    private String resolution;
    private int refreshRate;
    private static int monitorCount;

    public Monitor(){
        
    }
    public Monitor(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,String resolution,int refreshRate){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        monitorCount++;
    }
    public String getResolution(){
        return resolution;
    }
    public int getRefreshRate(){
        return refreshRate;
    }
    public void setResolution(String resolution){
        this.resolution = resolution;
    }
    public void setRefreshRate(int refreshRate){
        this.refreshRate = refreshRate;
    }
    public static int getMonitorCount(){
        return monitorCount;
    }
    public static void setMonitorCount(int monitorCount){
        Monitor.monitorCount = monitorCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + resolution + "," + refreshRate + "\n";
    }
    public static void addMonitor(){
        Monitor monitor = new Monitor();

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
                    System.out.print("Enter MonitorID (MTxxxx)('0' to exit): ");
                    monitor.setproductID(scanner.nextLine());
                    if (monitor.getproductID().equalsIgnoreCase("0")) {
                        System.out.println("Exiting adding process.");
                        writer.close(); // Close the writer before exiting
                        return; // Exit the method
                    }
                    if (!monitor.getproductID().matches("^MT.{4}$")){
                        System.out.println("Only 4 characters after MT are allowed.");
                    }
                } while (!monitor.getproductID().matches("^MT.{4}$"));
                
                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] productinfo = line.split(",");
                    if (productinfo.length >= 1 && productinfo[0].equals(monitor.getproductID())) {
                        System.out.println("MonitorID already exist! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("ProductList"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close();
            
            do {
                System.out.print("Enter Monitor Name: ");
                monitor.setproductName(scanner.nextLine());
                if (monitor.getproductName().isEmpty()) {
                    System.out.println("Product name cannot be empty! Try again.");
                }
            } while (monitor.getproductName().isEmpty());

            do {
                try {
                    System.out.print("Enter Monitor Quantity: ");
                    monitor.setproductQuantity(scanner.nextInt());
                    if (monitor.getproductQuantity() <1) {
                        System.out.println("Quantity must be >= 1");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (monitor.getproductQuantity() <1);
            scanner.nextLine();
            
        	do {
                System.out.print("Enter Monitor Brand: ");
                monitor.setproductBrand(scanner.nextLine());
                if (monitor.getproductBrand().isEmpty()) {
                    System.out.println("Product brand cannot be empty! Try again.");
                }
            } while (!monitor.getproductBrand().isEmpty());

            do {
                try {
                    System.out.print("Enter Monitor Price: ");
                    monitor.setproductPrice(scanner.nextDouble());
                    if (monitor.getproductPrice() <= 0) {
                        System.out.println("Price must be > 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (monitor.getproductPrice() <= 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Monitor Description: ");
                monitor.setproductDesc(scanner.nextLine());
                if (monitor.getproductDesc().isEmpty()) {
                    System.out.println("Product description cannot be empty! Try again.");
                }
            } while (monitor.getproductDesc().isEmpty());

            do {
                try {
                    System.out.print("Enter Monitor Warranty: ");
                    monitor.setproductWarranty(scanner.nextInt());
                    if (monitor.getproductWarranty() < 0) {
                        System.out.println("Warranty must be >= 0");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // Clear the invalid input
                }
            } while (monitor.getproductWarranty() < 0);
            scanner.nextLine();

            do {
                System.out.print("Enter Monitor Resolution: ");
                monitor.setResolution(scanner.nextLine());
                if (monitor.getResolution().isEmpty()) {
                    System.out.println("Product resolution cannot be empty! Try again.");
                }
            } while (monitor.getResolution().isEmpty());

            do {
                System.out.print("Enter Monitor Refresh rate: ");
                monitor.setRefreshRate(scanner.nextInt());
                if (monitor.getRefreshRate() < 60) {
                    System.out.println("Refresh rate must be >= 60");
                }
            } while (monitor.getRefreshRate() < 60);
        scanner.nextLine();
			
		    //comfirmation
            System.out.print("\n\n================================================");
            String comfirmAdding;
            do {
                System.out.print("\nComfirm Adding? (Y/N): ");
                comfirmAdding = scanner.nextLine();
            
                if (comfirmAdding.equalsIgnoreCase("y")){
                    writer.write(monitor.toString());
                    Product.setproductCount(productCount + 1);
                    System.out.println("\n*Monitor added successfully.");
                    writer.close();
                    App.adminmenu();
                } else if (comfirmAdding.equalsIgnoreCase("n")){
                    System.out.println("\n* Fail to add Monitor.");
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
	    return super.display() + "\n Resolution\t\t: " + resolution + 
		                         "\n Refresh rate\t\t: " + refreshRate + "Hz";                  
	}
    public static void removeMonitor(String productID){
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
                    System.out.println(" Product Type\t: Monitor");
                	System.out.println(" ProductID\t: " + productinfo[0]);
                	System.out.println(" Name\t\t: " + productinfo[1]);
                	System.out.println(" Quantity \t: " + productinfo[2]);
                	System.out.println(" Brand\t\t: " + productinfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productinfo[4]);
              		System.out.println(" Description\t: " + productinfo[5]);
                    System.out.println(" Warranty\t: " + productinfo[6] + " months");
                    System.out.println(" Resolution\t\t: " + productinfo[7]);
                    System.out.println(" Refresh rate\t\t: " + productinfo[8]);
                    String choice;
                    do{
                        System.out.println("\nDo you want to delete this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            System.out.println("Monitor with ID " + productID + " has been removed.");
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
                System.out.println("Monitor with ID " + productID + " not found.");
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
    public static void modifyMonitor(String productID){
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
                    System.out.println(" Product Type\t: Monitor");
                	System.out.println(" ProductID\t: " + productinfo[0]);
                	System.out.println(" Name\t\t: " + productinfo[1]);
                	System.out.println(" Quantity \t: " + productinfo[2]);
                	System.out.println(" Brand\t\t: " + productinfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productinfo[4]);
              		System.out.println(" Description\t: " + productinfo[5]);
                    System.out.println(" Warranty\t: " + productinfo[6] + " months");
                    System.out.println(" Resolution\t\t: " + productinfo[7]);
                    System.out.println(" Refresh rate\t\t: " + productinfo[8]);
                    String choice;
                    do{
                        System.out.print("\n\nDo you want to modify this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            String oldproductName = productinfo[1];
                            System.out.print("Enter new Monitor Name: ");
                            productinfo[1] = scanner.nextLine();
                            if (productinfo[1].isEmpty()) {
                                productinfo[1] = oldproductName; // Keep the old name if new name is empty     
                                
                            }
                            
                            String oldproductQuantity = productinfo[2];
                            do {
                                System.out.print("Enter new Monitor Quantity: ");
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
                            System.out.print("Enter new Monitor Brand: ");
                            productinfo[3] = scanner.nextLine();
                            if (productinfo[3].isEmpty()) {
                                productinfo[3] = oldproductBrand; // Keep the old brand if new brand is empty         
                            }
                            
                            String oldproductPrice = productinfo[4];
                            do {
                                try{
                                    System.out.print("Enter new Monitor Price: ");
                                    productinfo[4] = scanner.nextLine();
                                    if (Double.parseDouble(productinfo[4]) <= 0) {
                                        System.out.println("Price must be > 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productinfo[4] = oldproductPrice;
                                }
                            } while (Double.parseDouble(productinfo[4]) <= 0);
                            
                            String oldproductDesc = productinfo[5];
                            System.out.print("Enter new Monitor Description: ");
                            productinfo[5] = scanner.nextLine();
                            if (productinfo[5].isEmpty()) {
                                productinfo[5] = oldproductDesc; // Keep the old description if new description is empty     
                            }
                            
                            String oldproductWarranty = productinfo[6];
                            do {
                                try {
                                    System.out.print("Enter new Monitor Warranty: ");
                                    productinfo[6] = scanner.nextLine();
                                    if (Integer.parseInt(productinfo[6]) < 0) {
                                        System.out.println("Warranty must be >= 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productinfo[6] = oldproductWarranty;
                                }
                            } while (Integer.parseInt(productinfo[6]) < 0);
                            
                            String oldResolution = productinfo[7];
                            System.out.print("Enter new Monitor Resolution: ");
                            productinfo[7] = scanner.nextLine();
                            if (productinfo[7].isEmpty()) {
                                productinfo[7] = oldResolution; // Keep the old layout if new layout is empty     
                            }
                            
                            String oldRefreshRate = productinfo[8];
                            System.out.print("Enter new Monitor Refresh Rate: ");
                            productinfo[8] = scanner.nextLine();
                            if (productinfo[8].isEmpty()) {
                                productinfo[8] = oldRefreshRate;
                                
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
                System.out.println("Monitor with ID " + productID + " not found.");
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
