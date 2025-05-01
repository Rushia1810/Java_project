import java.util.Scanner;

public abstract class Payment {
    private double amount;
    
    public Payment() {
       
    }
    public Payment(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public abstract void processPayment();

    public static void applyDiscount() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDo you have a discount code? (Y/N): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            System.out.print("Enter discount code: ");
            String code = scanner.nextLine();
            double total = Cart.getTotal();
            double newTotal;
            if (code.equals("TMURAT1")) {
                newTotal = total * 0.9;
                Order.setDiscountAmount(total - newTotal);
                System.out.printf("Discount applied! -RM%.2f%n", Order.getDiscountAmount());
            } else {
                Order.setDiscountAmount(0);
                System.out.println("Invalid discount code. Proceeding with original total.");
            }
        }
    }
    public static void displayTotal(){
        double newtotal = Cart.getTotal() - Order.getDiscountAmount();
        Cart.setTotal(newtotal);
        System.out.printf("Total Price: " + "RM%.2f\n" ,newtotal);
    }
}