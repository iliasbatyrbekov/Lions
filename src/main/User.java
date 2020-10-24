package main;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;

public class User { 
	private String userID;
	private String password;
	private ArrayList<Transaction> transactionRecords; 
	private ArrayList<Account> accountList; 
	private ArrayList<Plan> planList; 
	private View view;
	
	public User(String userID, String password) { 
		this.userID = userID; 
		this.password = password;
		this.transactionRecords = new ArrayList<>();
		this.accountList = new ArrayList<>();
		this.planList = new ArrayList<>();
	} 
 
	public void changePassword(String password) { 
		// pretend having done a series of complex verification, e.g. One Time Password, email confirmation 
		this.password = password; 
	} 
 
	// *** userID and password as confidential data should not have getter and setter for public modification *** 
	// public String getUserID() { 
	// 	return userID; 
	// } 
	// public void setUserID(String userID) { 
	// 	this.userID = userID; 
	// } 
	// public String getPassword() { 
	// 	return password; 
	// } 
	// public void setPassword(String password) { 
	// 	this.password = password; 
	// }
	
	
	// TransactionList
	public void listAllTransactionRecords() { Transaction.listAll(this.transactionRecords); }
	
	public Transaction findTransactionRecord(int transactionId) {
		return Transaction.searchTransaction(transactionRecords, transactionId);
	}
	
	public void addTransaction(Double amount, String accountId, String description) {
		int transactionId = this.transactionRecords.get(this.transactionRecords.size()-1).getTransactionID();
		Transaction trans = new Transaction(transactionId, amount, accountId, description);
		this.transactionRecords.add(trans);
		Collections.sort(this.transactionRecords);
	}
 
	public void editTransaction(int transactionId) { 
		Transaction trans = this.findTransactionRecord(transactionId);
		if(trans != null) {
			
		}
	}
	
	public void deleteTransaction(int transactionId) {
		Transaction trans = this.findTransactionRecord(transactionId);
		if(trans != null) {
			this.transactionRecords.remove(trans);
		}
	}
	
	
 
	public ArrayList<Account> getAccountList() { 
		return this.accountList; 
	} 
	public void setAccountList(ArrayList<Account> accountList) { 
		this.accountList = accountList; 
	}
}