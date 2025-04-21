import java.util.*;
//import java.io.*;

public class Cart {
    private static ArrayList<Product> products;
    private static double total;

    public Cart(){

    }
    public Cart(ArrayList<Product> products, double total){
        products = new ArrayList<Product>();
        total = 0.0;
    }
    public ArrayList<Product> getCart() {
        return products;
    }
    public static double getTotal() {
        return total;
    }
    public void setCart(ArrayList<Product> products) {
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
        for (Product product : products) {
            System.out.println(product.getproductName() + "\t" + "RM" + product.getproductPrice());
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
        ;
    }
    public static void clearCart(){
        products.clear();
        System.out.println("Cart has been cleared.");
    }
    public static void calcTotal(){
        total = 0.0;
        for (Product product : products) {
            total += product.getproductPrice();
        }
    }

}
