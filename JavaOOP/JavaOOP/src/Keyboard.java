public class Keyboard extends Product{
    private String Layout;
    private String Type;
    private static int keyboardCount;
    public Keyboard(){
        
    }
    public Keyboard(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,String Layout,String Type){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.Layout = Layout;
        this.Type = Type;
        keyboardCount++;
    }
    public String getLayout(){
        return Layout;
    }
    public String getType(){
        return Type;
    }
    public void setLayout(String Layout){
        this.Layout = Layout;
    }
    public void setType(String Type){
        this.Type = Type;
    }
    public static int getKeyboardCount(){
        return keyboardCount;
    }
    public static void setKeyboardCount(int keyboardCount){
        Keyboard.keyboardCount = keyboardCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + Layout + "," + Type;
    }
}
