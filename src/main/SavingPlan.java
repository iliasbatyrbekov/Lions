package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SavingPlan extends Plan {
	private double goalAmount;
	private double currentAmount;

	public SavingPlan(String planName, ArrayList<Date> timePeriod, double goalAmount, double currentAmount) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.currentAmount = currentAmount;
	}
	
	public double getGoalAmount() {
		return goalAmount;
	}
	
	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public double getCurrentAmount() {
		return currentAmount;
	}
	
	//calculate average saving from past time period
	public Map predict_average_saving(ArrayList<Date> timePeriod) {
		Map<Date, double> average_saving_period = new HashMap<>();
		double average_saving = 0, total_saving = 0, count = 1;
		ArrayList<Transaction> transaction_history = new ArrayList<>();
		
		for(Transaction trans_hist : transaction_history) {
			total_saving += trans_hist.getAmount();
			count++;
		}

		average_saving = total_saving / count;
		average_saving_period.put(timePeriod, average_saving);
		return average_saving;
	}

	//calculate how far current saving from goal saving
	public double predict_goal_saving(){
		return getGoalAmount() - getCurrentAmount();
	}
}
