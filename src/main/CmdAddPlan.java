package main;

import java.util.*;
import java.util.stream.IntStream;

public class CmdAddPlan {
	public void execute(String[] cmdParts) {
		
		User u = User.getInstance();
		if(cmdParts[1].equals("LoanPlan")) {
			ArrayList<String> timePeriod = new ArrayList<>();
			timePeriod.add(cmdParts[3]);
			timePeriod.add(cmdParts[4]);
			
			u.addLoanPlan(cmdParts[2], timePeriod, Double.parseDouble(cmdParts[5]), Double.parseDouble(cmdParts[6]), cmdParts[7]);
		}
		else if(cmdParts[1].equals("SavingPlan")) {
			ArrayList<String> timePeriod = new ArrayList<>();
			timePeriod.add(cmdParts[3]);
			timePeriod.add(cmdParts[4]);
			
			u.addSavingPlan(cmdParts[2], timePeriod, Double.parseDouble(cmdParts[5]), cmdParts[6]);
		}
		else if(cmdParts[1].equals("BudgetPlan")) {
			ArrayList<String> timePeriod = new ArrayList<>();
			timePeriod.add(cmdParts[3]);
			timePeriod.add(cmdParts[4]);
			Map<String, Double> goalAmount = new HashMap<>();
			//will check for n category/goal pairs, n is an integer at index 8
			int finIndexEclusive = 9 + Integer.parseInt(cmdParts[8]); 
			for (int i=9;i<finIndexEclusive;) {
				goalAmount.put(cmdParts[i], Double.parseDouble(cmdParts[i+1]));
				i+=2;
			}
			u.addBudgetPlan(cmdParts[2], timePeriod, goalAmount);
		}
		else {
			//error
		}
		
	}
}
