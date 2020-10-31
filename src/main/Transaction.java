package main;

import java.sql.Date;

public class Transaction {
	private String transactionID;
	private double amount;
	private Date date;
	private String accountID;
	private String description;

	public Transaction(String transactionID, double amount, String accountID, String description) {
		this.transactionID = transactionID;
		this.amount = amount;
		this.accountID = accountID;
		this.description = description;
	}

	public String getTransactionID() {
		return this.transactionID;
	}
	
	public String getDate() {
		return this.date.toString();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccount() {
		return this.accountID;
	}

	public void setAccount(String accID) {
		this.accountID = accID;
	}	
}
