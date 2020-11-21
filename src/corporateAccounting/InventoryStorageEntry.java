package corporateAccounting;

public class InventoryStorageEntry {

	private String transID;
	private double unitCost;
	private int units;
	
	public InventoryStorageEntry(String transID, double unitCost, int units) {
		this.transID = transID;
		this.unitCost = unitCost;
		this.units = units;
	}
	
	public void decreaseUnits(int n) {
		if (n <= units)
			units -= n;
		else 
			System.out.print("The amount to decrease is larger than what is left. Action denied.\n");
	}

	public String getTransID() {
		return transID;
	}

	public int getUnits() {
		return units;
	}

	public double getUnitCost() {
		return unitCost;
	}
	
}
