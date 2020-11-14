package corporateAccounting;
import java.text.SimpleDateFormat;
import java.util.*;

public class CompanyAccount {

	private String accountName; //no setter
	private CompanyAccountCategory accountCategory; //no setter
	private ArrayList<CompanyTransaction> transactionList;
	private double balance;
	private CompanyAccountType accountType; //no setter
	
	public static final String[] assetAccountNames = { "Cash", "Petty Cash", "Accounts Receivable", "Notes Receivable", "Supplies", "Inventory", "Prepaid Advertising", "Prepaid Insurance", "Prepaid Rent", "Investments", "Land", "Land Improvements", "Buildings", "Equipment", "Lease Asset", "Natural Resources", "Patents", "Copyrights", "Trademarks", "Franchises", "Goodwill" };
	
	public static final String[] contraAssetAccountNames = { "Allowance for Uncollectible Accounts", "Accumulated Depreciation" };
	
	public static final String[] liabilityAccountNames = { "Accounts Payable", "Notes Payable", "Deferred Revenue", "Salaries Payable", "Interest Payable", "Utilities Payable", "Dividends Payable", "Income Tax Payable", "FICA Tax Payable", "Unemployment Tax Payable", "Sales Tax Payable", "Contingent Liability", "Warranty Liability", "Lease Payable", "Bonds Payable" };
	
	public static final String[] stockHoldersEquityAccountNames = { "Common Stock", "Preferred Stock", "Additional Paid-in Stock", "Retained Earnings", "Unrealized Holding Gain--Other Comprehensive Income", "Unrealized Holding Loss--Other Comprehensive Income" };
	
	public static final String[] contrastockHoldersEquityAccountNames = { "Treasury Stock" };
	
	public static final String[] revenueAccountNames = { "Advertising Expense", "Amortization Expense", "Bad Debt Expense", "Cost of Goods Sold", "Delivery Expense", "Depreciation Expense", "Entertainment Expense", "Income Tax Expense", "Insurance Expense", "Interest Expense", "Legal Fees Expense", "Payroll Tax Expense", "Postage Expense", "Property Tax Expense", "Rent Expense", "Repairs and Maintenance Expense", "Research and Development Expense", "Salaries Expense", "Service Fee Expense","Supplies Expense", "Utilities Expense", "Warranty Expense", "Loss", "Unrealized Holding Loss--Net Income" };
	
	public static final String[] expenseAccountNames = { "Service Revenue", "Sales Revenue", "Interest Revenue", "Dividend Revenue", "Equity Income", "Gain", "Unrealized Holding Gain--Net Income" };
	
	public static final String[] contraExpenseAccountNames = { "Sales Discounts", "Sales Returns", "Sales Allowances" };
	
	public static final String[] dividendExpenseAccountNames = { "Dividends (Cash)", "Stock Dividends" };
	
	public static String[] getDebitAssetAccountNames() {
		return assetAccountNames;
	}
	
