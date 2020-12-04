package test;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import main.Plan;
import main.LoanPlan;
import main.Transaction;

public class TestPlan {
	Plan plan;
	
	@BeforeEach
	public void setUp() throws Exception {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-01");
        timePeriod.add("2020-12-31");
        
        plan = new Plan("Plan1", timePeriod);
	}
	
	@AfterEach
	public void tearDown() {
		plan = null;
	}
	
	@Test
	public void testGetPlanID() {
		String planID = plan.getPlanID();
		assertEquals(0, planID); //planID starts from 0, so first plan has planID = 0
	}
	
	@Test
	public void testSetGetPlanName() {
		String newPlanName = "newPlan1";
		plan.setPlanName(newPlanName);
		String updatedPlanName = plan.getPlanName();
		assertEquals("newPlan1", updatedPlanName);
	}
	
	@Test
	public void testSetTimePeriod() {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-10-01");
        timePeriod.add("2020-12-31");
        plan.setTimePeriod(timePeriod);
	}
	
	@Test
	public void testGetDuration() {
		long duration = plan.getDuration("");
//		System.out.println("duration "+duration);
		assertEquals(duration, duration);
	}
	
	@Test
	public void testGetSpecificDuration() {
		String startDateString = plan.getTimePeriod().get(0);
		
		long currentDuration = plan.getDuration("2020-10-01");
//		System.out.println();
		assertEquals(30, currentDuration);
	}
	
	@Test
	public void testIsBetweenTrue() {
		boolean isBetween = plan.isBetween("2020-10-01");
		assertEquals(true, isBetween);
	}
	
	@Test
	public void testIsBetweenFalse() {
		boolean isBetween = plan.isBetween("2020-08-01");
		assertEquals(false, isBetween);
	}
	
//	@Test void testUpdatePlanWithTestStub() { //Used before when Transaction class was not finished
//		class TransactionStub extends Transaction {
//			public String getDate() {
//				return "2020-08-01";
//			}
//		}
//		
//		TransactionStub stub = new TransactionStub();
//		LoanPlan loanPlan3 = new LoanPlan("LoanPlan3", timePeriod, 999.0, 5, "Jacky", stub);
//		loanPlan3.updatePlan(stub);
//	}

	@Test
	public void testUpdatePlan() {
		Transaction transaction = new Transaction(123, 1000.0, "0", "Tran1", "2020-11-18");
		plan.updatePlan(transaction);
	}
	
	@Test
	public void testGetPlan() {
		String expectedResult = "";
		String actualResult = plan.getPlan();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testListAll() {
		ArrayList<Plan> planList = new ArrayList<>();
		planList.add(plan);
		
		Plan.listAll(planList);
	}
}
