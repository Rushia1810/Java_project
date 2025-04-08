public class Monitor extends Product{
    private String resolution;
    private int refreshRate;
    private String panelType;
    private float responseTime;
    private static int monitorCount;

    public Monitor(){
        
    }
    public Monitor(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,String resolution,int refreshRate,String panelType,float responseTime){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.resolution = resolution;
        this.refreshRate = refreshRate;
        this.panelType = panelType;
        this.responseTime = responseTime;
        monitorCount++;
    }
    public String getResolution(){
        return resolution;
    }
    public int getRefreshRate(){
        return refreshRate;
    }
    public String getPanelType(){
        return panelType;
    }
    public float getResponseTime(){
        return responseTime;
    }
    public void setResolution(String resolution){
        this.resolution = resolution;
    }
    public void setRefreshRate(int refreshRate){
        this.refreshRate = refreshRate;
    }
    public void setPanelType(String panelType){
        this.panelType = panelType;
    }
    public void setResponseTime(float responseTime){
        this.responseTime = responseTime;
    }
    public static int getMonitorCount(){
        return monitorCount;
    }
    public static void setMonitorCount(int monitorCount){
        Monitor.monitorCount = monitorCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + resolution + "," + refreshRate + "," + panelType + "," + responseTime;
    }

}
