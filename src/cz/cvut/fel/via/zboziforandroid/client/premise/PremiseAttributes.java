package cz.cvut.fel.via.zboziforandroid.client.premise;

public class PremiseAttributes {
    private EshopPremise eshopPremise;

    public EshopPremise getEshopPremise() {
        return eshopPremise;
    }

    public void setEshopPremise(EshopPremise eshopPremise) {
        this.eshopPremise = eshopPremise;
    }   

    @Override
    public String toString() {
        return "PremiseAttributes{" + "eshopPremise=" + eshopPremise +'}';
    }
    
}
