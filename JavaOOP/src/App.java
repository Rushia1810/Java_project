import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        usermenu();
        scanner.close();
        
        
    }
    public static void usermenu(){
        int choice = 0;
        while (true) {
            System.out.println("this is user menu");
            System.out.println("1.register");
            System.out.println("2.login");
            System.out.println("3.Exit");
            System.out.print("Enter choice: ");
        
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                UserManager.register();
                break;
                case 2:
                UserManager.login();
                break;    
                case 3:
                System.out.println("Thanks for shopping with us!");
                System.exit(1);
                break;
                default:
                System.out.println("Invalid choice");
                        
            }
        }
    }
    
    public static void customermenu(){
        int choice = 0;
        while (true) {
            System.out.println("this is customer menu");
            System.out.println("1.View Product");
            System.out.println("2.View Cart");
            System.out.println("3.View Receipt");
            System.out.println("4.Checkout");
            System.out.println("5.Logout");
            System.out.print("Enter choice: ");
        
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                productmenu("customer");
                break;
                case 2:
                System.out.println("Cart contains: ");
                Customer.viewCart();
                break;    
                case 3:
                //receipt();
                break;
                case 4:
                paymentmenu();
                break;
                case 5:
                UserManager.logout();
                break;
                default:
                System.out.println("Invalid choice");
                    
            }
        }
    }
    
    public static void adminmenu(){
        int choice = 0;
        while (true) {
            System.out.println("this is admin menu");
            System.out.println("1.View Product");
            System.out.println("2.Add product");
            System.out.println("3.Remove product");
            System.out.println("4.Modify product");
            System.out.println("5.Logout");
            System.out.print("Enter choice: ");
            
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                productmenu("admin");
                break;
                case 2:
                Admin.addProduct();
                break;    
                case 3:
                Admin.removeProduct();
                break;
                case 4:
                //modifyProduct();
                break;
                case 5:
                UserManager.logout();
                break;
                default:
                System.out.println("Invalid choice");
                    
            }
        }
    }
    public static void productmenu(String userType) {
        int choice = 0;
        while (true) {
            System.out.println("this is product menu");
            System.out.println("1.All Products");
            System.out.println("2.Keyboard");
            System.out.println("3.Mouse");
            System.out.println("4.Monitor");
            System.out.println("5.Headset");
            System.out.println("6.Back");
            System.out.print("Enter choice: ");
            
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                Product.allProductMenu();
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    customermenu(); // Return to customer menu
                }
                break;
                case 2:
                Product product = new Product("KB1001","Wooting60HE",10,"Wooting",999.99,"high spec keyboard",3);
                Product product1 = new Product("KB1002","Logitech G915",10,"Logitech",999.99,"high spec keyboard",3);
                Customer.addtoCart(product);
                Customer.addtoCart(product1);
                App.customermenu();
                //keyboardmenu();
                break;
                case 3:
                //mousemenu();
                break;    
                case 4:
                //monitormenu();
                break;
                case 5:
                //headsetmenu();
                break;
                case 6:
                return;                
                default:
                System.out.println("Invalid choice");
                        
            }
        }
    }
    public static void paymentmenu(){
        //print receipt logic here
        Cart.displaycart(); 
        System.out.println("Total amount: " + Cart.getTotal());

        int choice = 0;
        while (true) {
            System.out.println("this is payment menu");
            System.out.println("1.Online Banking");
            System.out.println("2.Credit/Debit Card");
            System.out.println("3.E-Wallet");
            System.out.println("4.Back");
            System.out.print("Enter choice: ");
            
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                //cash();
                break;
                case 2:
                //creditCard();
                break;    
                case 3:
                //eWallet();
                break;
                case 4:
                customermenu();
                break;
                default:
                System.out.println("Invalid choice");
                        
            }
        }
    }
}
