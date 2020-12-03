package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.DateSystem;
import main.LoanPlan;
import main.Plan;
import main.SavingPlan;
import main.Transaction;

public class TestSavingPlan {
	SavingPlan savingPlan;
	ArrayList<String> timePeriod;
	
	@BeforeEach
	public void setUp() throws Exception {
		class DateSystemStub extends DateSystem {
			public String getTodayDate() { return "2020-11-01"; };
		}
		
		timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-01");
        timePeriod.add("2020-12-31");
        DateSystemStub stub = new DateSystemStub(); 
        
        savingPlan = new SavingPlan("SavingPlan1", timePeriod, 10000.0, "Education", stub);
	}
	
	@AfterEach
	public void tearDown() {
		savingPlan = null;
		timePeriod = null;
	}
	
//	@Test void testUpdatePlanFailedWithTestStub() { //Used before when Transaction class was not finished
//	class TransactionStub extends Transaction {
//		public String getDate() {
//			return "2020-08-01";
//		}
//			public double getAmount {
//			return 100.0;
//			}
//	}
//	
//	TransactionStub stub = new TransactionStub();
//	LoanPlan loanPlan3 = new LoanPlan("LoanPlan3", timePeriod, 999.0, 5, "Jacky", stub);
//	loanPlan3.updatePlan(stub);
//}
	
	@Test
	public void testUpdatePlanFailed() {
		Transaction transaction = new Transaction(1234, 300.0, "Account1", "Semester A", "2020-08-01");
		SavingPlan savingPlan2 = new SavingPlan("SavingPlan2", timePeriod, 999.0, "House");
		savingPlan2.updatePlan(transaction);
	}
	
	@Test
	public void testSetGetGoalAmount() {
		double newGoalAmount = 15000.0;
		savingPlan.setGoalAmount(newGoalAmount);
		double goalAmount = savingPlan.getGoalAmount();
		assertEquals(15000.0, goalAmount);
	}
	
	@Test
	public void testGetCurrentAmount() {
		savingPlan.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-12-22"));
        
		double currentAmount = savingPlan.getCurrentAmount();
		assertEquals(999.9, currentAmount);
	}
	
	@Test
	public void testSetGetDescription() {
		String newDesciption = "Education - College";
		savingPlan.setDescription(newDesciption);
		String description = savingPlan.getDescription();
		assertEquals("Education - College", description);
	}
	
	@Test
	public void testAverageSaving(){
		String TensDayBefore = LocalDate.now().minusDays(10).toString();
		String FourDaysBefore = LocalDate.now().minusDays(4).toString();
		String TwoDaysBefore = LocalDate.now().minusDays(2).toString();
		String TenDaysAfter = LocalDate.now().plusDays(10).toString();
		
		ArrayList<String> timePeriod3 = new ArrayList<>();
		timePeriod3.add(TensDayBefore);
		timePeriod3.add(TenDaysAfter);
		SavingPlan savingPlan3 = new SavingPlan("SavingPlan3", timePeriod3, 10000.0, "Car");
		
		savingPlan3.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", FourDaysBefore));
		savingPlan3.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", TwoDaysBefore));
        
        double result = savingPlan3.getAverageSaving();
//        System.out.println("result "+result);
        assertEquals(99.0, result);
	}
	
	@Test
	public void testGetPlanLikely() {
		savingPlan.updatePlan(new Transaction(1234, 1696.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 696969.9, "Account1", "Semester B", "2020-12-22"));
        
        String summary = savingPlan.getPlan();
        String expectedSummary = "";
        expectedSummary += String.format("%-20s %-20s\n", "Plan Name", "SavingPlan1");
        expectedSummary += String.format("%-20s %-24s\n", "Plan Period", "2020-09-01 to 2020-12-31");
        expectedSummary += String.format("%-20s %-20s\n", "Total Saving", "10000.0");
        expectedSummary += String.format("%-20s %-20s\n", "Current Saving", "10000.0");
        expectedSummary += String.format("%-20s %-20s\n", "Average Saving", "163.0");
        expectedSummary += String.format("%-20s %-20s\n", "Days Needed More", "0");
        expectedSummary += String.format("%-20s %-20s\n", "Chance To Save Up", "Yes! Congratulations!");
        
        assertEquals(expectedSummary, summary);
	}
	
	@Test
	public void testGetPlanUnlikely() {
		savingPlan.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 69.9, "Account1", "Semester B", "2020-12-22"));
        
        String summary = savingPlan.getPlan();
        String expectedSummary = "";
        expectedSummary += String.format("%-20s %-20s\n", "Plan Name", "SavingPlan1");
        expectedSummary += String.format("%-20s %-24s\n", "Plan Period", "2020-09-01 to 2020-12-31");
        expectedSummary += String.format("%-20s %-20s\n", "Total Saving", "10000.0");
        expectedSummary += String.format("%-20s %-20s\n", "Current Saving", "149.9");
        expectedSummary += String.format("%-20s %-20s\n", "Average Saving", "2.0");
        expectedSummary += String.format("%-20s %-20s\n", "Days Needed More", "4926");
        expectedSummary += String.format("%-20s %-20s\n", "Chance To Save Up", "No");
        expectedSummary += String.format("%-20s %-20s\n", "Advised Avg. Saving", "165.0");
        
        assertEquals(expectedSummary, summary);
	}
	
	@Test
	public void testGetPlanNotEnoughData() {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-11-21");
        timePeriod.add("2021-02-01");
        
        savingPlan = new SavingPlan("SavingPlan1", timePeriod, 10000.0, "Car");
		
		savingPlan.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-11-21"));
        savingPlan.updatePlan(new Transaction(1235, 6.9, "Account1", "Semester B", "2020-11-22"));
        String summary = savingPlan.getPlan();
        String expectedSummary = "Not enough data to generate summary. Please check again after a while...";
        assertEquals(expectedSummary, summary);
	}
}


