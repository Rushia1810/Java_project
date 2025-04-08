import java.util.*;
//import java.io.*;

public class Cart {
    private static ArrayList<Product> products;

    public Cart(){

    }
    public Cart(ArrayList<Product> products){
        products = new ArrayList<Product>();
        
    }
    public ArrayList<Product> getCart() {
        return products;
    }
    public void setCart(ArrayList<Product> products) {
        Cart.products = new ArrayList<>(products);
    }
    public static void displaycart(){
        if(products == null){
            System.out.println("Cart is empty");
            App.customermenu();
        }
        System.out.println("Cart contains: ");
        for(Product product : products){
            System.out.println(product.getproductName() + "\t" + product.getproductPrice());
        }
        Cart.calcTotal();
        App.customermenu();
    }
    public static void addProduct(Product product){
        if(products == null){
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
        double total = 0;
        for(Product product : products){
            total += product.getproductPrice();
        }
        System.out.println("Total: " + total);
    }

}
