import java.util.*;
import java.io.*;

public class UserManager {  
    private static String loggedInUsername;

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
    public static void setLoggedInUsername(String username) {
        loggedInUsername = username;
    }
    public static void register(){
        User user = new User();
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserList.txt", true));
            BufferedReader reader = new BufferedReader(new FileReader("UserList.txt"));
            String line;
            boolean isDuplicate;
            
            do {
                isDuplicate = false;
                do {
                    System.out.print("Enter username('0' to exit): ");
                    user.setusername(scanner.nextLine());
                    if (user.getusername().equalsIgnoreCase("0")) {
                        System.out.println("Exiting registration process.");
                        writer.close(); // Close the writer before exiting
                        return; // Exit the method
                    }
                    if (user.getusername().isEmpty()) {
                    System.out.println("Username cannot be empty! Try again.");
                    }
                } while (user.getusername().isEmpty());
                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] userinfo = line.split(",");
                    if (userinfo.length >= 1 && userinfo[0].equalsIgnoreCase(user.getusername())) {
                        System.out.println("Username already taken! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("UserList.txt"));
                        break;
                    }
                }
            } while (isDuplicate);
            reader.close(); //Close reader after final check

            //Ensure password meets length requirement
            do {
                System.out.print("Enter password (at least 8 characters): ");
                user.setpassword(scanner.nextLine());
                if (user.getpassword().length() < 8) {
                    System.out.println("Password too short! Try again.");
                }
            } while (user.getpassword().length() < 8);

            do {
                System.out.print("Enter email: ");
                user.setemail(scanner.nextLine());
                if (!user.getemail().contains("@gmail.com")) {
                    System.out.println("Invalid email format!");
                }
            } while (!user.getemail().contains("@gmail.com"));
            

            do {
                System.out.print("Enter phone number (must start with 601): ");
                user.setphoneNumber(scanner.nextLine());
                if(user.getphoneNumber().startsWith("6011")){ 
                    if (user.getphoneNumber().length() != 12) {
                        System.out.println("Invalid phone number!");
                    } 
                } else if (!user.getphoneNumber().startsWith("6010") || !user.getphoneNumber().startsWith("6012") || !user.getphoneNumber().startsWith("6013") || !user.getphoneNumber().startsWith("6014") || !user.getphoneNumber().startsWith("6016") || !user.getphoneNumber().startsWith("6017") || !user.getphoneNumber().startsWith("6018") || !user.getphoneNumber().startsWith("6019")) {
                    if (user.getphoneNumber().length() != 11) {
                        System.out.println("Invalid phone number!");
                    }
                }
            } while (!user.getphoneNumber().startsWith("6011") && !user.getphoneNumber().startsWith("6010") && !user.getphoneNumber().startsWith("6012") && !user.getphoneNumber().startsWith("6013") && !user.getphoneNumber().startsWith("6014") && !user.getphoneNumber().startsWith("6016") && !user.getphoneNumber().startsWith("6017") && !user.getphoneNumber().startsWith("6018") && !user.getphoneNumber().startsWith("6019"));
            

            //Ensure role is either customer or admin
            if (user.getusername().startsWith("$")){
                user.setrole("Admin");
            } else {
                user.setrole("Customer");
            }
            
            //Write user data to file in correct format
            writer.write(user.toString());
            writer.close();
            System.out.println("Registered successfully!");
            App.usermenu();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void login(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("UserList.txt"));
            Scanner scanner = new Scanner(System.in);
            boolean loggedIn = false; 
            boolean userFound = false;      
            String line;

            do {
                reader = new BufferedReader(new FileReader("UserList.txt"));
                System.out.print("Enter your username to login('0' to exit ): ");
                String loginUsername = scanner.nextLine();

                while ((line = reader.readLine()) != null) {
                    String[] userinfo = line.split(",");
                    if (userinfo[0].equals(loginUsername)) {
                        userFound = true;

                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();
                        
                        if (userinfo[1].equals(loginPassword)) {
                            System.out.println("Login successful!");
                            loggedIn = true;
                            setLoggedInUsername(loginUsername);
                        } else {
                            System.out.println("Invalid password.");
                            break;
                        }

                        if (userinfo[2].equalsIgnoreCase("Customer")){
                            App.customermenu();
                        } else {
                            App.adminmenu();
                        }      
                    } else if (loginUsername.equalsIgnoreCase("0")){
                        System.out.println("Exiting login process.");
                        loggedIn = true; // Exit the loop
                        userFound = true; // Set userFound to true to exit the loop
                        break;

                    }              
                } if (!userFound){
                    System.out.println("Invalid username.");
                } 
                reader.close();               
            } while (!loggedIn);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void logout(){
        if (Cart.getCart() != null) {
            Cart.clearCart();
            System.out.println("Successfully logged out");
            App.usermenu();
        } else {
        System.out.println("Successfully logged out");
        App.usermenu();
        }
    }
}

