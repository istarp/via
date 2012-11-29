package cz.cvut.fel.via.zboziforandroid.client.items;

public class Item {
    private int id;
    private int productId;  
    private int premiseId;
    
    private String premiseName;
    private Store[] stores;
    private Depot[] depots;
    private PaymentTypes[] paymentTypes;    // lokalni trida
    
    private String URL;
    private String imgURL;
    private String premiseType;
    
    private int stockAvailability;
    private String vatPrice;
    private boolean hasPicture;
    private String minStockAvailability; 

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Item{" + "id=" + id + ", productId=" + productId + ", premiseId=" + premiseId +
                ", premiseName=" + premiseName + ", stores=" + stores + ", URL=" + URL + 
                ", imgURL=" + imgURL + ", premiseType=" + premiseType + ", depots=" + depots + 
                ", stockAvailability=" + stockAvailability + ", vatPrice=" + vatPrice + 
                ", hasPicture=" + hasPicture + ", minStockAvailability=" + minStockAvailability + '}');
        
        
        
        sb.append("\n depots: \n");
        for (int i = 0; i < depots.length; i++) {
            sb.append(depots[i]).append(", ");
        }
        sb.append("\n");
        
        return sb.toString();
    }
    
}
class PaymentTypes {
    private String paymentTypeId;

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Override
    public String toString() {
        return "PaymentTypes{" + "paymentTypeId=" + paymentTypeId + '}';
    }
    
}
