public class Customer extends User{
    private Cart cart;

    public Customer(){
        
    }
    public Customer(String username, String password, String role, String email, long phoneNumber, Cart cart){
        super(username, password, role, email, phoneNumber);
        cart = new Cart();
    }
    
    public Cart getcart(){
        return cart;
    }
    public void setcart(Cart cart){
        this.cart = cart;
    }
    public static void viewCart(){
        Cart.displaycart();
        System.out.println("Total Price: " + "RM" + Cart.getTotal());
    }
    public static void addtoCart(Product product){
        Cart.addProduct(product);
    }
    public static void removefromCart(Product product){
        Cart.removeProduct(product);
    }
    public static void checkout(){
        Cart.clearCart();
    }
}
