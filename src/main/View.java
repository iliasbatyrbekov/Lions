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
	
	public HashMap<String, Double> viewAllAccountBalance() {		
		HashMap<String, Double> account_balance = new HashMap<String, Double>();
		for(Account acc : accList) {
			account_balance.put(acc.getaccID(), acc.getBalance());
		} 
		return account_balance;			
	}

	//TODO: Bryan
	public ArrayList<String> viewExpensebyAccount() {
		HashMap<String, Double> acc_exp = new HashMap<String, Double>();
		
		
		
		//BAD CODE SMELL: maybe consider recording transactions history in Accounts too
		for (Transaction tra: tranHist) {
			if(tra instanceof Expense) {
				String targetaccID=tra.getAccountId();
				if (acc_exp.containsKey(targetaccID)) {
					acc_exp.put(targetaccID, acc_exp.get(targetaccID)+tra.getAmount());
				} else {
					acc_exp.put(targetaccID, tra.getAmount());
				}
			}
		}
		ArrayList<String> result = new ArrayList<>();

		//print
		for (String acc:acc_exp.keySet()) {
			System.out.println(acc+" : "+acc_exp.get(acc));
 			result.add(acc + ": "+acc_exp.get(acc));
		}
		return result;
	}
	
	public ArrayList<String> viewExpensebyCategory() {
		HashMap<String, Double> cat_exp = new HashMap<String, Double>();
		
		//BAD CODE SMELL: maybe consider recording transactions history in Accounts too
		for (Transaction tra: tranHist) {
			
			//BAD DESIGN: transaction should'nt know that its children class "expense" has attribute "category"
			if(tra instanceof Expense) {
				String cat=((Expense)tra).getCategory();
				if (cat_exp.containsKey(cat)) {
					cat_exp.put(cat, cat_exp.get(cat)+tra.getAmount());
				} else {
					cat_exp.put(cat, tra.getAmount());
				}
			}
		}
		
		//print
		ArrayList<String> result = new ArrayList<>();
 		for (String cat:cat_exp.keySet()) {
			System.out.println(cat+" : "+cat_exp.get(cat));
 			result.add(cat + ": "+cat_exp.get(cat));
		}
 		return result;
	}
	
	public ArrayList<String> viewExpensebyMember() {
		HashMap<String, Double> mem_exp = new HashMap<String, Double>();
		for (Transaction tra: tranHist) {
			if(tra instanceof Expense) {
				String mem=((Expense)tra).getMember();
				if (mem_exp.containsKey(mem)) {
					mem_exp.put(mem, mem_exp.get(mem)+tra.getAmount());
				} else {
					mem_exp.put(mem, tra.getAmount());
				}
			}
		}
		
		ArrayList<String> result = new ArrayList<>();
 		for (String mem:mem_exp.keySet()) {
			System.out.println(mem+" : "+mem_exp.get(mem));
 			result.add(mem + ": "+mem_exp.get(mem));
		}
		
 		return result;
	}
}
