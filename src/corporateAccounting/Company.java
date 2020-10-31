package corporateAccounting;
import java.util.*;

public class Company {
	private ArrayList<CompanyAccount> accountList;
	private ArrayList<CompanyTransaction> journal;
	
	public Company() {
		accountList=new ArrayList<CompanyAccount>();
		journal=new ArrayList<CompanyTransaction>();
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
		for(int i=0;i<journal.size();i++) {
			if(i==0) {
				journal.get(i).printTransaction(true);
			}
			else {
				journal.get(i).printTransaction(false);
			}
		}
	}
	
	//for simple testing
	public static void main(String args[]) {
		Date tday=new Date();
		CompanyTransaction testTrans1=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable",1000.0, "Borrow from bank");
		CompanyTransaction testTrans2=new CompanyTransaction("id12335", tday, "Inventory", "Cash",1000.0, "Buy Inventory");
		CompanyTransaction testTrans3=new CompanyTransaction("id12336", tday, "Cash", "CommonStock",1000.0, "Issue Common Stock");
		Company lukecompany=new Company();
		lukecompany.journal.add(testTrans1);
		lukecompany.journal.add(testTrans2);
		lukecompany.journal.add(testTrans3);
		lukecompany.printJournal();
	}
	

}
