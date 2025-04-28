import java.util.Scanner;
import java.io.*;

public class Admin extends User{
    public Admin(){
        
    }
    public Admin(String username, String password, String role, String email, long phoneNumber){
        super(username, password, role, email, phoneNumber);
    }
    
    public static void addProduct(){
		Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("\n\n----------------------------------------");
		  	System.out.println("         Item you want to ADD");
		  	System.out.println("----------------------------------------");
		  	System.out.println("1. Keyboard");
		  	System.out.println("2. Mouse");
            System.out.println("3. Monitor");
            System.out.println("4. Headset");
            System.out.println("5. Back");
		  	System.out.print("Enter choice: ");
		    choice = scanner.nextInt();		
            switch(choice){
                case 1:
                System.out.println("\n\n----------------------------------------");
                System.out.println("            Keyboard Adding...");
                System.out.println("----------------------------------------");
                Keyboard.addKeyboard();
                break;
                
                case 2:
                System.out.println("\n\n----------------------------------------");
                System.out.println("           Mouse Adding...");
                System.out.println("----------------------------------------");
                Mouse.addMouse();
                break;

                case 3:
                System.out.println("\n\n----------------------------------------");
                System.out.println("           Monitor Adding...");
                System.out.println("----------------------------------------");
                Monitor.addMonitor();
                break;

                case 4:
                System.out.println("\n\n----------------------------------------");
                System.out.println("           Headset Adding...");
                System.out.println("----------------------------------------");
                Headset.addHeadset();
                break;
            
                case 5:
                App.adminmenu();
                break;
                    
            default:
                System.out.println("\n\n*Invalid input*");
            }
        }    
	}
    
    public static void removeProduct(){
        Scanner scanner = new Scanner(System.in);
        String productID;

        do{
            System.out.print("Enter Item Code to Delete('0' to exit): ");
            productID = scanner.nextLine();
            if (productID.equals("0")){
                App.adminmenu();
            } else if (productID.length() != 6){
                System.out.print("\n*Invalid Item Code Length(XXXXXX).\n");
            }
        } while (productID.length() != 6);
        scanner.close();
    
        if (productID.startsWith("KB")) {
            Keyboard.removeKeyboard(productID);
        } else if (productID.startsWith("MS")) {
            Mouse.removeMouse(productID);
        } else if (productID.startsWith("MT")) {
            Monitor.removeMonitor(productID);
        } else if (productID.startsWith("HS")) {
            Headset.removeHeadset(productID);
        } else {
            System.out.println("\n\n*Invalid Item Code*");
            removeProduct();
        }
    }
    public static void modifyProduct(){
        try {
            Scanner scanner = new Scanner(System.in);
            String productID;
            System.out.print("Enter Item Code to Modify: ");
            productID = scanner.nextLine();
            if (productID.length() != 6){
                System.out.print("\n*Invalid Item Code Length(XXXXXX).\n");
                modifyProduct();
            } else if (productID.startsWith("KB")) {
                Keyboard.modifyKeyboard(productID);
            } else if (productID.startsWith("MS")) {
                Mouse.modifyMouse(productID);
            } else if (productID.startsWith("MT")) {
                Monitor.modifyMonitor(productID);
            } else if (productID.startsWith("HS")) {
                Headset.modifyHeadset(productID);
            } else {
                System.out.println("\n\n*Invalid Item Code*");
                modifyProduct();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
