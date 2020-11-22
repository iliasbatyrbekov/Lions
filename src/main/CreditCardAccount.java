package main;

public class CreditCardAccount extends Account {
	private double interest;
	private String paymentDate;

	public CreditCardAccount(String accID, double balance) {
		super(accID, balance);
	}
	public CreditCardAccount(String accID, double balance, double interest, String paymentDate) {
		super(accID, balance);
		this.interest = interest;
		this.setPaymentDate(paymentDate);
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}
