package cz.cvut.fel.via.zboziforandroid.client.product;

public class ProductResponse {
    private int status;
    private String statusMessage;
    private ProductAttributes productAttributes;

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

    public ProductAttributes getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(ProductAttributes productAttributes) {
        this.productAttributes = productAttributes;
    }

    @Override
    public String toString() {
        return "ProductResponse{" + "status=" + status + ", statusMessage=" + statusMessage + ", productAttributes=" + productAttributes + '}';
    }
}