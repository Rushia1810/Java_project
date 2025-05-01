import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Order {
    private int orderNo;
    private Date orderDate;
    private static double discountAmount;
    private double totalAmount;

    public Order() {

    }
    public Order(int orderNo, Date orderDate, double totalAmount) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    public int getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public static double getDiscountAmount() {
        return discountAmount;
    }
    public static void setDiscountAmount(double discountAmount) {
        Order.discountAmount = discountAmount;
    }
    
    public void saveOrder() {
        try{
            ArrayList<Product> products = Cart.getCart();
            BufferedWriter writer = new BufferedWriter(new FileWriter("OrderList.txt", true));
            BufferedReader reader = new BufferedReader(new FileReader("OrderList.txt"));
            String line;
            int orderNo = 100001;

            while ((line = reader.readLine()) != null) {
                String[] orderInfo = line.split(",");
                if (orderInfo.length > 0) {
                    int currentOrderNo = Integer.parseInt(orderInfo[0]);
                    if (currentOrderNo >= orderNo) {
                        orderNo = currentOrderNo + 1;
                    }
                }
            }
            reader.close();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            orderDate = new Date();
            String formattedDate = dateFormat.format(orderDate); 
            discountAmount = getDiscountAmount();
            String username = UserManager.getLoggedInUsername();
            writer.write(orderNo + "," + formattedDate + "," + discountAmount + "," + username + ",");
            for (Product product : products) {
                writer.write(product.getproductID() + "," + product.getproductName() + "," + product.getproductPrice() + ",");
            }
            writer.write(Cart.getTotal() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }
    public static void displayOrder() {
        try{
            Scanner scanner = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new FileReader("OrderList.txt"));
            String line;
            boolean found = false;
            String orderNo;

            System.out.println("\nOrder History:");
            System.out.println("Order Number" + "\tOrder Date" + "\t\tTotal Amount");
            while ((line = reader.readLine()) != null) {
                String[] orderList = line.split(",");
                if (orderList[3].equals(UserManager.getLoggedInUsername())) {
                    
                    System.out.printf("#%s\t\t%s\t\tRM%.2f%n", orderList[0], orderList[1], Double.parseDouble(orderList[orderList.length - 1]));
                }
            }
            reader.close();
            reader = new BufferedReader(new FileReader("OrderList.txt"));
            do{
                System.out.print("\nEnter your order number ('0' to exit): ");
                orderNo = scanner.nextLine();
                if (orderNo.equals("0")){
                    App.customermenu();
                } else if (orderNo.isEmpty()){
                    System.out.println("Invalid input");
                }
            } while (orderNo.isEmpty());

           
            while ((line = reader.readLine()) != null) {
                String[] orderInfo = line.split(",");
                if (orderInfo[0].equals(orderNo) && orderInfo[3].equals(UserManager.getLoggedInUsername())) {
                    System.out.println("\nOrder Number: #" + orderInfo[0]);
                    System.out.println("Order Date: " + orderInfo[1]);
                    System.out.println("=====================================================================");
                    ArrayList<String> checked = new ArrayList<>();

                    for (int i = 4; i < orderInfo.length - 1; i += 3) {
                        String productID = orderInfo[i];
                        String productName = orderInfo[i + 1];
                        double productPrice = Double.parseDouble(orderInfo[i + 2]);
    
                        // Skip if the product has already been processed
                        if (checked.contains(productID)) {
                            continue;
                        }
    
                        // Count occurrences of the current product
                        int count = 1;
                        for (int j = i + 3; j < orderInfo.length - 1; j += 3) {
                            if (orderInfo[j].equals(productID)) {
                                count++;
                            }
                        }

                        checked.add(productID);
                        System.out.printf("%s\t%s\t\tx%d\tRM%.2f\n", productID, productName, count, productPrice * count);
                    }

                    System.out.printf("Discount Amount: \t\t\t\t-RM%.2f%n" , Double.parseDouble(orderInfo[2]));
                    System.out.printf("Total Amount: \t\t\t\t\tRM%.2f%n" , Double.parseDouble(orderInfo[orderInfo.length - 1]));
                    found = true;
                    break;
                } 
            }
            if (!found) {
                System.out.println("Order not found.");
            }
            reader.close();
            
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

       
    }
}
