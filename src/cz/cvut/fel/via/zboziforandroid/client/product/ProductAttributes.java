package cz.cvut.fel.via.zboziforandroid.client.product;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ProductAttributes {

    private int id;
    private int parentId;
    private int premiseCount;
    private String minPrice;
    private String maxPrice;
    private String description;
    private Picture[] pictures;
    private int unfilteredPremiseCount;
    private int unfilteredItemCount;
    private String productName;
    private String productNameExt;
    private boolean parentProductFlag;
    private int itemCount;
    private Bitmap smallImage;
    private Bitmap bigImage;
    
    
//    private boolean hasPicture;
//    private int sourceItemId;
//    private String parentProductName;

    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ProductAttributes{" + "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice + ", id=" + id + ", productName=" + productName +
                ", productNameExt=" + productNameExt + ", itemCount=" + itemCount + 
                ", parentId=" + parentId + ", parentProductFlag=" + parentProductFlag +
                ", description=" + description + ", premiseCount=" + premiseCount + 
                ", unfilteredPremiseCount=" + unfilteredPremiseCount +
                ", unfilteredItemCount=" + unfilteredItemCount + '\n');

        for (int i = 0; i < pictures.length; i++) {
            sb.append(pictures[i].toString()).append("\n");
        }




        return sb.toString();
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public int getPremiseCount() {
		return premiseCount;
	}


	public void setPremiseCount(int premiseCount) {
		this.premiseCount = premiseCount;
	}


	public String getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}


	public String getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Picture[] getPictures() {
		return pictures;
	}


	public void setPictures(Picture[] pictures) {
		this.pictures = pictures;
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


	public boolean isParentProductFlag() {
		return parentProductFlag;
	}


	public void setParentProductFlag(boolean parentProductFlag) {
		this.parentProductFlag = parentProductFlag;
	}


	public int getItemCount() {
		return itemCount;
	}


	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}


	public Bitmap getSmallImage() {
		return smallImage;
	}


	public void setSmallImage() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(pictures[0].getImgUrl()).openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();            
            smallImage = BitmapFactory.decodeStream(is);            
       } catch (Exception e) {            
            smallImage = null;
       }
	}


	public Bitmap getBigImage() {
		return bigImage;
	}


	public void setBigImage() {
        try {
        	String url = "";
        	for (Picture p : pictures){
        		if (p.getFullSizedImgUrl() != null && p.getFullSizedImgUrl().length() > 0){
        			url = p.getFullSizedImgUrl();
        			break;
        		}
        	}
        	if (!url.equals("")){
	            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	            conn.setDoInput(true);
	            conn.connect();
	            InputStream is = conn.getInputStream();            
	            bigImage = BitmapFactory.decodeStream(is);
        	}else{
        		bigImage = null;
        	}
       } catch (Exception e) {            
            bigImage = null;
       }
	}
}