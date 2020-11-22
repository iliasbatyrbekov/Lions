package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import main.BudgetPlan;
import main.ExAccountNotExist;
import main.ExAccountTypeNotSupported;
import main.ExPlainNotExist;
import main.ExUpdateBalanceErr;
import main.Expense;

public class TestBudgetPlan {

	@Test
	public void testBudgetPlan_1() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-11-21");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", "2020-11-21", "A"));
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", "2020-11-21", "B"));
		
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 10.0);
		displayedString += String.format("%s: \t %f \n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 2.0);
		displayedString += String.format("%s: \t %f \n", "B", 2.0);
		displayedString += "Days left (on average) before exceeding the budget\n";
		displayedString += String.format("%s: \t %s \n", "A", "4");
		displayedString += String.format("%s: \t %s \n", "B", "9");
		
		assertEquals(displayedString, budgetPlan.getPlan());
	}
	
	@Test
	public void testBudgetPlan_4() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-11-22");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", "2020-11-22", "A"));
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", "2020-11-22", "B"));

		//this one is time sensetive to the current date
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 10.0);
		displayedString += String.format("%s: \t %f \n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 2.0);
		displayedString += String.format("%s: \t %f \n", "B", 2.0);
		displayedString += "Not enough data for a prediction \n";
		
		assertEquals(displayedString, budgetPlan.getPlan());
	}
	
	@Test
	public void testBudgetPlan_5() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-11-21");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		budgetPlan.updatePlan(new Expense(1, 20.0, "", "", "2020-11-21", "A"));
		budgetPlan.updatePlan(new Expense(1, 30.0, "", "", "2020-11-21", "B"));

		//this one is time sensetive to the current date
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 10.0);
		displayedString += String.format("%s: \t %f \n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 20.0);
		displayedString += String.format("%s: \t %f \n", "B", 30.0);
		displayedString += "Days left (on average) before exceeding the budget\n";
		displayedString += String.format("%s: \t %s \n", "A", "Overbudget");
		displayedString += String.format("%s: \t %s \n", "B", "Overbudget");
		
		assertEquals(displayedString, budgetPlan.getPlan());
	}
	
	@Test
	public void testBudgetPlan_6() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-11-21");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);

		//this one is time sensetive to the current date
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 10.0);
		displayedString += String.format("%s: \t %f \n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		displayedString += String.format("%s: \t %f \n", "A", 0.0);
		displayedString += String.format("%s: \t %f \n", "B", 0.0);
		displayedString += "Days left (on average) before exceeding the budget\n";
		displayedString += String.format("%s: \t %s \n", "A", "Analysis not possible (not enough data)");
		displayedString += String.format("%s: \t %s \n", "B", "Analysis not possible (not enough data)");
		
		assertEquals(displayedString, budgetPlan.getPlan());
	}
	
	@Test
	public void testBudgetPlan_2() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-30");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);

		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		assertEquals(goalAmount, budgetPlan.getGoalAmount());
	}
	
	@Test
	public void testBudgetPlan_3() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-30");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);

		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		budgetPlan.setGoalAmount(goalAmount);
	}
}
