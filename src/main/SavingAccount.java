package main;

import java.util.Date;

public class SavingAccount extends Account{
	private double interest;
	private Date withdrawDate;

	public SavingAccount(String accID, String password, double balance, double interest, Date withdrawDate) {
		super(accID, password, balance);
		this.interest = interest;
		this.withdrawDate = withdrawDate;
	}
	
	public Date getWithdrawDate() {
		return withdrawDate;
	}
	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}
	
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
}
