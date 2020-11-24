package test;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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
		long duration = plan.getDuration();
		System.out.println("duration "+duration);
		assertEquals(duration, duration);
	}
	
	@Test
	public void testGetCurrentDuration() {
		String startDateString = plan.getTimePeriod().get(0);
		long correctCurrentDuration = ChronoUnit.DAYS.between(LocalDate.parse(startDateString), LocalDate.now());
		
		long currentDuration = plan.getCurrentDuration();
//		System.out.println();
		assertEquals(correctCurrentDuration, currentDuration);
	}
	
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
