package main;

public class Expense extends Transaction {
	private String category;
	private String member;
	
	public Expense(int transactionID, double amount, String accountID, String description, String date, String category) {
		super(transactionID, amount, accountID, description, date);
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
}