package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BudgetPlan extends Plan {

	private Map<String, Double> actualExpense;
	private Map<String, Double> goalAmount;
	private ArrayList<String> members;
	
	public BudgetPlan(String planName, 
			ArrayList<Date> timePeriod, 
			Map<String, Double> actualExpense, 
			Map<String, Double> goalAmount, 
			ArrayList<String> members){
		
		super(planName, timePeriod);
		this.actualExpense = actualExpense;
		this.goalAmount = goalAmount;
		this.members = members;
	}
	
	public Map<String, Double> getGoalAmount() {
		return goalAmount;
	}
	public void setGoalAmount(Map<String, Double> goalAmount) {
		this.goalAmount = goalAmount;
	}
	public Map<String, Double> getActualExpense() {
		return actualExpense;
	}
	public void setActualExpense(Map<String, Double> actualExpense) {
		this.actualExpense = actualExpense;
	}
	public Map<String, Double> getAverageDailyExpense(int nDays){
		int numPassedDays = this.getNumDaysPassed();
		Map<String, Double> averageDailyExpense = new HashMap<String, Double>(); 
		for (Map.Entry<String, Double> entry : this.actualExpense.entrySet()){
			averageDailyExpense.put(entry.getKey(), entry.getValue()/numPassedDays);
		}
		return averageDailyExpense;
	}
	public int predictRemaining(Map<String, Double> averageDailyExpense) {
		ArrayList<Integer> remainingDays = new ArrayList<>();
		for (Map.Entry<String, Double> entry : this.actualExpense.entrySet()){
			String currKey = entry.getKey();
			int remainingDaysNum = (int) ((this.goalAmount.get(currKey) - this.actualExpense.get(currKey))/ averageDailyExpense.get(currKey));
			//TODO pretend that we found a bug that makes remaining days negative :D
			remainingDays.add(remainingDaysNum);
		}
		
		return this.findMin(remainingDays);
	}
	private int getNumDaysPassed() {
		long diff = this.getTimePeriod().get(0).getTime() - this.getTimePeriod().get(1).getTime();
		return (int) (diff / 1000 / 60 / 60 / 24);
	}
	private int findMin(ArrayList<Integer> remainingDays ) {
		int min = remainingDays.get(0);
		for (int i : remainingDays) {
			if(min<i) {
				min = i;
			}
		}
		return min;
	}
}
