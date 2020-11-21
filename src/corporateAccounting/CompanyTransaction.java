package corporateAccounting;

import java.util.*;
import java.text.*;

public class CompanyTransaction {

	private String transactionID;
	private Date date;
	private String debittedAccount; //use as a key to get the actual Account through the HashMap in Company
	private String credittedAccount;
	private double amount;
	private String description;

	public CompanyTransaction(String transID, Date dt, String debAcc, String creAcc, double amt, String desc) {
		transactionID=transID;
		date=dt;
		debittedAccount=debAcc;
		credittedAccount=creAcc;
		amount=amt;
		description=desc;
	}
		
	public String getDebittedAccount() {
		return debittedAccount;
	}
	
	public void setDebittedAccount(String debittedAccount) {
		this.debittedAccount=debittedAccount;
	}

	public String getCredittedAccount() {
		return credittedAccount;
	}

	public void setCredittedAccount(String credittedAccount) {
		this.credittedAccount=credittedAccount;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionID() {
		return this.transactionID;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public void printTransaction(Boolean printTablehead) {
		//print the journal entry for this transaction
		
		String tableFormat = "%-10s | %-15s | %-30s | %10s | %10s";
		String separationLine = "----------------------------------------------------------------------------------------\n";
		
		if(printTablehead) {
			System.out.print(separationLine);
		    System.out.printf(tableFormat, "ID", "DATE", "ACCOUNT NAME & DESCRIPTION", "DEBIT", "CREDIT");
		    System.out.print("\n");
		    System.out.print(separationLine);
		}
		
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	    String datestr = ft.format(date).toString();
	    
	    System.out.format(tableFormat,
                transactionID, datestr, debittedAccount, amount, "");
	    System.out.print("\n");
	    System.out.format(tableFormat,
                "", "", credittedAccount, "", amount);
	    System.out.print("\n");
	    System.out.format(tableFormat,
                "", "", "("+description+")", "", "");
	    System.out.print("\n");
	    System.out.print(separationLine);

	}
	
	//for simple testing
	public static void main(String args[]) {
		Date tday=new Date();
		CompanyTransaction testTrans=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable",1000.0, "Borrow from bank");
		testTrans.printTransaction(true);
		testTrans.printTransaction(false);
	}

}