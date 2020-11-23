package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.LoanPlan;
import main.Plan;
import main.SavingPlan;
import main.Transaction;

public class TestSavingPlan {
	SavingPlan savingPlan;
	
	@BeforeEach
	public void setUp() throws Exception {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-01");
        timePeriod.add("2020-12-31");
        
        savingPlan = new SavingPlan("SavingPlan1", timePeriod, 10000.0, "Education");
	}
	
	public void tearDown() {}
	
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
		savingPlan.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-12-22"));
        
        double result = savingPlan.getAverageSaving();
//        System.out.println("time period "+plan.getTimePeriod());
//        System.out.println("time length "+((Plan)savingPlan).getCurrentDuration());
//        System.out.println("result "+result);
        assertEquals(12.0, result, 0.01);
	}
	
	@Test
	public void testGetPlanLikely() {
		savingPlan.updatePlan(new Transaction(1234, 1696.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 69696.9, "Account1", "Semester B", "2020-12-22"));
        String summary = savingPlan.getPlan();
//        System.out.println("summary "+summary);
        String expectedSummary = "From the last 82 day(s), you repaid $870 on average per day. You will need 0 more day(s) to settle the loan. You are very likely to accomplish this loan plan by December 2020 as expected.";
        assertEquals(expectedSummary, summary);
	}
	
	@Test
	public void testGetPlanUnlikely() {
		savingPlan.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 69.9, "Account1", "Semester B", "2020-12-22"));
        String summary = savingPlan.getPlan();
//        System.out.println("summary "+summary);
        String expectedSummary = "From the last 82 day(s), you repaid $1 on average per day. You will need 9851 more day(s) to settle the loan. You are not likely to accomplish this loan plan by December 2020 as expected. Save $4 daily from now on!";
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
//        System.out.println("total "+ ((Plan)savingPlan).getDuration());
//        System.out.println("current "+ ((Plan)savingPlan).getCurrentDuration());
//        System.out.println("summary not data "+summary);
        String expectedSummary = "Not enough data to generate summary. Please check again after a while...";
        assertEquals(expectedSummary, summary);
	}
}


