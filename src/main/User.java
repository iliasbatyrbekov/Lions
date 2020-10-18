package main;

import java.util.ArrayList;

public class User {
	private String userID;
	private String password;
	private int transactionIDGenerator;
	private ArrayList<Transaction> transactionRecords;
	private ArrayList<Account> accountList;
	private ArrayList<Plan> planList;
	private View view;
	
	public User(String userID, String password) {
		this.userID = userID;
		this.password = password;
		this.transactionIDGenerator = 1;
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
	//*** View class already have transactionRecords ***
	// public ArrayList<Transaction> getTransactionRecords() {
	// 	return transactionRecords;
	// }
	// public void setTransactionRecords(ArrayList<Transaction> transactionRecords) {
	// 	this.transactionRecords = transactionRecords;
	// }

	public void addTransaction() {
		String transactionID = Integer.toString(this.transactionIDGenerator);
		this.transactionIDGenerator += 1;

		Transaction transaction = new Transaction(transactionID);
		this.transactionRecords.add(transaction);
	}

	public void editTransaction(String transactionID) {
		Transaction target = null;

		for (Transaction transaction : this.transactionRecords) {
			if(transaction.getTransactionID().equals(transactionID)) {
				target = transaction;
			}
		}

		if (target == null) { //throw Exception
			System.out.println("Transaction Not Found");
		}
		else {
			//To be continued
			//target.setDate(...)
			//target.setAmount(...)
			//target.setType(...)
			//target.setAccount(...)			
		}
	}

	public ArrayList<Account> getAccountList() {
		return this.accountList;
	}
	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
	
	
}
