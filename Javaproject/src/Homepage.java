import java.util.Scanner;

public class Homepage {
    
    public static void main(String[] args) {
        usermenu();
                
          
    }
    public static void usermenu(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("this is a menu");
        System.out.println("1.register");
        System.out.println("2.login");
        System.out.print("Enter a number: ");
        choice = scanner.nextInt();
        switch(choice){
            case 1:
            User.register();
            break;
            case 2:
            System.out.println("login");;
            break;            
        } scanner.close();
    }
}
