package main;

import java.util.ArrayList;
import java.util.Date;

public class LoanPlan extends Plan{
	
	public LoanPlan(String planName, ArrayList<Date> timePeriod,double loanAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.debtOwner = debtOwner;
		this.interestRate = interestRate;
		this.loanAmount = loanAmount;
	}
	private double loanAmount;
	private double interestRate;
	private String debtOwner;
	
	
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
	
	public double getMinimunPaymentAmount() {
		return loanAmount*interestRate;
	}


}
