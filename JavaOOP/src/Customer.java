import java.io.*;
import java.util.*;

public class Customer extends User{
    private Cart cart;

    public Customer(){
        cart = new Cart();
    }
    public Customer(String username, String password, String role, String email, long phoneNumber, Cart cart){
        super(username, password, role, email, phoneNumber);
        cart = new Cart();
    }
    
    public Cart getcart(){
        return cart;
    }
    public void setcart(Cart cart){
        this.cart = cart;
    }
    public static void viewCart(){
        Cart.displaycart();
        System.out.printf("Total Price: " + "RM%.2f\n" , Cart.getTotal());
    }
    public static void addtoCart(){
        Scanner scanner = new Scanner(System.in);
        String productCode;
        do{
            System.out.print("\nEnter product ID to add to cart('0' to exit): ");
            productCode = scanner.nextLine();
            if (productCode.equals("0")){
                App.customermenu();
            } else if (productCode.isEmpty()){
                System.out.println("Invalid input");
            } else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("ProductList"));
                    String line;
                    boolean found = false;
                    while ((line = reader.readLine()) != null) {
                        String[] productInfo = line.split(",");
                        String productID = productInfo[0];
                        String productName = productInfo[1];
                        int productQuantity = Integer.parseInt(productInfo[2]);
                        String productBrand = productInfo[3];
                        double productPrice = Double.parseDouble(productInfo[4]);
                        String productDesc = productInfo[5];
                        int productWarranty = Integer.parseInt(productInfo[6]);

                        if (productID.equals(productCode)) {
                            if (productQuantity > 0) { 
                                if (productID.startsWith("KB")){
                                    String layout = productInfo[7];
                                    String type = productInfo[8];
                                    Product product = new Keyboard(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, layout, type);
                                    Cart.addProduct(product);
                                    System.out.println("Product added to cart: " + product.getproductName() + "\t" + "RM" + product.getproductPrice());
                                } else if (productID.startsWith("MS")) {
                                    int weight = Integer.parseInt(productInfo[7]);
                                    String connectionType = productInfo[8];
                                    Product product = new Mouse(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, weight, connectionType);
                                    Cart.addProduct(product);
                                    System.out.println("Product added to cart: " + product.getproductName() + "\t" + "RM" + product.getproductPrice());
                                } else if (productID.startsWith("MT")) {
                                    String resolution = productInfo[7];
                                    int refreshRate = Integer.parseInt(productInfo[8]);
                                    Product product = new Monitor(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, resolution, refreshRate);
                                    Cart.addProduct(product);
                                    System.out.println("Product added to cart: " + product.getproductName() + "\t" + "RM" + product.getproductPrice());
                                } else if (productID.startsWith("HS")) {
                                    int driver = Integer.parseInt(productInfo[7]);
                                    String surroundSound = productInfo[8];
                                    Product product = new Headset(productID, productName, productQuantity, productBrand, productPrice, productDesc, productWarranty, driver, surroundSound);
                                    Cart.addProduct(product);
                                    System.out.println("Product added to cart: " + product.getproductName() + "\t" + "RM" + product.getproductPrice());
                                }
                                found = true;
                                break;
                            } else {
                                System.out.println("Product out of stock.");
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Product not found.");
                        try{
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        Product.allProductMenu();
                    }
                    reader.close();
            
                } catch (IOException e) {
                    System.out.println("An error occurred while adding the product: " + e.getMessage());
                }
            }
        } while (!productCode.equals("0") ); 
    }
    public static void removefromCart(){ 
        Scanner scanner = new Scanner(System.in);
        String productCode;
        ArrayList<Product> products = Cart.getCart();
        
        do{
            System.out.print("\nEnter product ID to remove to cart('0' to exit): ");
            productCode = scanner.nextLine();
            if (productCode.equals("0")){
                App.customermenu();
            } else if (productCode.isEmpty()){
                System.out.println("Invalid input");
            } else {
                boolean found = false;
               for (Product product: products){
                    if (product.getproductID().equals(productCode)){
                        Cart.removeProduct(product);
                        System.out.println("Product removed from cart: " + product.getproductName());
                        found = true;
                        break;
                    } 
                }
                if (!found) {
                System.out.println("Product not found in cart.");
                }
            }
        } while (!productCode.equals("0"));
    }
    public void checkout(){
        Cart.clearCart();
    }
}
