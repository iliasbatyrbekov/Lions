package main; 
 
import java.sql.Date; 
 
public class Transaction { 
	private String transactionID; 
	private double amount; 
	private Date date;
	private String accountID; 
	private String description; 
 
	public Transaction(String transactionID, Date date, double amount, String accountID, String description) { 
		this.transactionID = transactionID;
		this.date = date;
		this.accountID = accountID;
		this.amount = amount;
		this.description = description;
	} 
 
	public String getTransactionID() { 
		return this.transactionID; 
	} 
	 
	public String getDate() { 
		return this.date.toString(); 
	}
 
	public String getDescription() { 
		return this.description; 
	}
	
	public double getAmount() { 
		return this.amount; 
	}
	
	public String getAccountId() { 
		return this.accountID; 
	}
}
