package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.BudgetPlan;
import main.ExAccountNotExist;
import main.ExAccountTypeNotSupported;
import main.ExPlainNotExist;
import main.ExUpdateBalanceErr;
import main.User;

public class TestBudgetPlan {
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 

//	@Before
//	public void setUpStreams() { 
//	    System.setOut(new PrintStream(outContent)); 
//	}
//
//	@After
//	public void cleanUpStreams() { 
//	    System.setOut(null); 
//	}
	
	@Test
	public void testPrintTransaction1() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-30");
        timePeriod.add("2020-12-30");
        Map<String, Double> goalAmount = new HashMap<String, Double>();
        goalAmount.put("A", 10.0);
        goalAmount.put("B", 20.0);
        
		BudgetPlan budgetPlan = new BudgetPlan("name", timePeriod, goalAmount);
		
		User user = User.getInstance();
		user.addPlan(budgetPlan);
		user.addAccount("Cash", "1", 1000.0, 0.0, 0, null);
		
		user.addTransaction("Expense", 2.0, "1", "1", "", "2020-10-30", "A");
		user.addTransaction("Expense", 2.0, "1", "1", "", "2020-10-30", "B");
		
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		for (Entry<String, Double> i : goalAmount.entrySet()) {
			displayedString += String.format("%s: \t %d \n", i.getKey(), i.getValue());
		}
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		
//		assertEquals(displayedString, budgetPlan.getPlan());
	}
}
