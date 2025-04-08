public class User {
    private String username;
    private String password;
    private String role;
    private Cart cart;
    
        public User(){
    
        }
    
        public User(String username, String password, String role, Cart cart){
            this.username = username;
            this.password = password;
            this.role = role;
            cart = new Cart();
        }
        //get methods
        public String getusername(){
            return username;
        }
        public String getpassword(){
            return password;
        }
        public String getrole(){
            return role;
        }
        public Cart getcart(){
            return cart;
        }
        //set methods
        public void setusername(String username){
            this.username = username;
        }
        public void setpassword(String password){
            this.password = password;
        }
        public void setrole(String role){
            this.role = role;
        }
        public void setcart(Cart cart){
            this.cart = cart;
        }
        @Override
        public String toString(){
            return username + "," + password + "," + role;
        }
        public static void viewCart(){
            Cart.displaycart();
            
        }
        public static void addtoCart(Product product){
            Cart.addProduct(product);
        }
        public static void removefromCart(Product product){
            Cart.removeProduct(product);
        }
        public static void checkout(){
            //payment and receipt logic here
            System.out.println("Checkout successful!");
            Cart.clearCart();
        }
        public static void addProduct(){
            
        }
        public static void removeProduct(){
            
        }
        public static void modifyProduct(){
            
        }

       
}