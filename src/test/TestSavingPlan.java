package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Plan;
import main.SavingPlan;
import main.Transaction;

public class TestSavingPlan {
	Plan plan;
	SavingPlan savingPlan;
	
	@BeforeEach
	public void setUp() throws Exception {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-01");
        timePeriod.add("2020-12-31");
        
        plan = new Plan("Plan A", timePeriod);
        savingPlan = new SavingPlan(plan.getPlanName(), plan.getTimePeriod(), 10000.0, "Education");
	}
	
	public void tearDown() {}
	
//	@SuppressWarnings("deprecation")	
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
        savingPlan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-11-02"));
        
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
        savingPlan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-11-02"));
        
        double result = savingPlan.getAverageSaving();
//        System.out.println("time period "+plan.getTimePeriod());
//        System.out.println("time length "+plan.getDuration());
//        System.out.println("result "+result);
        assertEquals(12.0, result, 0.1);
	}
	
	@Test
	public void testGetPlan() {
		savingPlan.updatePlan(new Transaction(1234, 100.0, "Account1", "Semester A", "2020-10-01"));
        savingPlan.updatePlan(new Transaction(1235, 99.9, "Account1", "Semester B", "2020-11-02"));
        String summary = savingPlan.getPlan();
//        System.out.println("summary "+summary);
        String expectedSummary = "From the last 82 day(s), you repaid $2 on average per month. You will need 100 more month(s) to settle the loan. You are not likely to accomplish this loan plan by December 2020 as expected. save $6.000000 monthly from now on!";
        assertEquals(expectedSummary, summary);
	}
	
	
}


