package corporateAccounting;
import java.util.*;

public class CompanyAccount {

	private String accountName; //no setter
	private CompanyAccountCategory accountCategory; //no setter
	private ArrayList<CompanyTransaction> transactionList;
	private double balance;
	private CompanyAccountType accountType; //no setter
	
	public static ArrayList<CompanyAccount> getAllInitAccounts() {
		ArrayList<CompanyAccount> allAccounts = new ArrayList<CompanyAccount>();
		
		//allAccounts.add(new CompanyAccount())
		
		return allAccounts;
	}
	
	private CompanyAccount(String accountName, CompanyAccountCategory accountCategory, CompanyAccountType accountType) {
		this.accountName = accountName;
		this.accountCategory = accountCategory;
		this.transactionList = new ArrayList<CompanyTransaction>();
		this.balance = 0;
		this.accountType = accountType;
		
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	private void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public CompanyAccountCategory getAccountCategory() {
		return this.accountCategory;
	}
	
	private void setAccountCategory(CompanyAccountCategory accountType) {
		this.accountCategory = accountType;
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
	
	public CompanyAccountType getAccountType() {
		return this.accountType;
	}
	
	private void setDebitOrCredit(CompanyAccountType accountType) {
		this.accountType = accountType;
	}

}
