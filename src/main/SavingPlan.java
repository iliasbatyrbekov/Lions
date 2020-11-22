package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SavingPlan extends Plan {
	private double goalAmount;
	private double currentAmount;
//	private double monthlyGoalAmount;
	private ArrayList<Transaction> tranRecord;
//	private ArrayList<Double> monthlySum;
	private String description;//description, e.g. "Car", "House", "Education"

	public SavingPlan(String planName, ArrayList<String> timePeriod, double goalAmount, String description) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.currentAmount = 0;
//		this.monthlyGoalAmount = getMonthlyGoalAmount();
		this.tranRecord=new ArrayList<Transaction>();
		
//		this.monthlySum = new ArrayList<Double>();
//		for (int i = 0; i < super.getTimePeriodLength("month"); ++i)
//			this.monthlySum.add(this.monthlyGoalAmount);
	
		this.description = description;
	}

//	private double getMonthlyGoalAmount() {
//		return this.goalAmount / super.getTimePeriodLength("month");
//	}
	
	public double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
//		this.monthlyGoalAmount = getMonthlyGoalAmount();
	}
	
	public double getCurrentAmount() {
		return this.currentAmount;
//		double currentSaingAmount = 0;
//		for (Transaction tran : this.tranRecord) {
//			currentSaingAmount += tran.getAmount();
//		}
//		return currentSaingAmount;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	//calculate average saving from past time period
//	public Map predict_average_saving(ArrayList<String> arrayList) {
//		User user = new User();
//		
//		Map<ArrayList<String>, Double> average_saving_period = new HashMap<>();
//		Double average_saving = 0.0, total_saving = 0.0, count = 1.0;
//		
//		for(Transaction trans_hist : user.getTransactionList("2020-11-11", "2020-11-15")) {
//			total_saving += trans_hist.getAmount();
//			count++;
//		}
//
//		average_saving = total_saving / count;
//		average_saving_period.put(arrayList, average_saving);
//		return average_saving_period;
//	}
	public double getAverageSaving() {
//		double currentsavingAmount = this.getCurrentSavingAmount();
		double currentDur = (int) super.getCurrentDuration();
		
		double average = Math.floor(this.currentAmount / currentDur);
		return average;
	}
	@Override
	public void updatePlan(Transaction newSaving) {
		this.tranRecord.add(newSaving);

//		double currentDur = this.getCurrentPointInTime("month");// e.g. 1st month, 3rd month
//		int currentDurIndex = (int) currentDur - 1;// e.g. [0] == 1st month [2] == 3rd month
//		double currentSavingAmount = this.monthlySum.get(currentDurIndex) - newSaving.getAmount();
//		
//		this.monthlySum.set(currentDurIndex, currentSavingAmount);
		this.currentAmount += newSaving.getAmount();
	}
	
	private boolean predictChance() {
//		double currentLoanAmount = this.getCurrentLoanAmount();

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
//		double currentsavingAmount = this.getCurrentSavingAmount();

//		Map<String, Double> hist = new HashMap<>();
		int dur = (int) super.getDuration();
//		LocalDate date = LocalDate.parse(super.getTimePeriod().get(0));
//		for (int i=0; i<dur; ++i) {
//			hist.put(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), (monthlySum.get(i)));
//			date = date.plusMonths(1);
//		}

		String summary, advice;
		int currentDur = (int) super.getCurrentDuration();
		if (currentDur<30)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double avgSaving = this.getAverageSaving();
			int moreTime = this.calculateRemainigTime();
			LocalDate endDate = LocalDate.parse(super.getTimePeriod().get(1));
			String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
			boolean likely = predictChance();
			// String likelihoodString;
			double adjustAmount;
			
			summary = String.format("From the last %d day(s), you repaid $%.0f on average per day. "
									+ "You will need %d more day(s) to settle the loan. ",
									currentDur, avgSaving, moreTime);

			if (!likely) {
				// likelihoodString = "not likely";
//				adjustAmount = Math.ceil(currentsavingAmount / (dur - currentDur));
				adjustAmount = Math.ceil(this.currentAmount / (dur - currentDur));
				advice = String.format("You are not likely to accomplish this loan plan by %s as expected."
									+ " Save $%.0f daily from now on!", endDateString, adjustAmount);
			}
			else {
				// likelihoodString = "very likely";
				advice = String.format("You are very likely to accomplish this loan plan by %s as expected.", endDateString);
			}
			summary += advice;
		}

//		Map<String, Object> plan = new HashMap<String, Object>();
//		plan.put("total", this.goalAmount);
//		plan.put("current", currentsavingAmount);
//		plan.put("history", hist);
//		plan.put("summary", summary);
		
//		return plan;
		return summary;//todo
	}

//	//calculate how far current saving from goal saving
//	public double predict_goal_saving(){
//		return getGoalAmount() - getCurrentSavingAmount();
//	}
}
