package main;

public class TransferRemit extends Transaction {
	public TransferRemit(String transactionID) {
		super(transactionID);
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
