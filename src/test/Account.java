package test;

public class Account {
	private String name;
	private double debt;
	private double asset;
	private double balance;
	private String password;
	
	public Account(String name, String password) {
		this.name = name;
		this.asset =  0;
		this.balance = 0;
		this.debt = 0;
		this.password = password;
	}
	
	public Account(String name, String password, double balance) {
		this.name = name;
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
	public String getName() {
		return name;
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
	public void setName(String name) {
		this.name = name;
	}
	
}
