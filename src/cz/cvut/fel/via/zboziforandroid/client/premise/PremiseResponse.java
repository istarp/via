package cz.cvut.fel.via.zboziforandroid.client.premise;

public class PremiseResponse {
    private PremiseAttributes premiseAttributes;  
    private int status;
    private String statusMessage;

    public PremiseResponse(PremiseAttributes premiseAttributes, int status, String statusMessage) {
        this.premiseAttributes = premiseAttributes;
        this.status = status;
        this.statusMessage = statusMessage;
    }   
    
    public PremiseResponse() {        
    }

    public PremiseAttributes getPremiseAttributes() {
        return premiseAttributes;
    }

    public void setPremiseAttributes(PremiseAttributes premiseAttributes) {
        this.premiseAttributes = premiseAttributes;
    }

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

    @Override
    public String toString() {
        return "PremiseResponse{" + "premiseAttributes=" + premiseAttributes + ", status=" + status + ", statusMessage=" + statusMessage + '}';
    }
}
