package test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Plan;
import main.LoanPlan;
import main.Transaction;

public class TestLoanPlan {
	LoanPlan loanPlan;
	
	@BeforeEach
	public void setUp() throws Exception {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-01");
        timePeriod.add("2020-12-31");
        
        loanPlan = new LoanPlan("LoanPlan1", timePeriod, 10000.0, 10, "Jack");
	}
	
	public void tearDown() {
		loanPlan = null;
	}
	
//	@SuppressWarnings("deprecation")	
	@Test
	public void testSetGetInterestRate() {
		double newInterestRate = 45;
		loanPlan.setInterestRate(newInterestRate);
		double interestRate = loanPlan.getInterestRate();
		assertEquals(45, interestRate);
	}
	
	@Test
	public void testSetGetGoalAmount() {
		double newGoalAmount = 1111;
		loanPlan.setGoalAmount(newGoalAmount);
		double goalAmount = loanPlan.getGoalAmount();
		assertEquals(1111, goalAmount);
	}
	
	@Test
	public void testGetCurrentAmount() {
		loanPlan.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", "2020-10-01"));
        loanPlan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-11-02"));
        
		double currentAmount = loanPlan.getCurrentAmount();
		assertEquals(999.9, currentAmount);
	}
	
	@Test
	public void testSetDebtOwner() {
		String newDebtOwner = "Jackie";
		loanPlan.setDebtOwner(newDebtOwner);
		String debtOwner = loanPlan.getDebtOwner();
		assertEquals("Jackie", debtOwner);
	}
	
	@Test
	public void testAverageLoanRepayment(){
		loanPlan.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", "2020-10-01"));
        loanPlan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-12-22"));
        
        double result = loanPlan.getAverageLoanRepayment();
//        System.out.println("time period "+plan.getTimePeriod());
//        System.out.println("time length "+ ((Plan)loanPlan).getCurrentDuration());
//        System.out.println("result "+result);
        assertEquals(12.0, result);
	}
	
	@Test
	public void testGetPlanLikely() {
		loanPlan.updatePlan(new Transaction(1234, 1696.0, "Account1", "Semester A", "2020-10-01"));
        loanPlan.updatePlan(new Transaction(1235, 69696.9, "Account1", "Semester B", "2020-12-22"));
        String summary = loanPlan.getPlan();
//        System.out.println("summary likely "+summary);
        String expectedSummary = "From the last 82 day(s), you repaid $870 on average per day. You will need 0 more day(s) to settle the loan. You are very likely to accomplish this loan plan by December 2020 as expected.";
        assertEquals(expectedSummary, summary);
	}
	
	@Test
	public void testGetPlanUnlikely() {
		loanPlan.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-10-01"));
        loanPlan.updatePlan(new Transaction(1235, 6.9, "Account1", "Semester B", "2020-12-22"));
        String summary = loanPlan.getPlan();
//        System.out.println("total "+ ((Plan)loanPlan).getDuration());
//        System.out.println("current "+ ((Plan)loanPlan).getCurrentDuration());
        System.out.println("summary unlikely "+summary);
        String expectedSummary = "From the last 82 day(s), you repaid $1 on average per day. You will need 9914 more day(s) to settle the loan. You are not likely to accomplish this loan plan by December 2020 as expected. Save $3 daily from now on!";
        assertEquals(expectedSummary, summary);
	}
	
	@Test
	public void testGetPlanNotEnoughData() {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-11-21");
        timePeriod.add("2021-02-01");
        
        loanPlan = new LoanPlan("LoanPlan1", timePeriod, 10000.0, 10, "Jack");
		
		loanPlan.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-11-21"));
        loanPlan.updatePlan(new Transaction(1235, 6.9, "Account1", "Semester B", "2020-11-22"));
        String summary = loanPlan.getPlan();
//        System.out.println("total "+ ((Plan)loanPlan).getDuration());
//        System.out.println("current "+ ((Plan)loanPlan).getCurrentDuration());
//        System.out.println("summary unlikely "+summary);
        String expectedSummary = "Not enough data to generate summary. Please check again after a while...";
        assertEquals(expectedSummary, summary);
	}
}


