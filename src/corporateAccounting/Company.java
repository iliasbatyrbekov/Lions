package corporateAccounting;
import java.util.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.BeanWriterProcessor;

public class Company {
	private HashMap<String, CompanyAccount> accountList;
	private ArrayList<CompanyTransaction> journal;
	
	public Company() {
		accountList = CompanyAccount.getAllInitAccounts();
		journal = new ArrayList<CompanyTransaction>();
	}
	
	public HashMap<String, CompanyAccount> getAccountList() {
		return accountList;
	}
	public void setAccountList(HashMap<String, CompanyAccount> accountList) {
		this.accountList = accountList;
	}
	
	//dummy, will implement it later -> may use as a bug
	public boolean isValidTransaction(CompanyTransaction trans) {
		
		CompanyAccount account_to_debit=accountList.get(trans.getDebittedAccount());
		CompanyAccount account_to_credit=accountList.get(trans.getCredittedAccount());
		
		//for credit account type, if debit means decrease -> may have credit balance < 0
		if(account_to_debit.getAccountType()==CompanyAccountType.CREDIT_ACCOUNT) {
			if(account_to_debit.getBalance()-trans.getAmount()<0) {
				System.out.println("Error: Cannot debit account "+trans.getDebittedAccount());
				return false;
			}
		}
		
		//for debit account type, if credit means decrease -> may have insufficient fund
		if(account_to_credit.getAccountType()==CompanyAccountType.DEBIT_ACCOUNT) {
			if(account_to_credit.getBalance()-trans.getAmount()<0) {
				System.out.println("Error: Cannot credit account "+trans.getCredittedAccount());
				return false;
			}
		}
		
		return true;
	}
	
	public void recordTransaction(CompanyTransaction trans) { //use user cli now?
		if(isValidTransaction(trans)) {
			//record in journal
			journal.add(trans);
			
			//also update the accounts balance
			accountList.get(trans.getDebittedAccount()).recordTransaction(trans);
			accountList.get(trans.getCredittedAccount()).recordTransaction(trans);
		}
		else {
			System.out.println("Error: Cannot record this Transaction");
			//error message may be shown in isValidTransaction()
		}
	}
	public ArrayList<CompanyTransaction> getJournal() {
		return journal;
	}
	public void setJournal(ArrayList<CompanyTransaction> journal) {
		this.journal = journal;
	}
	
	public void printJournal() {
		if(journal.size()==0) {
			System.out.println("Transaction is empty");
		}
		for(int i=0; i<journal.size(); i++) {
			if(i == 0) {
				journal.get(i).printTransaction(true);
			}
			else {
				journal.get(i).printTransaction(false);
			}
		}
	}
	
