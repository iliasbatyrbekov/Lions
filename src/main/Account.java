package main;

import java.util.ArrayList;

public class Account {
	private String accID;
	private double debt;
	private double balance;
	
	public Account(String accID) {
		this.accID = accID;
		this.balance = 0;
		this.debt = 0;
	}
	
	public Account(String accID, double balance) {
		this.accID = accID;
		this.balance = balance;
		this.debt = 0;
	}

	
	public Account(String accID, double balance, double debt) {
		this.accID = accID;
		this.balance = balance;
		this.debt = debt;
	}
	
	public int updateBalance(double updateToBalance) {
		this.balance += updateToBalance;
		return 1;
	}
	public double getDebt() {
		return this.debt;
	}
	public double getBalance() {
		return this.balance;
	}
	public String getaccID() {
		return this.accID;
	}

	public static void listAll(ArrayList<Account> accountList) {
		// TODO Auto-generated method stub
		System.out.printf("%s| \t |%s| \t |%s|:",
				"Account ID", "Debt", "Balance");
		
		for(Account account : accountList) {
			System.out.printf("%s| \t |%d| \t |%d| \t |%s|:",
					account.accID, account.debt, account.balance);
		}
	}
}
