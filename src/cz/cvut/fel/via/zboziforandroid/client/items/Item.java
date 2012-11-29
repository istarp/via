package cz.cvut.fel.via.zboziforandroid.client.items;

public class Item implements Comparable<Item>{
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

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(int premiseId) {
		this.premiseId = premiseId;
	}

	public String getPremiseName() {
		return premiseName;
	}

	public void setPremiseName(String premiseName) {
		this.premiseName = premiseName;
	}

	public Store[] getStores() {
		return stores;
	}

	public void setStores(Store[] stores) {
		this.stores = stores;
	}

	public Depot[] getDepots() {
		return depots;
	}

	public void setDepots(Depot[] depots) {
		this.depots = depots;
	}

	public PaymentTypes[] getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(PaymentTypes[] paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
	}

	public int getStockAvailability() {
		return stockAvailability;
	}

	public void setStockAvailability(int stockAvailability) {
		this.stockAvailability = stockAvailability;
	}

	public String getVatPrice() {
		return vatPrice;
	}

	public void setVatPrice(String vatPrice) {
		this.vatPrice = vatPrice;
	}

	public boolean isHasPicture() {
		return hasPicture;
	}

	public void setHasPicture(boolean hasPicture) {
		this.hasPicture = hasPicture;
	}

	public String getMinStockAvailability() {
		return minStockAvailability;
	}

	public void setMinStockAvailability(String minStockAvailability) {
		this.minStockAvailability = minStockAvailability;
	}

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

	@Override
	public int compareTo(Item another) {
		
		float a = Float.parseFloat(this.vatPrice);
		float b = Float.parseFloat(another.vatPrice);
		
		if (a == b)
			return 0;
		if (a > b)
			return 1;
		return -1;
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
