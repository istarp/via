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