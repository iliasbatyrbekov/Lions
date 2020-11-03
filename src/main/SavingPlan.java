package main;

import java.util.ArrayList;
import java.util.Date;

public class SavingPlan extends Plan {
	public SavingPlan(String planName, ArrayList<Date> timePeriod) {
		super(planName, timePeriod);
	}
	
	private double goalAmount;
	private double currentAmount;
	
	public double getGoalAmount() {
		return goalAmount;
	}
	
	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public double getCurrentAmount() {
		return currentAmount;
	}
	
	public double predict_average_saving(ArrayList<Date> timePeriod) {
		double average_saving = 0, total_saving = 0, count = 0;
		ArrayList<Transaction> transaction_history = new ArrayList<>();
		
		for(Transaction trans_hist : transaction_history) {
			total_saving += trans_hist.getAmount();
			count++;
		}
		
		average_saving = total_saving / count;
		return average_saving;
	}
}
