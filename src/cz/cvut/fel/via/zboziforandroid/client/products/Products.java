package cz.cvut.fel.via.zboziforandroid.client.products;

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
