package main;


public class SavingAccount extends Account{
	private double interest;
	private String withdrawDate;

	public SavingAccount(String accID, double balance, double interest, String withdrawDate) {
		super(accID, balance);
		this.interest = interest;
		this.withdrawDate = withdrawDate;
	}
	
	public String getWithdrawDate() {
		return withdrawDate;
	}
	public void setWithdrawDate(String withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
}
