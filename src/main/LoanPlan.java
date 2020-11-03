package main;

import java.util.ArrayList;
import java.util.Date;

public class LoanPlan extends Plan{
	private double loanAmount;
	private double currentAmount;
	private double interestRate;
	private String debtOwner;
	
	public LoanPlan(String planName, ArrayList<String> timePeriod,double loanAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.loanAmount = loanAmount;
		this.currentAmount = loanAmount;
		this.interestRate = interestRate;
		this.debtOwner = debtOwner;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public String getDebtOwner() {
		return debtOwner;
	}
	public void setDebtOwner(String debtOwner) {
		this.debtOwner = debtOwner;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	public double getMinimunPaymentAmount() {//Todo
		return loanAmount*interestRate;
	}

	public void updatePlan(double transactionAmount) {
		this.currentAmount -= transactionAmount;
	}

	public void displayPlan() {//Todo

	}
}
