package cz.cvut.fel.via.zboziforandroid.client;

/**
*
* @author Milanec
*/
public class ProductsResponse {
   private int status;
   private String statusMessage;
   private int resultCount;
   private String versionId;
   private PriceRangeResultAggregated priceRangeResultAggregated;
   private Products[] products;

   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder( "ProductsResponse{" + "status=" + status + ", statusMessage=" + statusMessage + ", resultCount=" + resultCount + ", verionId=" + versionId + ", priceRangeResultAggregated=" + priceRangeResultAggregated);
       sb.append("\nProducts:\n");
       for (int i = 0; i < products.length; i++) {
           sb.append(products[i].toString()).append("\n");
       }
       return sb.toString();
   }
}