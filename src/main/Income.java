package main;

import java.util.Date;

public class Income extends Transaction {
	public Income(int transactionID, double amount, String accountID, String description, String date) {
		super(transactionID, amount, accountID, description, date);
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