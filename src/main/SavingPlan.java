package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SavingPlan extends Plan {
	private double goalAmount;
	private double currentAmount;
	private ArrayList<Transaction> tranRecord;
	private String description;//description, e.g. "Car", "House", "Education"

	public SavingPlan(String planName, ArrayList<String> timePeriod, double goalAmount, String description) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.currentAmount = 0;
		this.tranRecord=new ArrayList<Transaction>();
		this.description = description;
	}
	
	public double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public double getCurrentAmount() {
		return this.currentAmount;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAverageSaving() {
		double currentDur = (int) super.getCurrentDuration();
		
		double average = Math.floor(this.currentAmount / currentDur);
		return average;
	}
	
	@Override
	public void updatePlan(Transaction newSaving) {
		this.tranRecord.add(newSaving);
		this.currentAmount += newSaving.getAmount();
	}
	
	private boolean predictChance() {
		int dur = (int) super.getDuration();
		int currentDur = (int) super.getCurrentDuration();

		int moreMonths = calculateRemainigTime();

		return (currentDur+moreMonths < dur) ? true : false;
	}
	
	private int calculateRemainigTime() {
		if (this.goalAmount < this.currentAmount)
			return 0;
		
		double avgSaving = this.getAverageSaving();
		return (int) Math.ceil((this.goalAmount - this.currentAmount) / avgSaving);
	}

	public String getPlan() {//for display
		int dur = (int) super.getDuration();

		String summary, advice;
		int currentDur = (int) super.getCurrentDuration();
		if (currentDur<30)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double avgSaving = this.getAverageSaving();
			int moreTime = this.calculateRemainigTime();
			LocalDate endDate = LocalDate.parse(super.getTimePeriod().get(1));
			boolean likely = predictChance();
			double adjustAmount;
			String startDateString = super.getTimePeriod().get(0).toString();
			String endDateString = super.getTimePeriod().get(1).toString();
			
			summary = "";
			summary += String.format("%-20s %-20s\n", "Plan Name", this.getPlanName());
			summary += String.format("%-20s %-24s\n", "Plan Period", startDateString+" to "+endDateString);
//			summary += String.format("%-20s %-20s\n", "Days Passed", currentDur);
			summary += String.format("%-20s %-20s\n", "Total Saving", this.currentAmount);
			summary += String.format("%-20s %-20s\n", "Average Saving", avgSaving);
			summary += String.format("%-20s %-20s\n", "Days needed more", moreTime);
			advice = "";
			if (!likely) {
				adjustAmount = Math.ceil(this.currentAmount / (dur - currentDur));
				advice += String.format("%-20s %-20s\n", "Chance To Save Up", "No");
				advice += String.format("%-20s %-20s\n", "Advised Avg. Saving", adjustAmount);
			}
			else {
				advice += String.format("%-20s %-20s\n", "Chance to save up", "Yes! Congratulations!");
			}
			summary += advice;
		}
		
		return summary;
	}
}
