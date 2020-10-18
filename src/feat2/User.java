package feat2;

import java.util.ArrayList;

import test.Account;

public class User {
	private String userID;
	private String password;
	private ArrayList<Transaction> transactionRecords;
	private ArrayList<Account> accountList;
	private View view;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Transaction> getTransactionRecords() {
		return transactionRecords;
	}
	public void setTransactionRecords(ArrayList<Transaction> transactionRecords) {
		this.transactionRecords = transactionRecords;
	}
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}
	
	
}
