import java.util.*;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        usermenu();
        scanner.close();
        
        
    }
    public static void usermenu(){
        int choice = 0;
        while (true) {
            logo();
            System.out.println("1.register");
            System.out.println("2.login");
            System.out.println("3.Exit");
            System.out.print("Enter choice: ");
        
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
           
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
            System.out.println("\n");
            logo();
            System.out.println("1.View Product");
            System.out.println("2.View Cart");
            System.out.println("3.View Order");
            System.out.println("4.Checkout");
            System.out.println("5.Logout");
            System.out.print("Enter choice: ");
        
            try{
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                productmenu("customer");
                break;
                case 2:
                System.out.println("\nCart contains: ");
                Customer.viewCart();
                int choice2 = 0;
                while (true){
                    System.out.println("\nDo you want to buy more or remove product from cart?");
                    System.out.println("1. Buy more product");
                    System.out.println("2. Remove product from cart");
                    System.out.println("3. Back");
                    System.out.print("Enter choice: ");
                    try {
                        choice2 = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                        continue;
                    }
                    switch (choice2) {
                        case 1:
                        productmenu("customer");
                        break;
                        case 2:
                        Customer.removefromCart();
                        break;
                        case 3:
                        customermenu();
                        break;
                        default:
                        System.out.println("Invalid choice");
                    }
                } 
                case 3:
                Order.displayOrder();
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
            System.out.println("\n");
            logo();
            System.out.println("1.View Product");
            System.out.println("2.Add product");
            System.out.println("3.Remove product");
            System.out.println("4.Modify product");
            System.out.println("5.Logout");
            System.out.print("Enter choice: ");
            
            try{
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

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
                Admin.modifyProduct();
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
            System.out.println("\n");
            logo();
            System.out.println("1.All Products");
            System.out.println("2.Keyboard");
            System.out.println("3.Mouse");
            System.out.println("4.Monitor");
            System.out.println("5.Headset");
            System.out.println("6.Custom filter");
            System.out.println("7.Back");
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
                Product.allProductMenu();
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    Customer.addtoCart(); // Return to customer menu
                }
                break;
                case 2:
                Product.keyboardmenu();
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    Customer.addtoCart(); 
                }
                break;
                case 3:
                Product.mousemenu();
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    Customer.addtoCart(); 
                }
                break;
                case 4:
                Product.monitormenu();
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    Customer.addtoCart(); 
                }
                break;
                case 5:
                Product.headsetmenu();
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    Customer.addtoCart(); 
                }
                break;
                case 6:
                if (userType.equals("admin")) {
                    Product.customFilter("admin");
                } else if (userType.equals("customer")) {
                    Product.customFilter("customer"); 
                }
                break;
                case 7:
                if (userType.equals("admin")) {
                    adminmenu(); // Return to admin menu
                } else if (userType.equals("customer")) {
                    customermenu(); // Return to customer menu
                }
                break;               
                default:
                System.out.println("Invalid choice");
                
            }
        }
    }
    public static void paymentmenu(){
        System.out.println("\n");
        logo();
        System.out.println("\n--- Checkout ---");
        Customer.viewCart();
        // Apply discount
        Payment.applyDiscount();
        Payment.displayTotal();
        
        int choice = 0;
        while (true) {
            System.out.println("\n");
            System.out.println("1.Online Banking");
            System.out.println("2.Credit/Debit Card");
            System.out.println("3.E-Wallet");
            System.out.println("4.Back");
            System.out.print("Enter choice: ");
            
            try{
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            Payment payment;
            Order order = new Order();
            switch (choice) {
                case 1:
                    payment = new OnlineBanking(Cart.getTotal());
                    payment.processPayment();
                    Product.adjustProductQuantity();
                    order.saveOrder();
                    Cart.clearCart();
                    customermenu();
                    break;
                case 2:
                    payment = new CreditCard(Cart.getTotal());
                    payment.processPayment();
                    Product.adjustProductQuantity();
                    order.saveOrder();
                    Cart.clearCart();
                    customermenu();
                    break;
                case 3:
                    payment = new EWallet(Cart.getTotal());
                    payment.processPayment();
                    Product.adjustProductQuantity();
                    order.saveOrder();
                    Cart.clearCart();
                    customermenu();
                    break;
                case 4:
                    customermenu();
                    break;
                default:
                    System.out.println("Invalid choice");
                        
            }
        }
    }
    public static void logo(){
        System.out.println(" /$$$$$$$$ /$$      /$$ /$$   /$$ /$$$$$$$   /$$$$$$  /$$$$$$$$");
        System.out.println("|__  $$__/| $$$    /$$$| $$  | $$| $$__  $$ /$$__  $$|__  $$__/");
        System.out.println("   | $$   | $$$$  /$$$$| $$  | $$| $$  \\ $$| $$  \\ $$   | $$   ");
        System.out.println("   | $$   | $$ $$/$$ $$| $$  | $$| $$$$$$$/| $$$$$$$$   | $$   ");
        System.out.println("   | $$   | $$  $$$| $$| $$  | $$| $$__  $$| $$__  $$   | $$   ");
        System.out.println("   | $$   | $$ \\  $| $$| $$  | $$| $$  \\ $$| $$  | $$   | $$   ");
        System.out.println("   | $$   | $$  \\/ | $$|  $$$$$$/| $$  | $$| $$  | $$   | $$   ");
        System.out.println("   |__/   |__/     |__/ \\______/ |__/  |__/|__/  |__/   |__/   ");
    }
}
