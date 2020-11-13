package corporateAccounting;
import java.util.*;

public class Company {
	private ArrayList<CompanyAccount> accountList;
	private ArrayList<CompanyTransaction> journal;
	
	public Company() {
		accountList = CompanyAccount.getAllInitAccounts();
		journal = new ArrayList<CompanyTransaction>();
	}
	
	public ArrayList<CompanyAccount> getAccountList() {
		return accountList;
	}
	public void setAccountList(ArrayList<CompanyAccount> accountList) {
		this.accountList = accountList;
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
		int assetCount = 0, liabilitycount = 0, seCount = 0;
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
					seCount>0 ? stockholdersEquityAccounts.get(i).getAccountName() : "0", assetCount>0 ? stockholdersEquityAccounts.get(i).getBalance() : "0");
			assetCount--;
			liabilitycount--;
			seCount--;
			//System.out.println);
		}
	}
	
	//for simple testing
	public static void main(String args[]) {
		/*
		Date tday = new Date();
		CompanyTransaction testTrans1=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable",1000.0, "Borrow from bank");
		CompanyTransaction testTrans2=new CompanyTransaction("id12335", tday, "Inventory", "Cash",1000.0, "Buy Inventory");
		CompanyTransaction testTrans3=new CompanyTransaction("id12336", tday, "Cash", "CommonStock",1000.0, "Issue Common Stock");
		Company lukecompany=new Company();
		lukecompany.journal.add(testTrans1);
		lukecompany.journal.add(testTrans2);
		lukecompany.journal.add(testTrans3);*/
		System.out.println("???????");
		//lukecompany.printJournal();
		//lukecompany.printBalanceSheet();
		/*
		Company company = new Company();
		for (CompanyAccount ca: company.getAccountList()) {
			System.out.println(ca.getAccountName() + ": " + ca.getBalance());
		}
		*/
	}
	

}
