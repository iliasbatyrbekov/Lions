package main;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class BudgetPlan extends Plan {

	private Map<String, Double> goalAmount;
	private ArrayList<Transaction> allTrans;
	
	public BudgetPlan(String planName, 
			ArrayList<String> timePeriod, 
			Map<String, Double> goalAmount){
		
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		allTrans = new ArrayList<Transaction>();
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
		return (int) Period.between(this.getTimePeriodDatesArr().get(0), LocalDate.now()).getDays();
	}
	
	
	private Map<String, Double> getActualExp() {
	Map<String, Double> resultMap = new HashMap<String, Double>();
	for (Entry<String, Double> i : goalAmount.entrySet()) {
		resultMap.put(i.getKey(), 0.0);
	}
		
		allTrans.stream()
			.filter(t -> t instanceof Expense)
			.forEach(t -> {
					Double sum = resultMap.get(((Expense)t).getCategory()) + t.getAmount();
					resultMap.put(((Expense)t).getCategory(), Math.abs(sum));
				}
			);
		return resultMap;
	}
	
	public String getPlan()
	{
		
		String displayedString = "";
		displayedString += "Planned Budget \n";
		displayedString += String.format("%-20s %-20s\n", "Category" ,"Spending Planned") ;
		for (Entry<String, Double> i : this.goalAmount.entrySet()) {
			displayedString += String.format("%-20s %-20f\n", i.getKey(), i.getValue());
		}
		//current expenses
		displayedString += "Current Expenses \n";
		displayedString += String.format("%-20s %-20s n", "Category" ,"Money Spent") ;
		Map<String, Double> actExpMap = this.getActualExp();
		for (Entry<String, Double> i : goalAmount.entrySet()) {
			displayedString += String.format("%-20s %-20f\n", i.getKey(), actExpMap.get(i.getKey()));
		}
		//how many days left before going over budget
		int numDaysSinceStart = this.getNumDaysPassed();
		if(numDaysSinceStart > 0) {
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
					break;
				default:
					remDaysResponseString = Integer.toString(i.getValue());
					break;
				}
				
				displayedString += String.format("%-20s %-20s\n", i.getKey(), remDaysResponseString);
			}
		}
		else {
			displayedString += "Not enough data for a prediction \n";
		}
		return displayedString;
	}
	
	public void updatePlan(Transaction transaction) {
		allTrans.add(transaction);
	}
}

