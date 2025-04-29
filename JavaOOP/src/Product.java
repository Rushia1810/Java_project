import java.io.*;
import java.util.*;

public class Product {
    private String productID;
    private String productName;
    private int productQuantity;
    private String productBrand;
    private double productPrice;
    private String productDesc;
    private int productWarranty;
    private static int productCount;

    public Product(){
        
    }
    public Product(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty){
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productWarranty = productWarranty;
        productCount++;
    }
    public String getproductID(){
        return productID;
    }
    public String getproductName(){
        return productName;
    }
    public int getproductQuantity(){
        return productQuantity;
    }
    public String getproductBrand(){
        return productBrand;
    }
    public double getproductPrice(){
        return productPrice;
    }
    public String getproductDesc(){
        return productDesc;
    }
    public int getproductWarranty(){
        return productWarranty;
    }
    public static int getproductCount(){
        return productCount;
    }   
    public void setproductID(String productID){
        this.productID = productID;
    }
    public void setproductName(String productName){
        this.productName = productName;
    }
    public void setproductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }
    public void setproductBrand(String productBrand){
        this.productBrand = productBrand;
    }
    public void setproductPrice(double productPrice){
        this.productPrice = productPrice;
    }
    public void setproductDesc(String productDesc){
        this.productDesc = productDesc;
    }
    public void setproductWarranty(int productWarranty){
        this.productWarranty = productWarranty;
    }
    public static void setproductCount(int productCount){
        Product.productCount = productCount;
    }
    
    @Override   
    public String toString(){
        return productID + "," + productName + "," + productQuantity + "," + productBrand + "," + productPrice + "," + productDesc + "," + productWarranty;
    }

    public static void allProductMenu(){
        productCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            Product ProductArray[] = new Product[100];
            int i = 0;

            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                String productID = productInfo[0];
                String productName = productInfo[1];
                int productQuantity = Integer.parseInt(productInfo[2]);
                String productBrand = productInfo[3];
                double productPrice = Double.parseDouble(productInfo[4]);
                String productDesc = productInfo[5];
                int productWarranty = Integer.parseInt(productInfo[6]);

                if (productID.startsWith("KB")){
                    String layout = productInfo[7];
                    String type = productInfo[8];
                    ProductArray[i] = new Keyboard(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, layout, type);
                    i++;
                } else if (productID.startsWith("MS")) {
                    int weight = Integer.parseInt(productInfo[7]);
                    String connectionType = productInfo[8];
                    ProductArray[i] = new Mouse(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, weight, connectionType);
                    i++;
                } else if (productID.startsWith("MT")) {
                    String resolution = productInfo[7];
                    int refreshRate = Integer.parseInt(productInfo[8]);
                    ProductArray[i] = new Monitor(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, resolution, refreshRate);
                    i++;
                } else if (productID.startsWith("HS")) {
                    int driver = Integer.parseInt(productInfo[7]);
                    String surroundSound = productInfo[8];
                    ProductArray[i] = new Headset(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, driver, surroundSound);
                    i++;
                } 
            }   
            reader.close();        
            System.out.println("\nTotal Products: " + productCount);
            System.out.println("==========================");
            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Product " + (i+1) + ":" );
                System.out.println(" --------");
                if (ProductArray[i] instanceof Keyboard){
                    System.out.println(" Product Type\t\t: Keyboard");
                }else if (ProductArray[i] instanceof Mouse){
                    System.out.println(" Product Type\t\t: Mouse");
                }else if (ProductArray[i] instanceof Monitor){
                    System.out.println(" Product Type\t\t: Monitor");
                }else if (ProductArray[i] instanceof Headset){           
                    System.out.println(" Product Type\t\t: Headset");
    		    }
    		    System.out.println(ProductArray[i].display());
    		
			} 			
        
   	    }catch (IOException e){
            e.printStackTrace();
        }
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return productID.equals(p.productID);
    }
    @Override
    public int hashCode() {
        return productID.hashCode();
    }
    public static void keyboardmenu(){
        Keyboard.setKeyboardCount(0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            Product ProductArray[] = new Product[100];
            int i = 0;

            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                String productID = productInfo[0];
                String productName = productInfo[1];
                int productQuantity = Integer.parseInt(productInfo[2]);
                String productBrand = productInfo[3];
                double productPrice = Double.parseDouble(productInfo[4]);
                String productDesc = productInfo[5];
                int productWarranty = Integer.parseInt(productInfo[6]);

                if (productID.startsWith("KB")){
                    String layout = productInfo[7];
                    String type = productInfo[8];
                    ProductArray[i] = new Keyboard(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, layout, type);
                    i++;
                }
            }
            reader.close();
            System.out.println("\nTotal Keyboards: " + Keyboard.getKeyboardCount());
            System.out.println("==========================");
            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Keyboard " + (i+1) + ":" );
                System.out.println(" --------");
                System.out.println(ProductArray[i].display());
            
            } 
			
   	    }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void mousemenu(){
        Mouse.setMouseCount(0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            Product ProductArray[] = new Product[100];
            int i = 0;
    
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                String productID = productInfo[0];
                String productName = productInfo[1];
                int productQuantity = Integer.parseInt(productInfo[2]);
                String productBrand = productInfo[3];
                double productPrice = Double.parseDouble(productInfo[4]);
                String productDesc = productInfo[5];
                int productWarranty = Integer.parseInt(productInfo[6]);
    
                if (productID.startsWith("MS")) {
                    int weight = Integer.parseInt(productInfo[7]);
                    String connectionType = productInfo[8];
                    ProductArray[i] = new Mouse(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, weight, connectionType);
                    i++;
                }
            }
            reader.close();
            System.out.println("\nTotal Mice: " + Mouse.getMouseCount());
            System.out.println("==========================");
            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Mouse " + (i+1) + ":" );
                System.out.println(" --------");
                System.out.println(ProductArray[i].display());
            
            } 
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void monitormenu(){
        Monitor.setMonitorCount(0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            Product ProductArray[] = new Product[100];
            int i = 0;
    
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                String productID = productInfo[0];
                String productName = productInfo[1];
                int productQuantity = Integer.parseInt(productInfo[2]);
                String productBrand = productInfo[3];
                double productPrice = Double.parseDouble(productInfo[4]);
                String productDesc = productInfo[5];
                int productWarranty = Integer.parseInt(productInfo[6]);
    
                if (productID.startsWith("MT")) {
                    String resolution = productInfo[7];
                    int refreshRate = Integer.parseInt(productInfo[8]);
                    ProductArray[i] = new Monitor(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, resolution, refreshRate);
                    i++;
                }
            }
            reader.close();
            System.out.println("\nTotal Monitors: " + Monitor.getMonitorCount());
            System.out.println("==========================");
            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Monitor " + (i+1) + ":" );
                System.out.println(" --------");
                System.out.println(ProductArray[i].display());
            
            } 
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void headsetmenu(){
        Headset.setHeadsetCount(0);
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
            String line;
            Product ProductArray[] = new Product[100];
            int i = 0;
    
            while ((line = reader.readLine()) != null) {
                String[] productInfo = line.split(",");
                String productID = productInfo[0];
                String productName = productInfo[1];
                int productQuantity = Integer.parseInt(productInfo[2]);
                String productBrand = productInfo[3];
                double productPrice = Double.parseDouble(productInfo[4]);
                String productDesc = productInfo[5];
                int productWarranty = Integer.parseInt(productInfo[6]);
    
                if (productID.startsWith("HS")) {
                    int driver = Integer.parseInt(productInfo[7]);
                    String surroundSound = productInfo[8];
                    ProductArray[i] = new Headset(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, driver, surroundSound);
                    i++;
                }
            }
            reader.close();
            System.out.println("\nTotal Headsets: " + Headset.getHeadsetCount());
            System.out.println("==========================");
            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(" Headset " + (i+1) + ":" );
                System.out.println(" --------");
                System.out.println(ProductArray[i].display());
            
            } 
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void customFilter(String userType){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true){
            System.out.println("\n1. Filter by Name");
            System.out.println("2. Filter by Brand");
            System.out.println("3. Filter by Price Range");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            try{
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    productCount = 0;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
                        String line;
                        Product ProductArray[] = new Product[100];
                        int i = 0;

                        while ((line = reader.readLine()) != null) {
                            String[] productInfo = line.split(",");
                            String productID = productInfo[0];
                            String productName = productInfo[1];
                            int productQuantity = Integer.parseInt(productInfo[2]);
                            String productBrand = productInfo[3];
                            double productPrice = Double.parseDouble(productInfo[4]);
                            String productDesc = productInfo[5];
                            int productWarranty = Integer.parseInt(productInfo[6]);
                                
                            if (productName.contains(name)) {
                                if (productID.startsWith("KB")){
                                    String layout = productInfo[7];
                                    String type = productInfo[8];
                                    ProductArray[i] = new Keyboard(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, layout, type);
                                    i++;
                                } else if (productID.startsWith("MS")) {
                                    int weight = Integer.parseInt(productInfo[7]);
                                    String connectionType = productInfo[8];
                                    ProductArray[i] = new Mouse(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, weight, connectionType);
                                    i++;
                                } else if (productID.startsWith("MT")) {
                                    String resolution = productInfo[7];
                                    int refreshRate = Integer.parseInt(productInfo[8]);
                                    ProductArray[i] = new Monitor(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, resolution, refreshRate);
                                    i++;
                                } else if (productID.startsWith("HS")) {
                                    int driver = Integer.parseInt(productInfo[7]);
                                    String surroundSound = productInfo[8];
                                    ProductArray[i] = new Headset(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, driver, surroundSound);
                                    i++;
                                }
                            }
                        }       
                        reader.close();    
                        System.out.println("\nTotal Products with Name that contains "  + "(" + name + ")" + " : " + productCount);
                        System.out.println("==========================");
                            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println(" Product " + (i+1) + ":" );
                                System.out.println(" --------");
                                if (ProductArray[i] instanceof Keyboard){
                                    System.out.println(" Product Type\t\t: Keyboard");
                                }else if (ProductArray[i] instanceof Mouse){
                                    System.out.println(" Product Type\t\t: Mouse");
                                }else if (ProductArray[i] instanceof Monitor){
                                    System.out.println(" Product Type\t\t: Monitor");
                                }else if (ProductArray[i] instanceof Headset){           
                                    System.out.println(" Product Type\t\t: Headset");
                                }
                                System.out.println(ProductArray[i].display());
                            
                            }      
                        
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Enter product brand: ");
                    String brand = scanner.nextLine();
                    productCount = 0;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
                        String line;
                        Product ProductArray[] = new Product[100];
                        int i = 0;

                        while ((line = reader.readLine()) != null) {
                            String[] productInfo = line.split(",");
                            String productID = productInfo[0];
                            String productName = productInfo[1];
                            int productQuantity = Integer.parseInt(productInfo[2]);
                            String productBrand = productInfo[3];
                            double productPrice = Double.parseDouble(productInfo[4]);
                            String productDesc = productInfo[5];
                            int productWarranty = Integer.parseInt(productInfo[6]);
                                
                            if (productBrand.contains(brand)) {
                                if (productID.startsWith("KB")){
                                    String layout = productInfo[7];
                                    String type = productInfo[8];
                                    ProductArray[i] = new Keyboard(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, layout, type);
                                    i++;
                                } else if (productID.startsWith("MS")) {
                                    int weight = Integer.parseInt(productInfo[7]);
                                    String connectionType = productInfo[8];
                                    ProductArray[i] = new Mouse(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, weight, connectionType);
                                    i++;
                                } else if (productID.startsWith("MT")) {
                                    String resolution = productInfo[7];
                                    int refreshRate = Integer.parseInt(productInfo[8]);
                                    ProductArray[i] = new Monitor(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, resolution, refreshRate);
                                    i++;
                                } else if (productID.startsWith("HS")) {
                                    int driver = Integer.parseInt(productInfo[7]);
                                    String surroundSound = productInfo[8];
                                    ProductArray[i] = new Headset(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, driver, surroundSound);
                                    i++;
                                }
                            }
                        }       
                        reader.close();    
                        System.out.println("\nTotal Products with Brand that contains " + "(" + brand + ")" + " : " + productCount);
                        System.out.println("==========================");
                            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println(" Product " + (i+1) + ":" );
                                System.out.println(" --------");
                                if (ProductArray[i] instanceof Keyboard){
                                    System.out.println(" Item Type\t\t: Keyboard");
                                }else if (ProductArray[i] instanceof Mouse){
                                    System.out.println(" Item Type\t\t: Mouse");
                                }else if (ProductArray[i] instanceof Monitor){
                                    System.out.println(" Item Type\t\t: Monitor");
                                }else if (ProductArray[i] instanceof Headset){           
                                    System.out.println(" Item Type\t\t: Headset");
                                }
                                System.out.println(ProductArray[i].display());
                            
                            }      
                        
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                break;
                case 3:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    productCount = 0;
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
                        String line;
                        Product ProductArray[] = new Product[100];
                        int i = 0;

                        while ((line = reader.readLine()) != null) {
                            String[] productInfo = line.split(",");
                            String productID = productInfo[0];
                            String productName = productInfo[1];
                            int productQuantity = Integer.parseInt(productInfo[2]);
                            String productBrand = productInfo[3];
                            double productPrice = Double.parseDouble(productInfo[4]);
                            String productDesc = productInfo[5];
                            int productWarranty = Integer.parseInt(productInfo[6]);
                                
                            if (productPrice >= minPrice && productPrice <= maxPrice) {
                                if (productID.startsWith("KB")){
                                    String layout = productInfo[7];
                                    String type = productInfo[8];
                                    ProductArray[i] = new Keyboard(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, layout, type);
                                    i++;
                                } else if (productID.startsWith("MS")) {
                                    int weight = Integer.parseInt(productInfo[7]);
                                    String connectionType = productInfo[8];
                                    ProductArray[i] = new Mouse(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, weight, connectionType);
                                    i++;
                                } else if (productID.startsWith("MT")) {
                                    String resolution = productInfo[7];
                                    int refreshRate = Integer.parseInt(productInfo[8]);
                                    ProductArray[i] = new Monitor(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, resolution, refreshRate);
                                    i++;
                                } else if (productID.startsWith("HS")) {
                                    int driver = Integer.parseInt(productInfo[7]);
                                    String surroundSound = productInfo[8];
                                    ProductArray[i] = new Headset(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, driver, surroundSound);
                                    i++;
                                }
                            }
                        }       
                        reader.close();    
                        System.out.printf("\nTotal Products between RM%.2f - RM%.2f: %d\n", minPrice, maxPrice, productCount);
                        System.out.println("\n==========================");
                            for (i = 0; i < ProductArray.length && ProductArray[i] != null; i++) {
                                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                                System.out.println(" Product " + (i+1) + ":" );
                                System.out.println(" --------");
                                if (ProductArray[i] instanceof Keyboard){
                                    System.out.println(" Item Type\t\t: Keyboard");
                                }else if (ProductArray[i] instanceof Mouse){
                                    System.out.println(" Item Type\t\t: Mouse");
                                }else if (ProductArray[i] instanceof Monitor){
                                    System.out.println(" Item Type\t\t: Monitor");
                                }else if (ProductArray[i] instanceof Headset){           
                                    System.out.println(" Item Type\t\t: Headset");
                                }
                                System.out.println(ProductArray[i].display());
                            
                            }      
                        
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                break;
                case 4:
                    if (userType == "customer"){
                        App.productmenu("customer");
                    } else if (userType == "admin"){
                        App.productmenu("admin");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public String display(){        
        return " ProductID\t\t: " + productID + 
            "\n Name\t\t\t: " + productName + 
            "\n Quantity\t\t: " + productQuantity + 
            "\n Brand\t\t\t: " + productBrand +
            "\n Price\t\t\t: RM" + productPrice +
            "\n Description\t\t: " + productDesc +
            "\n Warranty\t\t: " + productWarranty + " months";
    }
}

