package main;

public class Transaction {
	private double amount;
	private String type;
	private String accountID;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccount() {
		return accountID;
	}
	public void setAccount(String accID) {
		this.accountID = accID;
	}
	
	
}
