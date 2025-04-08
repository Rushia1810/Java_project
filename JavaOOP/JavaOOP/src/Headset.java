public class Headset extends Product{
    private int Driver;
    private String surroundSound;
    private int impedance;
    private String micPick;
    private static int headsetCount;
    public Headset(){
        
    }
    public Headset(String productID,String productName,int productQuantity,String productBrand,double productPrice,String productDesc,int productWarranty,int Driver,String surroundSound,int impedance,String micPick){
        super(productID,productName,productQuantity,productBrand,productPrice,productDesc,productWarranty);
        this.Driver = Driver;
        this.surroundSound = surroundSound;
        this.impedance = impedance;
        this.micPick = micPick;
        headsetCount++;
    }
    public int getDriver(){
        return Driver;
    }
    public String getSurroundSound(){
        return surroundSound;
    }
    public int getImpedance(){
        return impedance;
    }
    public String getMicPick(){
        return micPick;
    }
    public void setDriver(int Driver){
        this.Driver = Driver;
    }
    public void setSurroundSound(String surroundSound){
        this.surroundSound = surroundSound;
    }
    public void setImpedance(int impedance){
        this.impedance = impedance;
    }
    public void setMicPick(String micPick){
        this.micPick = micPick;
    }
    public static int getHeadsetCount(){
        return headsetCount;
    }
    public static void setHeadsetCount(int headsetCount){
        Headset.headsetCount = headsetCount;
    }
    @Override
    public String toString(){
        return super.toString() + "," + Driver + "," + surroundSound + "," + impedance + "," + micPick;
    }
}
