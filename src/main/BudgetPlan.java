package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BudgetPlan extends Plan {

	private Map<String, Double> goalAmount;
	
	public BudgetPlan(String planName, 
			ArrayList<String> timePeriod, 
			Map<String, Double> goalAmount){
		
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
	}
	 
	public Map<String, Double> getGoalAmount() {
		return goalAmount;
	}
	public void setGoalAmount(Map<String, Double> goalAmount) {
		this.goalAmount = goalAmount;
	}
	public Map<String, Double> getAverageDailyExpense(int nDays){
		int numPassedDays = this.getNumDaysPassed();
		Map<String, Double> averageDailyExpense = new HashMap<String, Double>(); 
		for (Map.Entry<String, Double> entry : this.getActualExp().entrySet()){
			averageDailyExpense.put(entry.getKey(), entry.getValue()/numPassedDays);
		}
		return averageDailyExpense;
	}
	//puts -1 if analysis is not possible, 0 if expense is more than budget, >0 if ok.
	public Map<String, Integer>  predictRemaining(Map<String, Double> averageDailyExpense) {
		Map<String, Integer> remDaysByCategoryMap = new HashMap<String, Integer>();
		for (Map.Entry<String, Double> entry : this.getActualExp().entrySet()){
			String currKey = entry.getKey();
			double avgDailyExp = averageDailyExpense.get(currKey);
			int remainingDaysNum = -1;
			if (avgDailyExp > 0){
				remainingDaysNum = (int) ((this.goalAmount.get(currKey) - this.getActualExp().get(currKey))/ avgDailyExp);
				if (remainingDaysNum<1) {
					remainingDaysNum = 0;
				}
			}
			remDaysByCategoryMap.put(currKey, remainingDaysNum);
		}
		
		return remDaysByCategoryMap;
	}
	private int getNumDaysPassed() {
		long temp  = super.getCurrentDuration();
		return (int) temp;
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
		
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += "Category \t Money Spent \n";
		for (Entry<String, Double> i : this.goalAmount.entrySet()) {
			displayedString += String.format("%s: \t %d \n", i.getKey(), i.getValue());
		}
		//current expenses
		displayedString += "Current Expenses \n";
		displayedString += "Category \t Money Spent \n";
		for (Entry<String, Double> i : this.getActualExp().entrySet()) {
			displayedString += String.format("%s: \t %d \n", i.getKey(), i.getValue());
		}
		//how many days left before going over budget
		int numDaysSinceStart = this.getNumDaysPassed();
		if(numDaysSinceStart > 1) {
			displayedString += "Days left (on average) before exceeding the budget\n";
			Map<String, Double> averageDailyExpense = this.getAverageDailyExpense(this.getNumDaysPassed());
			Map<String, Integer> remDaysByCategoryMap = this.predictRemaining(averageDailyExpense);
			for (Entry<String, Integer> i : remDaysByCategoryMap.entrySet()) {
				String remDaysResponseString = "";
				//-1 analysis not possible, 0 overbudget, >0 ok
				switch (i.getValue()) {
				case -1:
					remDaysResponseString = "Analysis not possible (not enough data)";
					break;
				case 0:
					remDaysResponseString = "Overbudget";
				default:
					remDaysResponseString = Integer.toString(i.getValue());
					break;
				}
				
				displayedString += String.format("%s: \t %s \n", i.getKey(), remDaysResponseString);
			}
		}
		else {
			displayedString += "Not enough data for a prediction \n";
		}
		
		return displayedString;
	}
}
