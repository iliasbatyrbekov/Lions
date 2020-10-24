package main;

import java.sql.Date;

public class TransferRemit extends Transaction {
	public TransferRemit(int transactionID, double amount, String accountID, String description) {
		super(transactionID, amount, accountID, description);
	}

	private String member;
	private String remitAccount;
	
	public String getAccount() {
		return this.remitAccount;
	};
	public void setAccount(String accountId) {
		this.remitAccount = accountId;
	};
	
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

}
