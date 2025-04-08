public class Mouse extends Product{
    private int weight;
    private boolean isWired;
    private int pollingRate;
    private String size;
    private static int mouseCount;
    public Mouse(){
        
    }
    public Mouse(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,int weight,boolean isWired,int pollingRate,String size){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.weight = weight;
        this.isWired = isWired;
        this.pollingRate = pollingRate;
        this.size = size;
        mouseCount++;
    }
    public int getWeight(){
        return weight;
    }
    public boolean getIsWired(){
        return isWired;
    }
    public int getPollingRate(){
        return pollingRate;
    }
    public String getSize(){
        return size;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setIsWired(boolean isWired){
        this.isWired = isWired;
    }
    public void setPollingRate(int pollingRate){
        this.pollingRate = pollingRate;
    }
    public void setSize(String size){
        this.size = size;
    }
    public static int getMouseCount(){
        return mouseCount;
    }
    public static void setMouseCount(int mouseCount){
        Mouse.mouseCount = mouseCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + weight + "," + isWired + "," + pollingRate + "," + size;
    }
}
