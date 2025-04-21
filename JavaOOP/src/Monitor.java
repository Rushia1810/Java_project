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
        return super.toString() + "," + resolution + "," + refreshRate;
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
            
            System.out.print("Enter Monitor Name: ");
            monitor.setproductName(scanner.nextLine());

            do {
                System.out.print("Enter Monitor Quantity: ");
                monitor.setproductQuantity(scanner.nextInt());
                if (monitor.getproductQuantity() <1) {
                    System.out.println("Quantity must be >= 1");
                }
            } while (monitor.getproductQuantity() <1);
            scanner.nextLine();
            
        	
            System.out.print("Enter Monitor Brand: ");
            monitor.setproductBrand(scanner.nextLine());

            do {
                System.out.print("Enter Monitor Price: ");
                monitor.setproductPrice(scanner.nextDouble());
                if (monitor.getproductPrice() <= 0) {
                    System.out.println("Price must be > 0");
                }
            } while (monitor.getproductPrice() <= 0);
            scanner.nextLine();

            System.out.print("Enter Monitor Description: ");
            monitor.setproductDesc(scanner.nextLine());

            do {
                System.out.print("Enter Monitor Warranty: ");
                monitor.setproductWarranty(scanner.nextInt());
                if (monitor.getproductWarranty() < 0) {
                    System.out.println("Warranty must be >= 0");
                }
            } while (monitor.getproductWarranty() < 0);
            scanner.nextLine();

            System.out.print("Enter Monitor Resolution: ");
            monitor.setResolution(scanner.nextLine());

            System.out.print("Enter Monitor Refresh rate: ");
            monitor.setRefreshRate(scanner.nextInt());
            scanner.nextLine();
			
		    //comfirmation
            System.out.print("\n\n================================================");
            System.out.print("\nComfirm Adding? (Y/N): ");
            String comfirmAdding = scanner.nextLine();
        
            if (comfirmAdding.equalsIgnoreCase("y")){
                writer.write(monitor.toString());
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
                    System.out.println("Monitor with ID " + productID + " has been removed.");
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
