package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


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
	
	//Account
	public ArrayList<Account> getAccountList() { 
		return this.accountList; 
	}
	
	public void listAllAccounts() {
		Account.listAll(this.accountList);
	}
	
	public void addAccount(String accountType,
						String accID, double balance, 
						double asset, double debt, 
						double interest, String withdrawDate)
						throws ExAccountTypeNotSupported
	{
		Account account = new Account(accID,balance,asset,debt);
		switch (accountType) {
		case"Cash":
			account = new CashAccount(accID, balance);
			break;
		case"Saving":
			account = new SavingAccount(accID, balance, interest, withdrawDate);
			break;
		case"Credit":
			account = new CreditCardAccuont(accID, balance);
			break;
		case"Debit":
			account = new DebitAccount(accID, balance);
			break;
		default:
			System.out.printf("%s", "There is no ", accountType, " type.");
			throw new ExAccountTypeNotSupported();
		}
		accountList.add(account);
	}
	
	public void deleteAccount(String accId) throws ExAccountNotExist {
		Account acc = this.searchAccount(accId);
		if(acc != null) this.accountList.remove(acc);
		else throw new ExAccountNotExist();
	}
	
	public Account searchAccount(String accId) {
		for (Account a : this.accountList) {
			if(a.getaccID().equals(accId)) return a;
		}
		return null;
	}
	
	
	
	//Plan
	public void listAllPlan() { Plan.listAll(this.planList); }
	
	public void addPlan(String planName, String startDate, String endDate) {
		ArrayList<String> timePeriodString = new ArrayList<>();
		timePeriodString.add(startDate);
		timePeriodString.add(endDate);
		Plan plan = new Plan(planName, timePeriodString);
	}
	
	public void deletePlan(String planId) throws ExPlainNotExist {
		Plan p = this.searchPlan(planId);
		if(p != null) this.planList.remove(p);
		else throw new ExPlainNotExist();
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
	
	public void addTransaction(String transType, Double amount, String accountId, String plainId, String description, String date) throws ExPlainNotExist, ExAccountNotExist {
		
		Plan p = searchPlan(plainId);
		Account acc = searchAccount(accountId);
		if(p == null) throw new ExPlainNotExist();
		if (acc == null) throw new ExAccountNotExist();
		
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
	
	public void deleteTransaction(int transactionId) throws ExTransactionNotExist {
		Transaction trans = this.findTransactionRecord(transactionId);
		if(trans != null) this.transactionRecords.remove(trans);
		else throw new ExTransactionNotExist();
	}
	
}