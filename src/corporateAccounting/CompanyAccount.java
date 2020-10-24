package corporateAccounting;
import java.util.*;

public class CompanyAccount {

	private String accountName;
	private String accountType;
	private ArrayList<CompanyTransaction> transactionList;
	private double balance;
	private boolean debitOrCredit;
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountType() {
		return this.accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public ArrayList<CompanyTransaction> getTransactionList() {
		return this.transactionList;
	}
	
	public void setTransactionList(ArrayList<CompanyTransaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void getAttribute() {
	// TODO - implement Account.getAttribute
		throw new UnsupportedOperationException();
	}
	
	public void setAttribute(int attribute) {
	// TODO - implement Account.setAttribute
		throw new UnsupportedOperationException();
	}
	
	public void printAccount() {
	// TODO - implement Account.printAccount
		throw new UnsupportedOperationException();
	}
	
	public boolean getDebitOrCredit() {
		return this.debitOrCredit;
	}
	
	public void setDebitOrCredit(boolean debitOrCredit) {
		this.debitOrCredit = debitOrCredit;
	}

}
