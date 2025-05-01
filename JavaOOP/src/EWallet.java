import java.util.*;

public class EWallet extends Payment {

    public EWallet(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProcessing E-Wallet Payment:");
        
        String phoneNumber;
        do {
            System.out.print("Enter phone number (starting with 601, 11 digits total) ('0' to exit ): ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
            if(phoneNumber.startsWith("6011")){ 
                if (phoneNumber.length() != 12) {
                    System.out.println("Invalid phone number!");
                } 
            } else if (!phoneNumber.startsWith("6010") || !phoneNumber.startsWith("6012") || !phoneNumber.startsWith("6013") || !phoneNumber.startsWith("6014") || !phoneNumber.startsWith("6016") || !phoneNumber.startsWith("6017") || !phoneNumber.startsWith("6018") || !phoneNumber.startsWith("6019")) {
                if (phoneNumber.length() != 11) {
                    System.out.println("Invalid phone number!");
                }
            }
        }  while (!phoneNumber.startsWith("6011") && !phoneNumber.startsWith("6010") && !phoneNumber.startsWith("6012") && !phoneNumber.startsWith("6013") && !phoneNumber.startsWith("6014") && !phoneNumber.startsWith("6016") && !phoneNumber.startsWith("6017") && !phoneNumber.startsWith("6018") && !phoneNumber.startsWith("6019"));

        String pin;
        do {
            System.out.print("Enter 6-digit PIN ('0' to exit ): ");
            pin = scanner.nextLine();
            if (pin.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
            if (!pin.matches("\\d{6}")) {
                System.out.println("Error: PIN must be exactly 6 digits");
            }
        } while (!pin.matches("\\d{6}"));
        System.out.println("\nPayment Successful! Thank you for your purchase!");
    }
}