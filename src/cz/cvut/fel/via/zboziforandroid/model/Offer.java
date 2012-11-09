package cz.cvut.fel.via.zboziforandroid.model;

import java.util.ArrayList;
import java.util.List;

public class Offer implements Comparable<Offer>{
	
	private int id;
	private int premiseId;
	private int productId;
	private String premiseName;
	private String premiseType;
	private String URL;
	private String imgURL;
	private String vatPrice ;
	private int hasPicture;
	private int stockAvailability;
	private String minStockAvailability;
	private List<Depot> depots;
	private List<Store> stores;
	private List<PaymentType> paymentTypes;	
	
	public Offer(){}
	
	public Offer(int id, String premiseName, String vatPrice, int stockAvailability) {
		super();
		this.id = id;
		this.premiseName = premiseName;
		this.vatPrice = vatPrice;
		this.stockAvailability = stockAvailability;
	}
	
	public Offer(int id, int premiseId, int productId, String premiseName, String premiseType, String uRL, String imgURL, String vatPrice, int hasPicture, int stockAvailability, String minStockAvailability, ArrayList<Depot> depots, ArrayList<Store> stores, ArrayList<PaymentType> paymentTypes) {
		super();
		this.id = id;
		this.premiseId = premiseId;
		this.productId = productId;
		this.premiseName = premiseName;
		this.premiseType = premiseType;
		URL = uRL;
		this.imgURL = imgURL;
		this.vatPrice = vatPrice;
		this.hasPicture = hasPicture;
		this.stockAvailability = stockAvailability;
		this.minStockAvailability = minStockAvailability;
		this.depots = depots;
		this.stores = stores;
		this.paymentTypes = paymentTypes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(int premiseId) {
		this.premiseId = premiseId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getPremiseName() {
		return premiseName;
	}

	public void setPremiseName(String premiseName) {
		this.premiseName = premiseName;
	}

	public String getPremiseType() {
		return premiseType;
	}

	public void setPremiseType(String premiseType) {
		this.premiseType = premiseType;
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

	public String getVatPrice() {
		return vatPrice;
	}

	public void setVatPrice(String vatPrice) {
		this.vatPrice = vatPrice;
	}

	public int getHasPicture() {
		return hasPicture;
	}

	public void setHasPicture(int hasPicture) {
		this.hasPicture = hasPicture;
	}

	public int getStockAvailability() {
		return stockAvailability;
	}

	public void setStockAvailability(int stockAvailability) {
		this.stockAvailability = stockAvailability;
	}

	public String getMinStockAvailability() {
		return minStockAvailability;
	}

	public void setMinStockAvailability(String minStockAvailability) {
		this.minStockAvailability = minStockAvailability;
	}

	public List<Depot> getDepots() {
		return depots;
	}

	public void setDepots(ArrayList<Depot> depots) {
		this.depots = depots;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(ArrayList<Store> stores) {
		this.stores = stores;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(ArrayList<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	@Override
	public int compareTo(Offer another) {
		
		int a = Integer.parseInt(this.vatPrice);
		int b = Integer.parseInt(another.vatPrice);
		
		if (a == b)
			return 0;
		if (a > b)
			return 1;
		return -1;
	}
	
}
