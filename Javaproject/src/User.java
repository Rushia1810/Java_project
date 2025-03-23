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
        try{
            Scanner scanner = new Scanner(System.in);
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserList.txt",true));
            //add user to file
            System.out.print("Enter name: ");
            username = scanner.nextLine();
            System.out.print("Enter password: ");
            password = scanner.nextLine();
            while (password.length() < 8) {
                System.out.println("password too short");
                System.out.print("Enter password: ");
                password = scanner.nextLine();
            }
            System.out.print("Enter role: ");
            role = scanner.nextLine();
            writer.write(toString());
            writer.newLine();
            scanner.close();
            writer.close();
        }catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
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
            System.out.println("login");;
            break;            
        } scanner.close();
    }

    }   
}

    
   
        
    
    
    
 


    
   

