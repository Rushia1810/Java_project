import java.io.*;
import java.util.*;

public class User {
    private String username;
    private String password;
    private String role;

    public User(){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    //get methods
    public String getusername(){
        return username;
    }
    public String getpassword(){
        return password;
    }
    public String getrole(){
        return role;
    }
    @Override
    public String toString(){
        return username + "," + password + "," + role;
    }
    public void register(){
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserList.txt", true));

            //Read existing usernames to prevent duplicates**
            BufferedReader reader = new BufferedReader(new FileReader("UserList.txt"));
            String line;
            boolean isDuplicate;
            
            do {
                isDuplicate = false;
                System.out.print("Enter name: ");
                username = scanner.nextLine();

                //Check if username already exists
                while ((line = reader.readLine()) != null) { 
                    String[] parts = line.split(",");
                    if (parts.length >= 1 && parts[0].equalsIgnoreCase(username)) {
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
                password = scanner.nextLine();
                if (password.length() < 8) {
                    System.out.println("Password too short! Try again.");
                }
            } while (password.length() < 8);

            //Ensure role is either customer or admin
            do {
                System.out.print("Enter role (Customer or Admin): ");
                role = scanner.nextLine();
                if (!role.equalsIgnoreCase("Customer")&&!role.equalsIgnoreCase("Admin")) {
                    System.out.println("Invalid role");
                }
            } while (!role.equalsIgnoreCase("Customer")&&!role.equalsIgnoreCase("Admin"));
            

            //Write user data to file in correct format
            writer.write(toString());
            writer.newLine();
            writer.close();
            System.out.println("Registered successfully!");
            usermenu();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void login(){

    }
    public void usermenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("this is a menu");
        System.out.println("1.register");
        System.out.println("2.login");
        System.out.print("Enter a number: ");
        choice = scanner.nextInt();
        switch(choice){
            case 1:
            register();
            break;
            case 2:
            login();
            break;            
        } scanner.close();
    }
}   


    
   
        
    
    
    
 


    
   

