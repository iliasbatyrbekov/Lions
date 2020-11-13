package main;

import java.util.Date;

public class TransferRemit extends Transaction {
	public TransferRemit(int transactionID, double amount, String accountID, String description, Date date) {
		super(transactionID, amount, accountID, description, date);
	}

	private String member;
	private String remitAccount;
	
	public String getRemitAccount() {
		return this.remitAccount;
	};
	public void setRemitAccount(String accountId) {
		this.remitAccount = accountId;
	};
	
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

}
