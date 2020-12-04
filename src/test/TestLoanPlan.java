package test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import personalAccounting.DateSystem;
import personalAccounting.LoanPlan;
import personalAccounting.Plan;
import personalAccounting.Transaction;

public class TestLoanPlan {
	LoanPlan loanPlan;
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
        
        loanPlan = new LoanPlan("LoanPlan1", timePeriod, 10000.0, 10, "Jack", stub);
	}
	
	@AfterEach
	public void tearDown() {
		loanPlan = null;
		timePeriod = null;
	}
	
//	@Test void testUpdatePlanFailedWithTestStub() { //Used before when Transaction class was not finished
//		class TransactionStub extends Transaction {
//			public String getDate() {
//				return "2020-08-01";
//			}
// 			public double getAmount {
//				return 100.0;
// 			}
//		}
//		
//		TransactionStub stub = new TransactionStub();
//		LoanPlan loanPlan3 = new LoanPlan("LoanPlan3", timePeriod, 999.0, 5, "Jacky", stub);
//		loanPlan3.updatePlan(stub);
//	}
	
	@Test
	public void testUpdatePlanFailed() {
		Transaction transaction = new Transaction(1234, 300.0, "Account1", "Semester A", "2020-08-01");
		LoanPlan loanPlan2 = new LoanPlan("LoanPlan2", timePeriod, 999.0, 5, "Jacky");
		loanPlan2.updatePlan(transaction);
	}
	
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
		String TensDayBefore = LocalDate.now().minusDays(10).toString();
		String FourDaysBefore = LocalDate.now().minusDays(4).toString();
		String TwoDaysBefore = LocalDate.now().minusDays(2).toString();
		String TenDaysAfter = LocalDate.now().plusDays(10).toString();
		
		ArrayList<String> timePeriod3 = new ArrayList<>();
		timePeriod3.add(TensDayBefore);
		timePeriod3.add(TenDaysAfter);
		LoanPlan loanPlan3 = new LoanPlan("LoanPlan4", timePeriod3, 10000.0, 10, "Jack");
		
		loanPlan3.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", FourDaysBefore));
        loanPlan3.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", TwoDaysBefore));
        
        double result = loanPlan3.getAverageLoanRepayment();
        assertEquals(99.0, result);
	}
	
	@Test
	public void testGetPlanLikely() {
		loanPlan.updatePlan(new Transaction(1234, 1696.0, "Account1", "Semester A", "2020-10-01"));
        loanPlan.updatePlan(new Transaction(1235, 69696.9, "Account1", "Semester B", "2020-12-22"));
        
        String summary = loanPlan.getPlan();
        String expectedSummary = "";
        expectedSummary += String.format("%-20s %-20s\n", "Plan Name", "LoanPlan1");
        expectedSummary += String.format("%-20s %-24s\n", "Plan Period", "2020-09-01 to 2020-12-31");
        expectedSummary += String.format("%-20s %-20s\n", "Total Repayment", "10000.0");
        expectedSummary += String.format("%-20s %-20s\n", "Current Repayment", "10000.0");
        expectedSummary += String.format("%-20s %-20s\n", "Average Repayment", "163.0");
        expectedSummary += String.format("%-20s %-20s\n", "Days needed more", "0");
        expectedSummary += String.format("%-20s %-20s\n", "Chance To Repay", "Yes! Congratulations!");
        
        assertEquals(expectedSummary, summary);
	}
	
	@Test
	public void testGetPlanUnlikely() {
		loanPlan.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-10-01"));
        loanPlan.updatePlan(new Transaction(1235, 6.9, "Account1", "Semester B", "2020-12-22"));
        
        String summary = loanPlan.getPlan();
        
        String expectedSummary = "";
        expectedSummary += String.format("%-20s %-20s\n", "Plan Name", "LoanPlan1");
        expectedSummary += String.format("%-20s %-24s\n", "Plan Period", "2020-09-01 to 2020-12-31");
        expectedSummary += String.format("%-20s %-20s\n", "Total Repayment", "10000.0");
        expectedSummary += String.format("%-20s %-20s\n", "Current Repayment", "86.9");
        expectedSummary += String.format("%-20s %-20s\n", "Average Repayment", "1.0");
        expectedSummary += String.format("%-20s %-20s\n", "Days needed more", "9914");
        expectedSummary += String.format("%-20s %-20s\n", "Chance To Repay", "No");
        expectedSummary += String.format("%-20s %-20s\n", "Advised Avg. Repay", "166.0");
        
        assertEquals(expectedSummary, summary);
	}
	
//	@Test //tested with test stub before required function was not ready
//	public void testGetPlanNotEnoughDataWithTestStub() {
//		class DateSystemStub2 extends DateSystem {
//			public String getTodayDate() { return "2020-09-25"; };
//		}
//		DateSystemStub2 stub2 = new DateSystemStub2();
//		LoanPlan loanPlan4 = new LoanPlan("LoanPlan4", timePeriod, 10000.0, 10, "Jack", stub2);
//		
//		loanPlan4.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-09-21"));
//        loanPlan4.updatePlan(new Transaction(1235, 6.9, "Account1", "Semester B", "2020-09-22"));
//        String summary = loanPlan4.getPlan();
//        String expectedSummary = "Not enough data to generate summary. Please check again after a while...";
//        assertEquals(expectedSummary, summary);
//	}
	
	@Test
	public void testGetPlanNotEnoughData() {
		ArrayList<String> timePeriod2 = new ArrayList<>();
		timePeriod2.add(LocalDate.now().minusDays(1).toString());
		timePeriod2.add(LocalDate.now().plusDays(10).toString());
		LoanPlan loanPlan4 = new LoanPlan("LoanPlan4", timePeriod2, 10000.0, 10, "Jack");
	
		loanPlan4.updatePlan(new Transaction(1234, 80.0, "Account1", "Semester A", "2020-09-21"));
        loanPlan4.updatePlan(new Transaction(1235, 6.9, "Account1", "Semester B", "2020-09-22"));
        String summary = loanPlan4.getPlan();
        String expectedSummary = "Not enough data to generate summary. Please check again after a while...";
        assertEquals(expectedSummary, summary);
	}
}


