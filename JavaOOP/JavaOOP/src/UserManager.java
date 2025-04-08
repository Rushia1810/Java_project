import java.util.*;
import java.io.*;

public class UserManager {
    
    public static void register(){
        User user = new User();
        try{
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserList", true));
            //Read existing usernames to prevent duplicates**
            BufferedReader reader = new BufferedReader(new FileReader("UserList"));
            String line;
            boolean isDuplicate;
            
            do {
                isDuplicate = false;
                System.out.print("Enter username: ");
                user.setusername(scanner.nextLine());

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

            //Ensure role is either customer or admin
            do {
                System.out.print("Enter role (Customer or Admin): ");
                user.setrole(scanner.nextLine());
                if (!user.getrole().equalsIgnoreCase("Customer")&&!user.getrole().equalsIgnoreCase("Admin")) {
                    System.out.println("Invalid role");
                }
            } while (!user.getrole().equalsIgnoreCase("Customer")&&!user.getrole().equalsIgnoreCase("Admin"));
            

            //Write user data to file in correct format
            writer.write(user.toString());
            writer.newLine();
            scanner.close();
            writer.close();
            System.out.println("Registered successfully!");
            
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

            do{
                BufferedReader reader = new BufferedReader(new FileReader("UserList"));
                System.out.print("Enter your username to login: ");
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
                        }else{
                            System.out.println("Invalid password.");
                            break;
                        }

                        if(userinfo[2].equalsIgnoreCase("Customer")){
                            App.customermenu();
                        }else{
                            App.adminmenu();
                        }      
                    }               
                }if(!userFound){
                    System.out.println("Invalid username.");
                }
                
                reader.close();               
            }while(!loggedIn);
            scanner.close();
        }catch (IOException e) {
        e.printStackTrace();
        }
    }
    public static void logout(){
        System.out.println("Successfully logged out");
        App.usermenu();
    }
}

