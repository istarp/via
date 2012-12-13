package cz.cvut.fel.via.zboziforandroid.client.items;

class Depot {
    private int depotPremiseId;
    private String depotRegion;
    private String locality;

    public int getDepotPremiseId() {
        return depotPremiseId;
    }

    public void setDepotPremiseId(int depotPremiseId) {
        this.depotPremiseId = depotPremiseId;
    }

    public String getDepotRegion() {
        return depotRegion;
    }

    public void setDepotRegion(String depotRegion) {
        this.depotRegion = depotRegion;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        return "Depot{" + "depotPremiseId=" + depotPremiseId + ", depotRegion=" + depotRegion + ", locality=" + locality + '}';
    }
    
}
