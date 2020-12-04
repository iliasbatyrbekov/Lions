package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
		
		LocalDate localDate = LocalDate.now().minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = localDate.format(formatter);
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add(formattedString);
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", formattedString, "A"));
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", formattedString, "B"));
		
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += String.format("%-20s %-20s\n", "Category" ,"Spending Planned") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 10.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += String.format("%-20s %-20s \n", "Category" ,"Money Spent") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 2.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 2.0);
		displayedString += "Days left (on average) before exceeding the budget\n";
		displayedString += String.format("%-20s %-20s\n", "A", "4");
		displayedString += String.format("%-20s %-20s\n", "B", "9");
		
		assertEquals(displayedString, budgetPlan.getPlan());
	}
	
	@Test
	public void testBudgetPlan_4() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = localDate.format(formatter);
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add(formattedString);
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", formattedString, "A"));
		budgetPlan.updatePlan(new Expense(1, 2.0, "", "", formattedString, "B"));

		//this one is time sensetive to the current date
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += String.format("%-20s %-20s\n", "Category" ,"Spending Planned") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 10.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += String.format("%-20s %-20s \n", "Category" ,"Money Spent") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 2.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 2.0);
		displayedString += "Not enough data for a prediction \n";
		
		assertEquals(displayedString, budgetPlan.getPlan());
	}
	
	@Test
	public void testBudgetPlan_5() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		String formattedString = localDate.format(formatter);
		
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
		displayedString += String.format("%-20s %-20s\n", "Category" ,"Spending Planned") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 10.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += String.format("%-20s %-20s \n", "Category" ,"Money Spent") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 20.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 30.0);
		displayedString += "Days left (on average) before exceeding the budget\n";
		displayedString += String.format("%-20s %-20s\n", "A", "Overbudget");
		displayedString += String.format("%-20s %-20s\n", "B", "Overbudget");
		
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
		displayedString += String.format("%-20s %-20s\n", "Category" ,"Spending Planned");
		displayedString += String.format("%-20s %-20.1f\n", "A", 10.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 20.0);
		displayedString += "Current Expenses \n";
		displayedString += String.format("%-20s %-20s \n", "Category" ,"Money Spent") ;
		displayedString += String.format("%-20s %-20.1f\n", "A", 0.0);
		displayedString += String.format("%-20s %-20.1f\n", "B", 0.0);
		displayedString += "Days left (on average) before exceeding the budget\n";
		displayedString += String.format("%-20s %-20s\n", "A", "Analysis not possible (not enough data)");
		displayedString += String.format("%-20s %-20s\n", "B", "Analysis not possible (not enough data)");
		
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
