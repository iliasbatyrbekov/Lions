package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

//TODO: Make User Singleton????

public class User {
	
	private ArrayList<Transaction> transactionRecords; 
	private ArrayList<Account> accountList; 
	private ArrayList<Plan> planList;
	private View view;
	
	private static User instance = new User();
	public static User getInstance() { return instance; }
	
	public User() {
		this.transactionRecords = new ArrayList<>();
		this.accountList = new ArrayList<>();
		this.planList = new ArrayList<>();
	}
	
	
	//TODO ADD ACCOUNT: Ilias
	
	//Plan
	public void addPlan() {
		
	}
	
	public void deletePlan() {
		
	}
	
	public void editPlan() {
		
	}
	
	public ArrayList<Plan> getPlanList(){
		return this.planList;
	}
	
	public Plan searchPlan(String id) {
		for (Plan p : this.planList) {
			if(p.getPlanID().equals(id)) {
				return p;
			}
		}
		return null;
	}
	
	
	// TransactionList
	public void listAllTransactionRecords() { Transaction.listAll(this.transactionRecords); }
	
	public Transaction findTransactionRecord(int transactionId) {
		return Transaction.searchTransaction(transactionRecords, transactionId);
	}
	
	public void addTransaction(String transType, Double amount, String accountId, int plainId, String description, Date date) {
		
		
		int transactionId = this.transactionRecords.get(this.transactionRecords.size()-1).getTransactionID();
		
		if(transType.equals("Expense")){
			Expense expenseTrans = new Expense(transactionId, amount, accountId, description, date);
			this.transactionRecords.add(expenseTrans);
		} else if (transType.equals("Income")) {
			Income incomeTrans = new Income(transactionId, amount, accountId, description, date);
			this.transactionRecords.add(incomeTrans);
		} else if (transType.equals("TransferReceive")) {
			TransferReceive transReceive = new TransferReceive(transactionId, amount, accountId, description, date);
			this.transactionRecords.add(transReceive);
		} else if (transType.equals("TransferRemit")) {
			TransferRemit transRemit = new TransferRemit(transactionId, amount, accountId, description, date);
			this.transactionRecords.add(transRemit);
		} else {
			System.out.printf("%s", "There is no ", transType, " type.");
		}
		
		Collections.sort(this.transactionRecords);
	}
 
	public ArrayList<Transaction> getTransactionList(String fromTime, String endTime) {
		//TODO: filter from date and to date
		return this.transactionRecords;
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