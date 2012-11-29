package cz.cvut.fel.via.zboziforandroid.client.products;

public class PriceRangeResultAggregated {
    private int versionId;
    private PriceRanges[] priceRanges;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PriceRangeResultAggregated{" + "versionId=" + versionId);
        sb.append("\nPrice Ranges:\n");
        for (int i = 0; i < priceRanges.length; i++) {
            sb.append(priceRanges[i].toString()).append("\n");
        }
        
        return sb.toString();
    }

    class PriceRanges {
        
        private int minPrice;
        private int itemCount;
        private int maxPrice;

        @Override
        public String toString() {
            return "PriceRanges{" + "minPrice=" + minPrice + ", itemCount=" + itemCount + ", maxPrice=" + maxPrice + '}';
        }
    }
}
