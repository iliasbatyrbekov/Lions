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
	
	public void viewAllAccountBalance() {
		
	}
	
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
		
	}
	public void viewExpensebyMember() {
		
	}



}
