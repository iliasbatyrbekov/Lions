package main;

public class Account {
	private String accID;
	private double debt;
	private double asset;
	private double balance;
	private String password;
	
	public Account(String accID, String password) {
		this.accID = accID;
		this.asset =  0;
		this.balance = 0;
		this.debt = 0;
		this.password = password;
	}
	
	public Account(String accID, String password, double balance) {
		this.accID = accID;
		this.asset =  0;
		this.balance = balance;
		this.debt = 0;
		this.password = password;
	}
	
	public double getAsset() {
		return asset;
	}
	public double getDebt() {
		return debt;
	}
	public double getBalance() {
		return balance;
	}
	public String getaccID() {
		return accID;
	}
	
	public double setAsset(double asset, String password) {
		if(this.password.equals(password)) {
			this.balance = asset;
			return asset;
		}
		return -2.0;
	}
	public double setDebt(double asset, String password) {
		if(this.password.equals(password)) {
			this.asset = asset;
			return asset;
		}
		return -2.0;
	}
	public double setBalance(double balance, String password) {
		if(this.password.equals(password)) {
			this.asset = balance;
			return balance;
		}
		return -2.0;
	}
	public void setaccID(String accID) {
		this.accID = accID;
	}
	
}
