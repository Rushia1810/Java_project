public class Product {
    protected String productID;
    protected String productName;
    protected int productQuantity;
    protected String productBrand;
    protected double productPrice;
    protected String productDesc;
    protected int productWarranty;
    protected static int productCount;

    public Product(){
        
    }
    public Product(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty){
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productPrice = productPrice;
        this.productDesc = productDesc;
        this.productWarranty = productWarranty;
        productCount++;
    }
    public String getproductID(){
        return productID;
    }
    public String getproductName(){
        return productName;
    }
    public int getproductQuantity(){
        return productQuantity;
    }
    public String getproductBrand(){
        return productBrand;
    }
    public double getproductPrice(){
        return productPrice;
    }
    public String getproductDesc(){
        return productDesc;
    }
    public int getproductWarranty(){
        return productWarranty;
    }
    public static int getproductCount(){
        return productCount;
    }   
    public void setproductID(String productID){
        this.productID = productID;
    }
    public void setproductName(String productName){
        this.productName = productName;
    }
    public void setproductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }
    public void setproductBrand(String productBrand){
        this.productBrand = productBrand;
    }
    public void setproductPrice(double productPrice){
        this.productPrice = productPrice;
    }
    public void setproductDesc(String productDesc){
        this.productDesc = productDesc;
    }
    public void setproductWarranty(int productWarranty){
        this.productWarranty = productWarranty;
    }
    public static void setproductCount(int productCount){
        Product.productCount = productCount;
    }
    
    @Override   
    public String toString(){
        return productID + "," + productName + "," + productQuantity + "," + productBrand + "," + "RM" + productPrice + "," + productDesc + "," + productWarranty + "\n";
    }

    
}
