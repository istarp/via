package cz.cvut.fel.via.zboziforandroid.client.product;

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
}