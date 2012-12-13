package cz.cvut.fel.via.zboziforandroid.client.products;

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
      
   public int getStatus() {
	   return status;
   }
   
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	
	public PriceRangeResultAggregated getPriceRangeResultAggregated() {
		return priceRangeResultAggregated;
	}

	public void setPriceRangeResultAggregated(
			PriceRangeResultAggregated priceRangeResultAggregated) {
		this.priceRangeResultAggregated = priceRangeResultAggregated;
	}
	
	public Products[] getProducts() {
		return products;
	}
	
	public void setProducts(Products[] products) {
		this.products = products;
	}
	
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