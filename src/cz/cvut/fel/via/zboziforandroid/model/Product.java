package cz.cvut.fel.via.zboziforandroid.model;

import java.util.List;
import java.util.Map;

import android.graphics.drawable.Drawable;

public class Product {
	
	private int id;
	private String productName;
	private String productNameExt;
	private int itemCount;
	private int sourceItemId;
	private int parentId;
	private boolean parentProductFlag;
	private String description;
	private int premiseCount;
	private int unfilteredPremiseCount;
	private int unfilteredItemCount;
	private String parentProductName;
	private int minPrice;
	private int maxPrice;
	private String imgUrl;
	private String minVatPrice;
	private String maxVatPrice;
	private String variantCount;
	private boolean hasPicture;
	private Drawable image;
	private List<Picture> pictures;
	private Map<Integer, Offer> offers;
	
	public Product(){};
	
	public Product(int id, String productName, String productNameExt, int itemCount, int sourceItemId, int parentId, boolean parentProductFlag, String description, int premiseCount, int unfilteredPremiseCount, int unfilteredItemCount, String parentProductName, int minPrice, int maxPrice, String imgUrl, String minVatPrice, String maxVatPrice, String variantCount, boolean hasPicture, Drawable image, List<Picture> pictures, Map<Integer, Offer> offers) {
		super();
		this.id = id;
		this.productName = productName;
		this.productNameExt = productNameExt;
		this.itemCount = itemCount;
		this.sourceItemId = sourceItemId;
		this.parentId = parentId;
		this.parentProductFlag = parentProductFlag;
		this.description = description;
		this.premiseCount = premiseCount;
		this.unfilteredPremiseCount = unfilteredPremiseCount;
		this.unfilteredItemCount = unfilteredItemCount;
		this.parentProductName = parentProductName;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.imgUrl = imgUrl;
		this.minVatPrice = minVatPrice;
		this.maxVatPrice = maxVatPrice;
		this.variantCount = variantCount;
		this.hasPicture = hasPicture;
		this.image = image;
		this.pictures = pictures;
		this.offers = offers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameExt() {
		return productNameExt;
	}

	public void setProductNameExt(String productNameExt) {
		this.productNameExt = productNameExt;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getSourceItemId() {
		return sourceItemId;
	}

	public void setSourceItemId(int sourceItemId) {
		this.sourceItemId = sourceItemId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public boolean isParentProductFlag() {
		return parentProductFlag;
	}

	public void setParentProductFlag(boolean parentProductFlag) {
		this.parentProductFlag = parentProductFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPremiseCount() {
		return premiseCount;
	}

	public void setPremiseCount(int premiseCount) {
		this.premiseCount = premiseCount;
	}

	public int getUnfilteredPremiseCount() {
		return unfilteredPremiseCount;
	}

	public void setUnfilteredPremiseCount(int unfilteredPremiseCount) {
		this.unfilteredPremiseCount = unfilteredPremiseCount;
	}

	public int getUnfilteredItemCount() {
		return unfilteredItemCount;
	}

	public void setUnfilteredItemCount(int unfilteredItemCount) {
		this.unfilteredItemCount = unfilteredItemCount;
	}

	public String getParentProductName() {
		return parentProductName;
	}

	public void setParentProductName(String parentProductName) {
		this.parentProductName = parentProductName;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMinVatPrice() {
		return minVatPrice;
	}

	public void setMinVatPrice(String minVatPrice) {
		this.minVatPrice = minVatPrice;
	}

	public String getMaxVatPrice() {
		return maxVatPrice;
	}

	public void setMaxVatPrice(String maxVatPrice) {
		this.maxVatPrice = maxVatPrice;
	}

	public String getVariantCount() {
		return variantCount;
	}

	public void setVariantCount(String variantCount) {
		this.variantCount = variantCount;
	}

	public boolean isHasPicture() {
		return hasPicture;
	}

	public void setHasPicture(boolean hasPicture) {
		this.hasPicture = hasPicture;
	}		

	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable image) {
		this.image = image;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	public Map<Integer, Offer> getOffers() {
		return offers;
	}

	public void setOffers(Map<Integer, Offer> offers) {
		this.offers = offers;
	}	

}
