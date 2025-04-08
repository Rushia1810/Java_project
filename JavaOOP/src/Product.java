public class Product {
    private String productID;
    private String productName;
    private int productQuantity;
    private String productBrand;
    private double productPrice;
    private String productDesc;
    private int productWarranty;

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
    @Override   
    public String toString(){
        return productID + "," + productName + "," + productQuantity + "," + productBrand + "," + "RM" + productPrice + "," + productDesc + "," + productWarranty ;
    }
    

    
}
