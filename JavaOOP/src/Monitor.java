import java.io.*;
import java.util.*;

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
                    String[] productInfo = line.split(",");
                    if (productInfo.length >= 1 && productInfo[0].equals(monitor.getproductID())) {
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
                String[] productInfo = line.split(",");
                if (productInfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Monitor");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Resolution\t\t: " + productInfo[7]);
                    System.out.println(" Refresh rate\t\t: " + productInfo[8] + "Hz");
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
                String[] productInfo = line.split(",");
                if (productInfo[0].equals(productID)) {
                    found = true;
                    System.out.println("\n Product found:");
                    System.out.println(" Product Type\t: Monitor");
                	System.out.println(" ProductID\t: " + productInfo[0]);
                	System.out.println(" Name\t\t: " + productInfo[1]);
                	System.out.println(" Quantity \t: " + productInfo[2]);
                	System.out.println(" Brand\t\t: " + productInfo[3]);
                	System.out.println(" Price\t\t: " + "RM" + productInfo[4]);
              		System.out.println(" Description\t: " + productInfo[5]);
                    System.out.println(" Warranty\t: " + productInfo[6] + " months");
                    System.out.println(" Resolution\t\t: " + productInfo[7]);
                    System.out.println(" Refresh rate\t\t: " + productInfo[8] + "Hz");
                    String choice;
                    do{
                        System.out.print("\n\nDo you want to modify this product? (Y/N): ");
                        Scanner scanner = new Scanner(System.in);
                        choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            String oldproductName = productInfo[1];
                            System.out.print("Enter new Monitor Name: ");
                            productInfo[1] = scanner.nextLine();
                            if (productInfo[1].isEmpty()) {
                                productInfo[1] = oldproductName; // Keep the old name if new name is empty     
                                
                            }
                            
                            String oldproductQuantity = productInfo[2];
                            do {
                                System.out.print("Enter new Monitor Quantity: ");
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
                            System.out.print("Enter new Monitor Brand: ");
                            productInfo[3] = scanner.nextLine();
                            if (productInfo[3].isEmpty()) {
                                productInfo[3] = oldproductBrand; // Keep the old brand if new brand is empty         
                            }
                            
                            String oldproductPrice = productInfo[4];
                            do {
                                try{
                                    System.out.print("Enter new Monitor Price: ");
                                    productInfo[4] = scanner.nextLine();
                                    if (Double.parseDouble(productInfo[4]) <= 0) {
                                        System.out.println("Price must be > 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[4] = oldproductPrice;
                                }
                            } while (Double.parseDouble(productInfo[4]) <= 0);
                            
                            String oldproductDesc = productInfo[5];
                            System.out.print("Enter new Monitor Description: ");
                            productInfo[5] = scanner.nextLine();
                            if (productInfo[5].isEmpty()) {
                                productInfo[5] = oldproductDesc; // Keep the old description if new description is empty     
                            }
                            
                            String oldproductWarranty = productInfo[6];
                            do {
                                try {
                                    System.out.print("Enter new Monitor Warranty: ");
                                    productInfo[6] = scanner.nextLine();
                                    if (Integer.parseInt(productInfo[6]) < 0) {
                                        System.out.println("Warranty must be >= 0");
                                    }
                                } catch (NumberFormatException e) {
                                    productInfo[6] = oldproductWarranty;
                                }
                            } while (Integer.parseInt(productInfo[6]) < 0);
                            
                            String oldResolution = productInfo[7];
                            System.out.print("Enter new Monitor Resolution: ");
                            productInfo[7] = scanner.nextLine();
                            if (productInfo[7].isEmpty()) {
                                productInfo[7] = oldResolution; // Keep the old layout if new layout is empty     
                            }
                            
                            String oldRefreshRate = productInfo[8];
                            System.out.print("Enter new Monitor Refresh Rate: ");
                            productInfo[8] = scanner.nextLine();
                            if (productInfo[8].isEmpty()) {
                                productInfo[8] = oldRefreshRate;
                                
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
