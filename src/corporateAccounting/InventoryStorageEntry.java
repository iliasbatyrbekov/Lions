package corporateAccounting;

public class InventoryStorageEntry {

	private String transID;
	private double unitCost;
	private int units;
	
	public InventoryStorageEntry(String transID, double unitCost, int units) {
		this.setTransID(transID);
		this.setUnitCost(unitCost);
		this.setUnits(units);
	}
	
	public void decreaseUnits(int n) {
		units -= n;
	}

	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	
}
