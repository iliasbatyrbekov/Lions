package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class tesPredictAverageSaving {

	@Test
	public void test_1(){
        ArrayList<Date> timePeriod = new ArrayList<>();
        timePeriod.add(new Date());
        Plan plan = new Plan("Plan A", timePeriod);
        SavingPlan saving_plan = new SavingPlan(plan.getPlanName(), plan.getTimePeriod(), 20.0, 15.0);
        Map<Date, Double> result = new HashMap<>();
        result = saving_plan.predict_average_saving(plan.getTimePeriod());
        Map<Date, Double> expected = new HashMap<>();
        expected.put(new Date(), 20.0);
        assertEquals(expected, result);
	}
}


