package main;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.Collections;


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
						String accID, double balance, double debt, 
						double interest, String withdrawDate)
						throws ExAccountTypeNotSupported
	{
		switch (accountType) {
		case"Cash":
			CashAccount ca = new CashAccount(accID, balance);
			this.accountList.add(ca);
			break;
		case"Saving":
			SavingAccount sa = new SavingAccount(accID, balance, interest, withdrawDate);
			this.accountList.add(sa);
			break;
		case"Credit":
			CreditCardAccuont cca = new CreditCardAccuont(accID, balance);
			this.accountList.add(cca);
			break;
		case"Debit":
			DebitAccount da = new DebitAccount(accID, balance);
			this.accountList.add(da);
			break;
		default:
			System.out.printf("%s", "There is no ", accountType, " type.");
			throw new ExAccountTypeNotSupported();
		}
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
		this.planList.add(plan);
	}
	
	public void addPlan(Plan aplan) {
		this.planList.add(aplan);
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
	
	public void listAllPlans() { Plan.listAll(this.planList); }
	
	// TransactionList
	public void listAllTransactionRecords() { Transaction.listAll(this.transactionRecords); }
	
	public Transaction findTransactionRecord(int transactionId) {
		return Transaction.searchTransaction(transactionRecords, transactionId);
	}
	
	public void addTransaction(String transType, Double amount, String accountId, String planId, String description, String date, String category) throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		
		Plan p = searchPlan(planId);
		Account acc = searchAccount(accountId);
		if(p == null) throw new ExPlainNotExist();
		if (acc == null) throw new ExAccountNotExist();
		
		int transactionId = 0;
		if(transactionRecords.size() != 0)
			this.transactionRecords.get(this.transactionRecords.size()-1).getTransactionID();
		
		if(transType.equals("Expense")){
			Expense expenseTrans = new Expense(transactionId, amount, accountId, description, date, category);
			if(acc.updateBalance(amount) == 1) this.transactionRecords.add(expenseTrans);
			else throw new ExUpdateBalanceErr();
		} else if (transType.equals("Income")) {
			Income incomeTrans = new Income(transactionId, amount, accountId, description, date, category);
			if(acc.updateBalance(amount) == 1) this.transactionRecords.add(incomeTrans);
			else throw new ExUpdateBalanceErr();
		} else if (transType.equals("TransferReceive")) {
			TransferReceive transReceive = new TransferReceive(transactionId, amount, accountId, description, date);
			if(acc.updateBalance(amount) == 1) this.transactionRecords.add(transReceive);
			else throw new ExUpdateBalanceErr();
		} else if (transType.equals("TransferRemit")) {
			TransferRemit transRemit = new TransferRemit(transactionId, amount, accountId, description, date);
			if(acc.updateBalance(amount) == 1) this.transactionRecords.add(transRemit);
			else throw new ExUpdateBalanceErr();
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