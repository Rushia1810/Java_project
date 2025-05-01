import java.util.*;

public class CreditCard extends Payment {

    public CreditCard(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProcessing Credit/Debit Card Payment:");
        
        String cardNumber;
        do {
            System.out.print("Enter 16-digit card number ('0' to exit ): ");
            cardNumber = scanner.nextLine().replaceAll("\\s+", "");
            if (cardNumber.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
            if (!cardNumber.matches("\\d{16}")) {
                System.out.println("Error: Card number must be exactly 16 digits");
            }
        } while (!cardNumber.matches("\\d{16}"));

        String cvv;
        do {
            System.out.print("Enter 3-digit CVV ('0' to exit ): ");
            cvv = scanner.nextLine();
            if (cvv.equalsIgnoreCase("0")) {
                System.out.println("Exiting payment process.");
                System.out.println("Payment cancelled.");
                App.paymentmenu();
            }
            if (!cvv.matches("\\d{3}")) {
                System.out.println("Error: CVV must be exactly 3 digits");
            }
        } while (!cvv.matches("\\d{3}"));
        System.out.println("\nPayment Successful! Thank you for your purchase!");
    }
}