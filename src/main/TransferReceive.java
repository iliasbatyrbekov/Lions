package main;

public class TransferReceive extends TransactionType {
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
	@Override
	public TransactionType getTransactionType() {
		return this;
	}

}
