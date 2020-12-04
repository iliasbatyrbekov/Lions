package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SavingPlan extends Plan {
	public DateSystem dateSystem;
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
		this.dateSystem = DateSystem.getDateSystem();
	}
	
	public SavingPlan(String planName, ArrayList<String> timePeriod, double goalAmount, String description, DateSystem dateSystem) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.currentAmount = 0;
		this.tranRecord=new ArrayList<Transaction>();
		this.description = description;
		this.dateSystem = dateSystem;
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
		double currentDur = (int) super.getDuration(this.dateSystem.getTodayDate());
		
		double average = Math.floor(this.currentAmount / currentDur);
		return average;
	}
	
	@Override
	public void updatePlan(Transaction newSaving) {
		if (!super.isBetween(newSaving.getDate())) {// transaction date out of range
			System.out.println("Transaction Not Included: Date Out Of Range");
			return;
		}
		
		this.tranRecord.add(newSaving);
		double updateAmount = Math.abs(newSaving.getAmount());
		this.currentAmount += updateAmount;
		
		if (this.goalAmount <= this.currentAmount) {
			double extra = this.currentAmount - this.goalAmount;
			System.out.println("You have successfully saved up! With extra $"+extra);
			
			this.currentAmount = this.goalAmount;
		}
	}
	
	private boolean predictChance() {
		int dur = (int) super.getDuration("");
		int currentDur = (int) super.getDuration(this.dateSystem.getTodayDate());

		int moreMonths = calculateRemainigTime();

		return (currentDur+moreMonths < dur) ? true : false;
	}
	
	private int calculateRemainigTime() {
		if (this.goalAmount <= this.currentAmount)
			return 0;
		
		double avgSaving = this.getAverageSaving();
		return (int) Math.ceil((this.goalAmount - this.currentAmount) / avgSaving);
	}

	public String getPlan() {//for display
		int dur = (int) super.getDuration("");

		String summary, advice;
		int currentDur = (int) super.getDuration(this.dateSystem.getTodayDate());
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
			summary += String.format("%-20s %-20s\n", "Total Saving", this.goalAmount);
			summary += String.format("%-20s %-20s\n", "Current Saving", this.currentAmount);
			summary += String.format("%-20s %-20s\n", "Average Saving", avgSaving);
			summary += String.format("%-20s %-20s\n", "Days Needed More", moreTime);
			advice = "";
			if (!likely) {
				adjustAmount = Math.ceil((this.goalAmount - this.currentAmount) / (dur - currentDur));
				advice += String.format("%-20s %-20s\n", "Chance To Save Up", "No");
				advice += String.format("%-20s %-20s\n", "Advised Avg. Saving", adjustAmount);
			}
			else {
				advice += String.format("%-20s %-20s\n", "Chance To Save Up", "Yes! Congratulations!");
			}
			summary += advice;
		}
		
		return summary;
	}
}
