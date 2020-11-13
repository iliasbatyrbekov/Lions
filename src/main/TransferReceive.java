package main;

import java.util.Date;

public class TransferReceive extends Transaction {
	public TransferReceive(int transactionID, double amount, String accountID, String description, Date date) {
		super(transactionID, amount, accountID, description, date);
	}

	private String member;
	private String receiveAccount;
	
	public String getReceiveAccount() {
		return this.receiveAccount;
	};
	public void setReceiveAccount(String accountId) {
		this.receiveAccount = accountId;
	};
	
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

}
