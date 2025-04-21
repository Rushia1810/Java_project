import java.util.*;
import java.io.*;

public class UserManager {
    
    
    public static void register(){
        User user = new User();
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserList", true));
            //Read existing usernames to prevent duplicates**
            BufferedReader reader = new BufferedReader(new FileReader("UserList"));
            String line;
            boolean isDuplicate;
            
            do {
                isDuplicate = false;
                System.out.print("Enter username('0' to exit): ");
                user.setusername(scanner.nextLine());
                if (user.getusername().equalsIgnoreCase("0")) {
                    System.out.println("Exiting registration process.");
                    writer.close(); // Close the writer before exiting
                    return; // Exit the method
                }
                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] userinfo = line.split(",");
                    if (userinfo.length >= 1 && userinfo[0].equalsIgnoreCase(user.getusername())) {
                        System.out.println("Username already taken! Try a different one.");
                        isDuplicate = true;
                        reader.close(); //Close and reopen reader to restart loop
                        reader = new BufferedReader(new FileReader("UserList"));
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
                user.setphoneNumber(scanner.nextLong());
                if(user.getphoneNumber() < 60100000000L || user.getphoneNumber() > 60199999999L){ 
                    System.out.println("Invalid phone number!");
                }  
            } while (user.getphoneNumber() < 60100000000L || user.getphoneNumber() > 60199999999L);
            
            scanner.nextLine(); // Consume the newline character left by nextLong()

            //Ensure role is either customer or admin
            if (user.getusername().startsWith("$")){
                user.setrole("Admin");
            } else {
                user.setrole("Customer");
            }
            
            //Write user data to file in correct format
            writer.write(user.toString());
            writer.newLine();
            writer.close();
            System.out.println("Registered successfully!");
            App.usermenu();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void login(){
        try {
            Scanner scanner = new Scanner(System.in);
            boolean loggedIn = false; 
            boolean userFound = false;      
            String line;

            do {
                BufferedReader reader = new BufferedReader(new FileReader("UserList"));
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
            
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    public static void logout(){
        System.out.println("Successfully logged out");
        App.usermenu();
    }
}