	// returns an arraylist of company accounts for now. will change to a map later for effieicnty purposes
	public static HashMap<String, CompanyAccount> getAllInitAccounts() {
		HashMap<String, CompanyAccount> allAccounts = new HashMap<>();
		
		
		for (String accName: assetAccountNames) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		}
		for (String accName: contraAssetAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.ASSET, CompanyAccountType.CREDIT_ACCOUNT));
		}
		for (String accName: liabilityAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		}
		for (String accName: stockHoldersEquityAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		}
		for (String accName: contrastockHoldersEquityAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.DEBIT_ACCOUNT));
		}
		for (String accName: revenueAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		}
		for (String accName: expenseAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		}
		for (String accName: contraExpenseAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.EXPENSE, CompanyAccountType.CREDIT_ACCOUNT));
		}
		for (String accName: dividendExpenseAccountNames ) {
			allAccounts.put(accName, new CompanyAccount(accName, CompanyAccountCategory.DIVIDEND, CompanyAccountType.CREDIT_ACCOUNT));
		}
		
		/*
		allAccounts.put(new CompanyAccount("Cash", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Petty Cash", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Accounts Receivable", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		//allAccounts.put(new CompanyAccount("Allowance for Uncollectible Accounts", CompanyAccountCategory.ASSET, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Notes Receivable", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Interest Receivable", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Supplies", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Inventory", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Prepaid Advertising", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Prepaid Insurance", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Prepaid Rent", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Investments", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Land", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Land Improvements", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Buildings", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Equipment", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		//allAccounts.put(new CompanyAccount("Accumulated Depreciation", CompanyAccountCategory.ASSET, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Lease Asset", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Natural Resources", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Patents", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Copyrights", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Trademarks", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Franchises", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Goodwill", CompanyAccountCategory.ASSET, CompanyAccountType.DEBIT_ACCOUNT));
		
		
		allAccounts.put(new CompanyAccount("Accounts Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Notes Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Deferred Revenue", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Salaries Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Interest Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Utilities Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Dividends Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Income Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("FICA Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Unemployment Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Sales Tax Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Contingent Liability", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Warranty Liability", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Lease Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Bonds Payable", CompanyAccountCategory.LIABILITY, CompanyAccountType.CREDIT_ACCOUNT));
		
		
		allAccounts.put(new CompanyAccount("Common Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Preferred Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Additional Paid-in Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Retained Earnings", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Treasury Stock", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Unrealized Holding Gain--Other Comprehensive Income", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Unrealized Holding Loss--Other Comprehensive Income", CompanyAccountCategory.STOCKHOLDERS_EQUITY, CompanyAccountType.CREDIT_ACCOUNT));
		
		allAccounts.put(new CompanyAccount("Advertising Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Amortization Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Bad Debt Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Cost of Goods Sold", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Delivery Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Depreciation Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Entertainment Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Income Tax Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Insurance Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Interest Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Legal Fees Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Payroll Tax Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Postage Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Property Tax Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Rent Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Repairs and Maintenance Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Research and Development Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Salaries Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Service Fee Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Supplies Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Utilities Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Warranty Expense", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Loss", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Unrealized Holding Loss--Net Income", CompanyAccountCategory.EXPENSE, CompanyAccountType.DEBIT_ACCOUNT));
		
		allAccounts.put(new CompanyAccount("Service Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Sales Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Sales Discounts", CompanyAccountCategory.REVENUE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Sales Returns", CompanyAccountCategory.REVENUE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Sales Allowances", CompanyAccountCategory.REVENUE, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Interest Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Dividend Revenue", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Equity Income", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Gain", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Unrealized Holding Gain--Net Income", CompanyAccountCategory.REVENUE, CompanyAccountType.CREDIT_ACCOUNT));

		allAccounts.put(new CompanyAccount("Dividends (Cash)", CompanyAccountCategory.DIVIDEND, CompanyAccountType.DEBIT_ACCOUNT));
		allAccounts.put(new CompanyAccount("Stock Dividends", CompanyAccountCategory.DIVIDEND, CompanyAccountType.DEBIT_ACCOUNT));
		*/
		
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
		String separationline="--------------------------------------------------------------";
		
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
		CompanyAccount cashAccount=lukecompany.getAccountList().get("Cash");
		
		//put transaction to company and cashAccount -> will be done in recordTrans() in Company in the future
		Date tday=new Date();
		CompanyTransaction trans1=new CompanyTransaction("id12334", tday, "Cash", "Accounts Payable",1000.0, "Borrow from bank");
		CompanyTransaction trans2=new CompanyTransaction("id12335", tday, "Land", "Cash", 2000.0,"Buy land with cash");
		lukecompany.recordTransaction(trans1);
		lukecompany.recordTransaction(trans2);
		
		cashAccount.printTLedger();
		
	}

}
