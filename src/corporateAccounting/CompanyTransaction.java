package corporateAccounting;

import java.util.*;
import java.text.*;

public class CompanyTransaction {

	private String transactionID;
	private Date date;
	private String debittedAccount;
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
		// TODO - implement Transaction.getDebittedAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param debittedAccount
	 */
	public void setDebittedAccount(String debittedAccount) {
		// TODO - implement Transaction.setDebittedAccount
		throw new UnsupportedOperationException();
	}

	public String getCredittedAccount() {
		// TODO - implement Transaction.getCredittedAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param credittedAccount
	 */
	public void setCredittedAccount(String credittedAccount) {
		// TODO - implement Transaction.setCredittedAccount
		throw new UnsupportedOperationException();
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionID() {
		return this.transactionID;
	}

	/**
	 * 
	 * @param transactionID
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public void printTransaction(Boolean printtablehead) {
		//print the journal entry for this transaction
		
		String tableformat="%-10s | %-15s | %-30s | %10s | %10s";
		String separationline="----------------------------------------------------------------------------------------";
		
		if(printtablehead) {
			System.out.println(separationline);
		    System.out.printf(tableformat, "ID", "DATE", "ACCOUNT NAME & DESCRIPTION", "DEBIT", "CREDIT");
		    System.out.println();
		    System.out.println(separationline);
		}
		
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	    String datestr = ft.format(date).toString();
	    
	    System.out.format(tableformat,
                transactionID, datestr, debittedAccount, amount, "");
	    System.out.println();
	    System.out.format(tableformat,
                "", "", credittedAccount, "", amount);
	    System.out.println();
	    System.out.format(tableformat,
                "", "", "("+description+")", "", "");
	    System.out.println();
	    System.out.println(separationline);

	}
	
	//for simple testing
	public static void main(String args[]) {
		Date tday=new Date();
		CompanyTransaction testTrans=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable",1000.0, "Borrow from bank");
		testTrans.printTransaction(true);
		testTrans.printTransaction(false);
	}

}