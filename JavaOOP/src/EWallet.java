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
            System.out.print("Enter phone number (must start with 601) ('0' to exit): ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
        
            // List of valid prefixes
            String[] validPrefixes = {"6010", "6011", "6012", "6013", "6014", "6016", "6017", "6018", "6019"};
            boolean isValidPrefix = false;
        
            // Check if the phone number starts with a valid prefix
            for (String prefix : validPrefixes) {
                if (phoneNumber.startsWith(prefix)) {
                    isValidPrefix = true;
                    break;
                }
            }
        
            // Validate phone number length and prefix
            if (!isValidPrefix || (phoneNumber.startsWith("6011") && phoneNumber.length() != 12) || (!phoneNumber.startsWith("6011") && phoneNumber.length() != 11)) {
                System.out.println("Invalid phone number!");
            } else {
                break; // Exit the loop if the phone number is valid
            }
        } while (true);

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