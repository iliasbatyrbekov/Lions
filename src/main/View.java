package main;

import java.util.ArrayList;
import java.util.HashMap;

public class View {
	private ArrayList<Transaction> tranHist;
	private ArrayList<Account>accList;
	private ArrayList<Plan> planList;
	
	View(ArrayList<Transaction> TL,ArrayList<Account> AL, ArrayList<Plan> PL){
		tranHist=TL;
		accList=AL;
		planList=PL;
	}
	
	public String viewAllAccountBalance() {
		
		HashMap<String, Double> account_balance = new HashMap<String, Double>();
		for(Account acc : accList) {
			account_balance.put(acc.getaccID(), acc.getBalance());
		}
			
		return account_balance.toString();
	}
	//test
	public void viewExpensebyAccount() {
		HashMap<String, Double> acc_exp = new HashMap<String, Double>();
		
		//get all accIDs
		for (Account acc : accList) {
			acc_exp.put(acc.getaccID(), (double) 0);
		}
		
		//BAD CODE SMELL: maybe consider recording transactions history in Accounts too
		for (Transaction tra: tranHist) {
			if(tra.getType().equals("Expense")) {
				String targetaccID=tra.getAccount();
				double amount =tra.getAmount();
				acc_exp.put(targetaccID, acc_exp.get(targetaccID)+amount);
			}
		}
//		lolololololololol
		
		//print
		for (String acc:acc_exp.keySet()) {
			System.out.println(acc+" : "+acc_exp.get(acc));
		}
	}
	public void viewExpensebyCategory() {
		HashMap<String, Double> cat_exp = new HashMap<String, Double>();
		
		//BAD CODE SMELL: maybe consider recording transactions history in Accounts too
		for (Transaction tra: tranHist) {
			
			//BAD DESIGN: transaction should'nt know that its children class "expense" has attribute "category"
			if(tra.getType().equals("Expense")) {
				String cat=((Expense)tra).getCategory();
				cat_exp.put(cat,cat_exp.get(cat)+tra.getAmount());
			}
		}
		
		//print
		for (String cat:cat_exp.keySet()) {
			System.out.println(cat+" : "+cat_exp.get(cat));
		}
	}
	public void viewExpensebyMember() {
		
	}



}
