import java.util.*;

public class OnlineBanking extends Payment {

    public OnlineBanking(double amount) {
        super(amount);

    }

    @Override
    public void processPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProcessing Online Banking Payment:");
        
        String bankId;
        do {
            System.out.print("Enter Bank ID (6-12 characters) ('0' to exit ): ");
            bankId = scanner.nextLine();
            if (bankId.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
            if (bankId.length() < 6 || bankId.length() > 12) {
                System.out.println("Error: Bank ID must be 6-12 characters long");
            }
        } while (bankId.length() < 6 || bankId.length() > 12);

        String password;
        do {
            System.out.print("Enter Password (6-11 characters) ('0' to exit ): ");
            password = scanner.nextLine();
            if (password.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
            if (password.length() < 6 || password.length() > 11) {
                System.out.println("Error: Password must be 6-11 characters long");
            }
        } while (password.length() < 6 || password.length() > 11);
        System.out.println("\nPayment Successful! Thank you for your purchase!");
    }
}