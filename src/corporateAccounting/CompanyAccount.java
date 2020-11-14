package corporateAccounting;
import java.text.SimpleDateFormat;
import java.util.*;

public class CompanyAccount {

	private String accountName; //no setter
	private CompanyAccountCategory accountCategory; //no setter
	private ArrayList<CompanyTransaction> transactionList;
	private double balance;
	private CompanyAccountType accountType; //no setter
	
	// returns an arraylist of company accounts for now. will change to a map later for effieicnty purposes
	public static ArrayList<CompanyAccount> getAllInitAccounts() {
		ArrayList<CompanyAccount> allAccounts = new ArrayList<CompanyAccount>();
		
		allAccounts.add(new CompanyAccount("Cash", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Petty Cash", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Accounts Receivable", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Allowance for Uncollectible Accounts", CompanyAccountCategory.ASSET, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Notes Receivable", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Interest Receivable", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Supplies", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Inventory", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Prepaid Advertising", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Prepaid Insurance", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Prepaid Rent", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Investments", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Land", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Land Improvements", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Buildings", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Equipment", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Accumulated Depreciation", CompanyAccountCategory.ASSET, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Lease Asset", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Natural Resources", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Patents", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Copyrights", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Trademarks", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Franchises", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Goodwill", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		
		allAccounts.add(new CompanyAccount("Accounts Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Notes Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Deferred Revenue", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Salaries Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Interest Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Utilities Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Dividends Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Income Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("FICA Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Unemployment Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Sales Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Contingent Liability", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Warranty Liability", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Lease Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Bonds Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));

		allAccounts.add(new CompanyAccount("Common Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Preferred Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("additional Paid-in Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Retained Earnings", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Treasury Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Unrealized Holding Gain--Other Comprehensive Income", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Unrealized Holding Loss--Other Comprehensive Income", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		
		allAccounts.add(new CompanyAccount("Advertising Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Amortization Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Bad Debt Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Cost of Goods Sold", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Delivery Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Depreciation Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Entertainment Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Income Tax Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Insurance Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Interest Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Legal Fees Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Payroll Tax Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Postage Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Property Tax Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Rent Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Repairs and Maintenance Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Research and Development Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Salaries Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Service Fee Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Supplies Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Utilities Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Warranty Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Loss", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Unrealized Holding Loss--Net Income", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		
		allAccounts.add(new CompanyAccount("Service Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Sales Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Sales Discounts", CompanyAccountCategory.REVENUE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Sales Returns", CompanyAccountCategory.REVENUE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Sales Allowances", CompanyAccountCategory.REVENUE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Interest Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Dividend Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Equity Income", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Gain", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Unrealized Holding Gain--Net Income", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));

		allAccounts.add(new CompanyAccount("Dividends (Cash)", CompanyAccountCategory.DIVIDEND, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.add(new CompanyAccount("Stock Dividends", CompanyAccountCategory.DIVIDEND, CompanyAccountType.DEBIT_ACCOUNT));
		
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
	
	private void setAccountType(CompanyAccountType accountType) {
		this.accountType = accountType;
	}
	
	public void recordTransaction(CompanyTransaction trans) {
		transactionList.add(trans);
		if(trans.getDebittedAccount().equals(accountName)) {
			if(accountType==CompanyAccountType.DEBIT_ACCOUNT) {
				balance+=trans.getAmount();
			}
			else {
				balance-=trans.getAmount();
			}
		}
		else if(trans.getCredittedAccount().equals(accountName)) {
			if(accountType==CompanyAccountType.DEBIT_ACCOUNT) {
				balance-=trans.getAmount();
			}
			else {
				balance+=trans.getAmount();
			}
		}
	}
	
	public void printTLedger() {
		String tableformat="%-10s | %-15s | %-30s | %10s | %10s";
		String separationline="----------------------------------------------------------------------------------------";
		
		System.out.format("%30s", accountName);
		System.out.println();
		System.out.println(separationline);
		
		System.out.format("%-30s | %-30s", "Debit", "Credit");
		System.out.println();
		System.out.println(separationline);
		
		System.out.format("%-15s|%-15s|%-15s|%-15s", "TransID", "Amount","TransID","Amount");
		System.out.println();
		System.out.println(separationline);
		
		//need to categorize transactions to debit and credit
		ArrayList<CompanyTransaction> debitList=new ArrayList<CompanyTransaction>();
		ArrayList<CompanyTransaction> creditList=new ArrayList<CompanyTransaction>();
		for(CompanyTransaction tran:transactionList) {
			if(tran.getDebittedAccount().equals(accountName)) {
				debitList.add(tran);
			}
			else if(tran.getCredittedAccount().equals(accountName)) {
				creditList.add(tran);
			}
		}
		
		int maxlen=(debitList.size()>creditList.size())?debitList.size():creditList.size();
		for(int i=0;i<maxlen;i++) {
			String debitTransId,debitTransAmount,creditTransId,creditTransAmount;
			if(i<debitList.size()) {
				debitTransId=debitList.get(i).getTransactionID();
				debitTransAmount=String.valueOf(debitList.get(i).getAmount());
			}
			else {
				debitTransId="";
				debitTransAmount="";
			}
			
			if(i<creditList.size()) {
				creditTransId=creditList.get(i).getTransactionID();
				creditTransAmount=String.valueOf(creditList.get(i).getAmount());
			}
			else {
				creditTransId="";
				creditTransAmount="";
			}
			System.out.format("%-15s|%-15s|%-15s|%-15s",debitTransId,debitTransAmount,creditTransId,creditTransAmount);
			System.out.println();
			System.out.println(separationline);
		}
		if(accountType==CompanyAccountType.DEBIT_ACCOUNT) {
			System.out.format("%-15s|%-15s|%-15s|%-15s","Total",balance,"","");
			System.out.println();
			System.out.println(separationline);
		}
		else {
			System.out.format("%-15s|%-15s|%-15s|%-15s","","","Total",balance);
			System.out.println();
			System.out.println(separationline);
		}
	}
	
	public static void main(String args[]) {
		Company lukecompany=new Company();
		CompanyAccount cashAccount=lukecompany.getAccountList().get(0);
		
		//add transaction to company and cashAccount -> will be done in recordTrans() in Company in the future
		Date tday=new Date();
		CompanyTransaction trans1=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable",1000.0, "Borrow from bank");
		CompanyTransaction trans2=new CompanyTransaction("id12335", tday, "Land", "Cash", 2000.0,"Buy land with cash");
		lukecompany.getJournal().add(trans1);
		lukecompany.getJournal().add(trans2);
		
		cashAccount.recordTransaction(trans1);
		cashAccount.recordTransaction(trans2);
		
		cashAccount.printTLedger();
		
	}

}
