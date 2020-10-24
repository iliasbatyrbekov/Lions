package main;

import java.sql.Date;

public class TransferReceive extends Transaction {
	public TransferReceive(String transactionID, Date date, double amount, String accountID, String description) {
		super(transactionID, date, amount, accountID, description);
	}

	private String member;
	private String receiveAccount;
	
	public String getAccount() {
		return this.receiveAccount;
	};
	public void setAccount(String accountId) {
		this.receiveAccount = accountId;
	};
	
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

}
