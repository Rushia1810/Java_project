import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        usermenu();
        
        
    }
    public static void usermenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("this is user menu");
        System.out.println("1.register");
        System.out.println("2.login");
        System.out.println("3.Exit");
        System.out.print("Enter choice: ");
        
        choice = scanner.nextInt();
        switch(choice){
            case 1:
            UserManager.register();
            usermenu();
            break;
            case 2:
            UserManager.login();
            break;    
            case 3:
            System.exit(1);
            break;
            default:
            System.out.println("Invalid choice");
            usermenu();        
        }scanner.close();
    }
    
    public static void customermenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("this is customer menu");
        System.out.println("1.View Product");
        System.out.println("2.View Cart");
        System.out.println("3.View Receipt");
        System.out.println("4.Checkout");
        System.out.println("5.Logout");
        System.out.print("Enter choice: ");
        
        choice = scanner.nextInt();
        switch(choice){
            case 1:
            productmenu();
            break;
            case 2:
            User.viewCart();
            break;    
            case 3:
            //receipt();
            break;
            case 4:
            User.checkout();
            break;
            case 5:
            UserManager.logout();
            break;
            default:
            System.out.println("Invalid choice");
            customermenu();     
        }scanner.close();
    }
    
    public static void adminmenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("this is admin menu");
        System.out.println("1.View Product");
        System.out.println("2.Add product");
        System.out.println("3.Remove product");
        System.out.println("4.Modify product");
        System.out.println("5.Logout");
        System.out.print("Enter choice: ");
        
        choice = scanner.nextInt();
        switch(choice){
            case 1:
            productmenu();
            break;
            case 2:
            //addProduct();
            break;    
            case 3:
            //removeProduct();
            break;
            case 4:
            //modifyProduct();
            break;
            case 5:
            UserManager.logout();
            break;
            default:
            System.out.println("Invalid choice");
            adminmenu();        
        }scanner.close();
    }
    public static void productmenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("this is product menu");
        System.out.println("1.Keyboard");
        System.out.println("2.Mouse");
        System.out.println("3.Monitor");
        System.out.println("4.Headset");
        System.out.println("5.Back");
        System.out.print("Enter choice: ");
        
        choice = scanner.nextInt();
        switch(choice){
            case 1:
            Product product = new Product("KB1001","Wooting60HE",10,"Wooting",999.99,"high spec keyboard",3);
            User.addtoCart(product);
            App.customermenu();
            //keyboardmenu();
            break;
            case 2:
            //mousemenu();
            break;    
            case 3:
            //monitormenu();
            break;
            case 4:
            //headsetmenu();
            break;
            case 5:
            customermenu();
            break;
            default:
            System.out.println("Invalid choice");
            productmenu();        
        }scanner.close();
    }
    
}
