package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Plan;
import main.SavingPlan;
import main.Transaction;

public class TestGetAverageSaving {

	@SuppressWarnings("deprecation")
	@Test
	public void test_1(){
        ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add("2020-09-30");
        timePeriod.add("2020-12-30");
        
        Plan plan = new Plan("Plan A", timePeriod);
        SavingPlan saving_plan = new SavingPlan(plan.getPlanName(), plan.getTimePeriod(), 10000.0, "Education");
//        Map<Date, Double> result = new HashMap<>();
//        result = saving_plan.predict_average_saving(plan.getTimePeriod());
//        Map<Date, Double> expected = new HashMap<>();
//        expected.put(new Date(), 20.0);
        saving_plan.updatePlan(new Transaction(1234, 300.0, "Account1", "Semester A", "2020-10-01"));
        saving_plan.updatePlan(new Transaction(1235, 699.9, "Account1", "Semester B", "2020-11-02"));
//        System.out.println("current point: " + plan.getCurrentPointInTime("day"));
        double result = saving_plan.getAverageSaving();
//        System.out.println("result: " + result);
        assertEquals(45.0, result, 0.1);
	}
}


