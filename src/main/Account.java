package main;

public class Account {
	private String accID;
	private double debt;
	private double asset;
	private double balance;
	
	public Account(String accID) {
		this.accID = accID;
		this.asset =  0;
		this.balance = 0;
		this.debt = 0;
	}
	
	public Account(String accID, double asset, double debt) {
		this.accID = accID;
		this.asset =  asset;
		this.debt = debt;
		this.balance = 0;
	}

	public double getAsset() {
		return this.asset;
	}
	public double getDebt() {
		return this.debt;
	}
	public double getBalance() {
		return this.balance;
	}
	public String getaccID() {
		return this.accID;
	}
}
