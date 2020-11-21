package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BudgetPlan extends Plan {

	private Map<String, Double> actualExpense;
	private Map<String, Double> goalAmount;
	
	public BudgetPlan(String planName, 
			ArrayList<String> timePeriod, 
			Map<String, Double> actualExpense, 
			Map<String, Double> goalAmount){
		
		super(planName, timePeriod);
		this.actualExpense = this.getActualExp();
		this.goalAmount = goalAmount;
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
	public Map<String, Integer>  predictRemaining(Map<String, Double> averageDailyExpense) {
		Map<String, Integer> remDaysByCategoryMap = new HashMap<String, Integer>();
		ArrayList<Integer> remainingDays = new ArrayList<>();
		for (Map.Entry<String, Double> entry : this.actualExpense.entrySet()){
			String currKey = entry.getKey();
//			TODO find bug with averageDailyExpense being zero divide by zero
//			double avgDailyExp = averageDailyExpense.get(currKey);
//			if (avgDailyExp<=0) {
//				
//			}
			int remainingDaysNum = (int) ((this.goalAmount.get(currKey) - this.actualExpense.get(currKey))/ averageDailyExpense.get(currKey));
			if (remainingDaysNum<0) {
				remainingDaysNum = 0;
			}
			remainingDays.add(remainingDaysNum);
			remDaysByCategoryMap.put(currKey, remainingDaysNum);
		}
		
		return remDaysByCategoryMap;
	}
	private int getNumDaysPassed() {
		long temp  = super.getTimePeriodLength("days");
		return (int) temp;
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
	
	private Map<String, Double> getActualExp() {
	Map<String, Double> resultMap = new HashMap<String, Double>();
		//get all trans
		ArrayList<String> period = super.getTimePeriod();
		ArrayList<Transaction> allTrans = User.getInstance().getTransactionList(
				period.get(0), 
				period.get(1));
		
		allTrans.stream()
			.filter(t -> t instanceof Expense)
			.forEach(t -> resultMap.put(
					((Expense)t).getCategory(), t.getAmount()));
		return resultMap;
	}
	
	public String getPlan()
	{
		this.actualExpense = getActualExp();
		

		String displayedString = "";
		/*
		 * current expense$
		 * how much was planned?$
		 * how many days you can survive without overbudget
		 * 
		 * 
		 */

		//planned expense
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		for (Entry<String, Double> i : this.goalAmount.entrySet()) {
			displayedString += String.format("%s: \t %d \n", i.getKey(), i.getValue());
		}
		//current expenses
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		for (Entry<String, Double> i : this.actualExpense.entrySet()) {
			displayedString += String.format("%s: \t %d \n", i.getKey(), i.getValue());
		}
		//how many days left before going over budget
		int numDaysSinceStart = this.getNumDaysPassed();
		if(numDaysSinceStart > 5) {
			displayedString += "Days left (on average) before exceeding the budget\n";
			Map<String, Double> averageDailyExpense = this.getAverageDailyExpense(this.getNumDaysPassed());
			Map<String, Integer> remDaysByCategoryMap = this.predictRemaining(averageDailyExpense);
			for (Entry<String, Integer> i : remDaysByCategoryMap.entrySet()) {
				displayedString += String.format("%s: \t %d \n", i.getKey(), i.getValue());
			}
		}
		else {
			displayedString += "Not enough data for a prediction \n";
		}
		
		System.out.print(displayedString);
		return displayedString;
	}
}
