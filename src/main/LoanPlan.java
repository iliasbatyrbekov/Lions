package main;

// import java.util.Collections;
// import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.String;

public class LoanPlan extends Plan{
	private double goalAmount;//goal loan amount
	private double interestRate;
	private double currentAmount;
	private ArrayList<Transaction> tranRecord;
	private String debtOwner;
	
	public LoanPlan(String planName, ArrayList<String> timePeriod,double goalAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.currentAmount = 0;
		this.interestRate = interestRate;
		this.tranRecord = new ArrayList<Transaction>();
		this.debtOwner = debtOwner;
	}
		
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public String getDebtOwner() {
		return debtOwner;
	}
	
	public void setDebtOwner(String debtOwner) {
		this.debtOwner = debtOwner;
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
	@Override
	public void updatePlan(Transaction transaction) {
		this.tranRecord.add(transaction);
		this.currentAmount += transaction.getAmount();
	}
	
	private double getAverageRepayment() {//refactor
		int currentDur = (int) super.getCurrentDuration();
		double average = Math.floor(this.currentAmount / currentDur);
		
		return average;
	}

	private boolean predictChanceOfPayment() {
		int dur = (int) super.getDuration();
		int currentDur = (int) super.getCurrentDuration();

		int moreMonths = calculateRemainigTime();

		return (currentDur+moreMonths < dur) ? true : false;
	}
	
	public double getAverageLoanRepayment() {
		int currentDur = (int) super.getCurrentDuration();
		double avg = Math.floor(this.currentAmount / currentDur);
		return avg;
	}
	
	private int calculateRemainigTime() {
		if (this.goalAmount < this.currentAmount)
			return 0;
		
		double avgRepaid = this.getAverageRepayment();
		int remaining = (int) Math.ceil((this.goalAmount - this.currentAmount) / avgRepaid);
		return remaining;
	}
	
	@Override
	public String getPlan() {//for display
		int dur = (int) super.getDuration();

		String summary, advice;
		int currentDur = (int) super.getCurrentDuration();
		if (currentDur<30)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double adjustAmount;
			double avgRepaid = this.getAverageLoanRepayment();
			int moreTime = this.calculateRemainigTime();
			String startDateString = super.getTimePeriod().get(0).toString();
			String endDateString = super.getTimePeriod().get(1).toString();
			boolean likely = predictChanceOfPayment();
			
			summary = "";
			summary += String.format("%-20s %-20s\n", "Plan Name", this.getPlanName());
			summary += String.format("%-20s %-24s\n", "Plan Period", startDateString+" to "+endDateString);
//			summary += String.format("%-20s %-20s\n", "Days Passed", currentDur);
			summary += String.format("%-20s %-20s\n", "Total Repayment", this.currentAmount);
			summary += String.format("%-20s %-20s\n", "Average Repayment", avgRepaid);
			summary += String.format("%-20s %-20s\n", "Days needed more", moreTime);
			
			advice = "";
			if (!likely) {
				adjustAmount = Math.ceil(this.currentAmount / (dur - currentDur));
				advice += String.format("%-20s %-20s\n", "Chance To Repay", "No");
				advice += String.format("%-20s %-20s\n", "Advised Avg. Repay", adjustAmount);
			
			}
			else {
				advice += String.format("%-20s %-20s\n", "Chance to Repay", "Yes! Congratulations!");
			}
			summary += advice;
		}

		return summary;
	}
}
