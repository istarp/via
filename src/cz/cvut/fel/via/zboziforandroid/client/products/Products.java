package cz.cvut.fel.via.zboziforandroid.client.products;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Products {
    private int id;
    private int parentId;
    private String productName;
    private String productNameExt;
    private String parentProductName;
    private String imgUrl;
    private String minVatPrice;
    private String maxVatPrice;
    private int premiseCount;
    private int itemCount;
    //variantcount  nevraci...
    private boolean hasPicture;
    private Pictures[] pictures;
    private Bitmap img;

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

	public String getParentProductName() {
		return parentProductName;
	}

	public void setParentProductName(String parentProductName) {
		this.parentProductName = parentProductName;
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

	public int getPremiseCount() {
		return premiseCount;
	}

	public void setPremiseCount(int premiseCount) {
		this.premiseCount = premiseCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public boolean isHasPicture() {
		return hasPicture;
	}

	public void setHasPicture(boolean hasPicture) {
		this.hasPicture = hasPicture;
	}

	public Pictures[] getPictures() {
		return pictures;
	}

	public void setPictures(Pictures[] pictures) {
		this.pictures = pictures;
	}
	
	public Bitmap getImg() {
		return img;
	}
	
	public void setImg(){		
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(imgUrl).openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();            
            img = BitmapFactory.decodeStream(is);            
       } catch (Exception e) {            
            img = null;
       }
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Products{" + "id=" + id + ", parentId=" + parentId + ", productName=" + productName + ", productNameExt=" + productNameExt + ", parentProductName=" + parentProductName + ", imgUrl=" + imgUrl + ", minVatPrice=" + minVatPrice + ", maxVatPrice=" + maxVatPrice + ", premiseCount=" + premiseCount + ", itemCount=" + itemCount + ", hasPicture=" + hasPicture );
        sb.append("\nPictures:\n");
        for (int i = 0; i < pictures.length; i++) {
            sb.append(pictures[i].toString()).append("\n");
        }
        return sb.toString();
    }
		
}
