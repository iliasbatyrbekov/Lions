package main;

import java.sql.Date;

public class Expense extends Transaction {
	public Expense(int transactionID, double amount, String accountID, String description) {
		super(transactionID, amount, accountID, description);
	}

	private String category;
	private String member;

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
}