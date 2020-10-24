package main;

public class Expense extends TransactionType {
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

	@Override
	public TransactionType getTransactionType() {
		return this;
	}
}