package main;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


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
		view = new View(this.transactionRecords, this.accountList, this.planList);
	}
	
	//View
	public void viewExpenseByAccount() { view.viewExpensebyAccount(); }
	public void viewExpenseByCategory() { view.viewExpensebyCategory(); }
	public void viewExpenseByMember() {
		
		view.viewExpensebyMember();
	}
	
	
	//Account
	public String checkAccount(String type, String accId) {
		Account acc = this.searchAccount(accId);
		if(acc != null) {
			if(acc instanceof CashAccount && type.equals("Cash")) return "Added in Cash Account";
			else if(acc instanceof SavingAccount && type.equals("Saving")) return "Added in Saving Account";
			else if(acc instanceof DebitAccount && type.equals("Debit")) return "Added in Debit Account";
			else if(acc instanceof CreditCardAccount && type.equals("Credit")) return "Added in Credit Card Account";
			else return "Account type mismatch";
		}
		else return "Account not found";
	}
	
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
			CreditCardAccount cca = new CreditCardAccount(accID, balance);
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
	
	public Account searchAccount(String accId) {
		for (Account a : this.accountList) {
			if(a.getaccID().equals(accId)) return a;
		}
		return null;
	}
	
	public void addAccount(Account anAccount) {
		this.accountList.add(anAccount);
	}
	
	
	//Plan
	public void listAllPlan() { Plan.listAll(this.planList); }
	
	public void addPlan(
			String planTypeString,
			String planName, 
			String startDate, 
			String endDate,
			Map<String, Double> goalAmountByCategories,
			Double goalAmount,
			Double interestRate, 
			String debtOwner,
			String description) 
	{	
		ArrayList<String> timePeriodString = new ArrayList<>();
		timePeriodString.add(startDate);
		timePeriodString.add(endDate);
		
		switch (planTypeString) {
		case "Budget":
			this.planList.add(new BudgetPlan(planName, timePeriodString, goalAmountByCategories));
			break;
		case "Loan":
			this.planList.add(new LoanPlan(planName, timePeriodString, goalAmount, interestRate, debtOwner));
			break;
		case "Saving":
			this.planList.add(new SavingPlan(planName, timePeriodString, goalAmount, description));
			break;
		default:
			this.addPlan(planName, startDate, endDate);
			break;
		}
	}
	
	public void addPlan(
			String planName, 
			String startDate, 
			String endDate) {
		ArrayList<String> timePeriodString = new ArrayList<>();
		timePeriodString.add(startDate);
		timePeriodString.add(endDate);
		Plan plan = new Plan(planName, timePeriodString);
		this.planList.add(plan);
	}
	
	public void addPlan(Plan aplan) {
		this.planList.add(aplan);
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
	
	public void addExpenseTransaciton (String transType, Double amount, String accountId, String planId, String description, String date, String category, String member) throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		Account acc = searchAccount(accountId);
		if (acc == null) throw new ExAccountNotExist();

		int transactionId = 0;
		if(transactionRecords.size() != 0)
			transactionId = this.transactionRecords.get(this.transactionRecords.size()-1).getTransactionID();
		if(transType.equals("Expense")){
			Expense expenseTrans = new Expense(transactionId, amount, accountId, description, date, category);
			expenseTrans.setMember(member);
			if(acc.updateBalance(amount) == 1)
				this.transactionRecords.add(expenseTrans);
			else throw new ExUpdateBalanceErr();
			
			Plan plan = searchPlan(planId);
			if(plan == null) 
				throw new ExPlainNotExist();
			else
				plan.updatePlan(expenseTrans);
		}
		
	}
	
	public void addTransaction(String transType, Double amount, String accountId, String planId, String description, String date, String category) throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		
		Account acc = searchAccount(accountId);
		if (acc == null) throw new ExAccountNotExist();

		int transactionId = 0;
		if(transactionRecords.size() != 0)
			transactionId = this.transactionRecords.get(this.transactionRecords.size()-1).getTransactionID();
		
		if(transType.equals("Expense")){
			Expense expenseTrans = new Expense(transactionId, amount, accountId, description, date, category);
			if(acc.updateBalance(amount) == 1)
				this.transactionRecords.add(expenseTrans);
			else throw new ExUpdateBalanceErr();
			
			Plan plan = searchPlan(planId);
			if(plan == null) 
				throw new ExPlainNotExist();
			else
				plan.updatePlan(expenseTrans);
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
		return this.transactionRecords;
	}
	
}