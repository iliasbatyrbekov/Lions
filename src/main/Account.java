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
	
	public Account(String accID, double balance) {
		this.accID = accID;
		this.asset =  0;
		this.balance = balance;
		this.debt = 0;
	}

	
	public Account(String accID, String password, double balance, double asset, double debt) {
		this.accID = accID;
		this.asset =  asset;
		this.balance = balance;
		this.debt = debt;
		this.password = password;
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
