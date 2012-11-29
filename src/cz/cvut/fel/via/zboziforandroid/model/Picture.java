package cz.cvut.fel.via.zboziforandroid.model;

public class Picture {

	private String imgUrl;
	private String productImgUrl;
	private String fullSizedImgUrl;
	private int width;
	private int height;
	
	public Picture(String imgUrl, String productImgUrl, String fullSizedImgUrl, int width, int height) {
		super();
		this.imgUrl = imgUrl;
		this.productImgUrl = productImgUrl;
		this.fullSizedImgUrl = fullSizedImgUrl;
		this.width = width;
		this.height = height;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public String getFullSizedImgUrl() {
		return fullSizedImgUrl;
	}

	public void setFullSizedImgUrl(String fullSizedImgUrl) {
		this.fullSizedImgUrl = fullSizedImgUrl;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
