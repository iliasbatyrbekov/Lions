package feat2;

import java.util.ArrayList;

import test.Account;

public class View {
	private ArrayList<Transaction> tranHist;
	private ArrayList<Account>accList;
	private ArrayList<Plan> planList;
	
	View(ArrayList<Transaction> TL,ArrayList<Account> AL, ArrayList<Plan> PL){
		tranHist=TL;
		accList=AL;
		planList=PL;
	}
}
