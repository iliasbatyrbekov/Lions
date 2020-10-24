package main; 
 
import java.util.Date;
import java.util.ArrayList; 
 
public class Transaction implements Comparable<Transaction>{ 
	private int transactionID; 
	private double amount; 
	private Date date;
	private String accountID; 
	private String description; 
 
	public Transaction(int transactionID, double amount, String accountID, String description) { 
		this.transactionID = transactionID;
		this.accountID = accountID;
		this.amount = amount;
		this.description = description;
		this.date = new Date();
	}
	
 
	public int getTransactionID() { return this.transactionID; } 
	public String getDate() { return this.date.toString(); }
	public String getDescription() { return this.description; }
	public double getAmount() { return this.amount; }
	public String getAccountId() { return this.accountID; }

	public static void listAll(ArrayList<Transaction> transactionRecords) {
		System.out.printf("%-30s %1.7f %-20s %-30s %-20s:", "Transaction ID", "Amount", "Account ID", "Description", "Create Date");
		for(Transaction trans : transactionRecords) {
			System.out.printf("%-30s %1.7f %-20s %-30s %-20s:",
					trans.transactionID, trans.amount, trans.accountID, trans.description, trans.getDate());
		}
	}

	public static Transaction searchTransaction(ArrayList<Transaction> transactionRecords, int transactionId) {
		for(Transaction trans : transactionRecords) {
			if(trans.getTransactionID() == transactionId)
				return trans;
		}
		return null;
	}


	@Override
	public int compareTo(Transaction trans) {
		return this.transactionID - trans.transactionID;
	}
	
}
