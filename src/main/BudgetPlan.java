package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	public int predictRemaining(Map<String, Double> averageDailyExpense) {
		ArrayList<Integer> remainingDays = new ArrayList<>();
		for (Map.Entry<String, Double> entry : this.actualExpense.entrySet()){
			String currKey = entry.getKey();
			int remainingDaysNum = (int) ((this.goalAmount.get(currKey) - this.actualExpense.get(currKey))/ averageDailyExpense.get(currKey));
			if (remainingDaysNum<0) {
				remainingDaysNum = -1;
			}
			remainingDays.add(remainingDaysNum);
		}
		
		return this.findMin(remainingDays);
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
	
	public void getPlan()
	{
		/*
		 * current expense$
		 * how much was planned?$ 
		 * how much left?$
		 * how many days left?
		 * 
		 * 
		 */
	}
}
