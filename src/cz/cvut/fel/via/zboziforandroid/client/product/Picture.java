package cz.cvut.fel.via.zboziforandroid.client.product;

public class Picture {
    private String imgUrl; //Povinne ID maleho obrazku.
    private String productImgUrl; //Nepovinne ID produktoveho obrazku.
    private String fullSizedImgUrl; //Nepovinne ID velkeho obrazku.
    private int width; //Sirka velkeho obrazku
    private int height; //Vyska velkeho obrazku

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

    @Override
    public String toString() {
        return "Picture{" + "imgUrl=" + imgUrl + ", productImgUrl=" + productImgUrl + ", fullSizedImgUrl=" + fullSizedImgUrl + ", width=" + width + ", height=" + height + '}';
    }
    
}