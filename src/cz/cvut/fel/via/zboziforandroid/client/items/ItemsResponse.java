package cz.cvut.fel.via.zboziforandroid.client.items;

public class ItemsResponse {
    private int status;
    private String statusMessage;
    
    private String[] paymentTypesAggregated;
    private String[] availabilitiesAggregated;
    private String[] regionsAggregated;
    private String versionId;
    private int[] products;
    private int resultCount;    
    private Item[] items;

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

	public String[] getPaymentTypesAggregated() {
		return paymentTypesAggregated;
	}

	public void setPaymentTypesAggregated(String[] paymentTypesAggregated) {
		this.paymentTypesAggregated = paymentTypesAggregated;
	}

	public String[] getAvailabilitiesAggregated() {
		return availabilitiesAggregated;
	}

	public void setAvailabilitiesAggregated(String[] availabilitiesAggregated) {
		this.availabilitiesAggregated = availabilitiesAggregated;
	}

	public String[] getRegionsAggregated() {
		return regionsAggregated;
	}

	public void setRegionsAggregated(String[] regionsAggregated) {
		this.regionsAggregated = regionsAggregated;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public int[] getProducts() {
		return products;
	}

	public void setProducts(int[] products) {
		this.products = products;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ItemsResponse{" + "status=" + status + ", statusMessage=" + statusMessage + 
                ", versionId=" + versionId + ", resultCount=" + resultCount + "}\n");
        
        
        sb.append("Items:\n");
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]).append("\n");
        }
        
        sb.append("paymentTypesAggregated:\n");
        for (int i = 0; i < paymentTypesAggregated.length; i++) {
            sb.append(paymentTypesAggregated[i]).append(", ");
        }
        
        sb.append("\n").append("availabilitiesAggregated\n");
        for (int i = 0; i < availabilitiesAggregated.length; i++) {
            sb.append(availabilitiesAggregated[i]).append(", ");
        }
        sb.append("\n");
        
        return sb.toString();
    }
}