import java.util.*;
import java.io.*;

public class Cart {
    private static ArrayList<Product> products;
    private static double total;

    public Cart(){
        
    }
    public Cart(ArrayList<Product> products, double total){
        products = new ArrayList<Product>();
        total = 0.0;
    }
    public static ArrayList<Product> getCart() {
        return products;
    }
    public static double getTotal() {
        return total;
    }
    public static void setCart(ArrayList<Product> products) {
        Cart.products = new ArrayList<>(products);
    }
    public static void setTotal(double total) {
        Cart.total = total;
    }
    public static void displaycart(){
        if (products == null) {
            System.out.println("Cart is empty");
            App.customermenu();
        }
        if (products.isEmpty()){
            System.out.println("Cart is empty");
            App.customermenu();
        }
        
        ArrayList<Product> checked = new ArrayList<>();
        
        for (int i = 0; i < products.size(); i++) {
            Product current = products.get(i);
            if (!checked.contains(current)) {
                int count = 1;
            for (int j = i + 1; j < products.size(); j++) {
                if (products.get(j).equals(current)) {
                    count++;
                }
            }
            System.out.printf("%s\t%s\tx%d\tRM%.2f\n", 
            current.getproductID(), 
            current.getproductName(), 
            count, 
            current.getproductPrice() * count);
            checked.add(current);
            }
        }
        calcTotal();
    }
    public static void addProduct(Product product){
        if (products == null) {
            products = new ArrayList<Product>();
        }
        products.add(product);   
    }
    public static void removeProduct(Product product){
        products.remove(product);
    }
    public static void clearCart(){
        products.clear();
        
    }
    public static void calcTotal(){
        total = 0.0;
        for (Product product : products) {
            total += product.getproductPrice();
        }
    }

}
