package cz.cvut.fel.via.zboziforandroid.model;

public class Store {
	
	private int depotPremiseId;
	private String depotRegion;
	private String locality;
	
	public Store(int depotPremiseId, String depotRegion, String locality) {
		super();
		this.depotPremiseId = depotPremiseId;
		this.depotRegion = depotRegion;
		this.locality = locality;
	}

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
	
	

}