	public void printBalanceSheet() {
		String seperationLine = "\n--------------------------------------------------------------+--------------------------------------------------------------+--------------------------------------------------------------|";
		String accountcategoryTitleFormatString = " %-60s | %-60s | %-60s |";
		String balanceRowFormat = " %-47s | %10s | %-47s | %10s | %-47s | %10s |";
		
		System.out.println('\n' + new String(new char[187]).replace("\0", "-") + "-");
		System.out.format(accountcategoryTitleFormatString, "ASSETS", "LIABILITY", "STOCK HOLDER'S EQUITY");
		//System.out.println('\n' + new String(new char[186]).replace("\0", "-"));
		System.out.println(seperationLine);
		//System.out.format(balanceRowFormat, "Accounts Payable", "1000.0", "Accounts Payable", "1000.0", "Accounts Payable", "1000.0");
		/*int assetCount = 0, liabilitycount = 0, seCount = 0;
		ArrayList<CompanyAccount> assetAccounts = new ArrayList<>();
		ArrayList<CompanyAccount> liabilityAccounts = new ArrayList<>();
		ArrayList<CompanyAccount> stockholdersEquityAccounts = new ArrayList<>();
		for (CompanyAccount a: accountList) {
			switch (a.getAccountCategory()) {
			case ASSET:
				assetCount++;
				assetAccounts.add(a);
				break;
			case LIABILITY:
				liabilitycount++;
				liabilityAccounts.add(a);
				break;
			case STOCKHOLDERS_EQUITY:
				seCount++;
				stockholdersEquityAccounts.add(a);
				break;
			default:
				break;
			} 
		}
		System.out.println(assetCount+liabilitycount+seCount);
		for (int i=0; i < Math.max(assetCount, Math.max(liabilitycount, seCount)); i++) {
			System.out.format(balanceRowFormat, 
					assetCount>0 ? assetAccounts.get(i).getAccountName() : "0", assetCount>0 ? assetAccounts.get(i).getBalance() : "0", 
					liabilitycount>0 ? liabilityAccounts.get(i).getAccountName() : "0", liabilitycount>0 ? liabilityAccounts.get(i).getBalance() : "0", 
					seCount>0 ? stockholdersEquityAccounts.get(i).getAccountName() : "0", seCount>0 ? stockholdersEquityAccounts.get(i).getBalance() : "0");
			assetCount--;
			liabilitycount--;
			seCount--;
			System.out.println();
		}*/
		
		for (int i=0; i < Math.max(CompanyAccount.assetAccountNames.length + CompanyAccount.contraAssetAccountNames.length, Math.max(CompanyAccount.liabilityAccountNames.length, CompanyAccount.stockHoldersEquityAccountNames.length + CompanyAccount.contrastockHoldersEquityAccountNames.length)); i++) {
			String assetAcc,assetBal,liabAcc,liabBal,seAcc,seBal;
			//Assets
			if(i<CompanyAccount.assetAccountNames.length) {
				assetAcc=accountList.get(CompanyAccount.assetAccountNames[i]).getAccountName();
				assetBal=String.valueOf(accountList.get(CompanyAccount.assetAccountNames[i]).getBalance());
			}
			else if(i<CompanyAccount.assetAccountNames.length + CompanyAccount.contraAssetAccountNames.length) {
				assetAcc=accountList.get(CompanyAccount.contraAssetAccountNames[i-CompanyAccount.assetAccountNames.length]).getAccountName();
				assetBal=String.valueOf(accountList.get(CompanyAccount.contraAssetAccountNames[i-CompanyAccount.assetAccountNames.length]).getBalance());
			}
			else {
				assetAcc="";
				assetBal="";
			}
			//Liabilities
			if(i<CompanyAccount.liabilityAccountNames.length) {
				liabAcc=accountList.get(CompanyAccount.liabilityAccountNames[i]).getAccountName();
				liabBal=String.valueOf(accountList.get(CompanyAccount.liabilityAccountNames[i]).getBalance());
			}
			else {
				liabAcc="";
				liabBal="";
			}
			//Stockholders' equity
			if(i<CompanyAccount.stockHoldersEquityAccountNames.length) {
				seAcc=accountList.get(CompanyAccount.stockHoldersEquityAccountNames[i]).getAccountName();
				seBal=String.valueOf(accountList.get(CompanyAccount.stockHoldersEquityAccountNames[i]).getBalance());
			}
			else if(i<CompanyAccount.stockHoldersEquityAccountNames.length + CompanyAccount.contrastockHoldersEquityAccountNames.length) {
				seAcc=accountList.get(CompanyAccount.contrastockHoldersEquityAccountNames[i-CompanyAccount.stockHoldersEquityAccountNames.length]).getAccountName();
				seBal=String.valueOf(accountList.get(CompanyAccount.contrastockHoldersEquityAccountNames[i-CompanyAccount.stockHoldersEquityAccountNames.length]).getBalance());
			}
			else {
				seAcc="";
				seBal="";
			}
			System.out.format(balanceRowFormat, assetAcc,assetBal,liabAcc,liabBal,seAcc,seBal);
			System.out.println(seperationLine);
		}
		
	}
	
	public double generateClosingEntries() {
		String retEarnAccName = "Retained Earnings";  // name of the account in which to record the closing entries
		for (String revAccName: CompanyAccount.revenueAccountNames) {
			double amount = accountList.get(revAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction("111", new Date(), revAccName, retEarnAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		for (String expAccName: CompanyAccount.expenseAccountNames) {
			double amount = accountList.get(expAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction("111", new Date(), retEarnAccName, expAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		for (String divAccName: CompanyAccount.dividendExpenseAccountNames) {
			double amount = accountList.get(divAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction("111", new Date(), retEarnAccName, divAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		for (String contraRevAccName: CompanyAccount.contraRevenueAccountNames) {
			double amount = accountList.get(contraRevAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction("111", new Date(), retEarnAccName, contraRevAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		return accountList.get("Retained Earnings").getBalance();
	}
	
	//for simple testing
	public static void main(String args[]) {
		
		Date tday = new Date();
		CompanyTransaction testTrans1=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable", 1000.0, "Borrow from bank");
		CompanyTransaction testTrans2=new CompanyTransaction("id12335", tday, "Inventory", "Cash", 1000.0, "Buy Inventory");
		CompanyTransaction testTrans3=new CompanyTransaction("id12336", tday, "Cash", "CommonStock", 1000.0, "Issue Common Stock");
		CompanyTransaction testTran4 = new CompanyTransaction("777", tday, "Cash", "Service Revenue", 300, "Provide soccer training");
		Company lukecompany=new Company();
		lukecompany.journal.add(testTrans1);
		lukecompany.journal.add(testTrans2);
		lukecompany.journal.add(testTrans3);
		//lukecompany.journal.add(testTran4);
		lukecompany.recordTransaction(testTran4);
		lukecompany.generateClosingEntries();
		lukecompany.printJournal();
		//lukecompany.printBalanceSheet();
		/*
		Company company = new Company();
		for (CompanyAccount ca: company.getAccountList()) {
			System.out.println(ca.getAccountName() + ": " + ca.getBalance());
		}
		*/
	}
	

}
