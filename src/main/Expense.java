package main;

public class Expense extends Transaction {
	private String category;
	private String member;

	public Expense(String transactionID, double amount, String accountID, String description) {
		super(transactionID, amount, accountID, description);
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getMember() {
		return this.member;
	}

	public void setMember(String member) {
		this.member = member;
	}
}
