import java.io.*;
import java.util.*;

public class User {
    private String username;
    private String password;
    private String role;

    public User(String username,String password,String role){
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
    public static void register(){
        try{
            File UserFile = new File("UserList.txt");
            if(!UserFile.exists()){
                System.out.println("File doesnt exist");
            }
            PrintWriter writer = new PrintWriter(new FileWriter(UserFile,true));
            BufferedReader reader = new BufferedReader(new FileReader(UserFile));

        }

    }   
}

    
   
        
    
    
    
 


    
   

