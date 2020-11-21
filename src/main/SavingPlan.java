package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SavingPlan extends Plan {
	private double goalAmount;
	// private double currentSavingAmount;
	private double monthlyGoalAmount;
	private ArrayList<Transaction> tranRecord;
	private ArrayList<Double> monthlySum;
	private String purposeOfPlan;//description, e.g. "Car", "House", "Education"

	public SavingPlan(String planName, ArrayList<String> timePeriod, double goalAmount, String purposeOfPlan) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.monthlyGoalAmount = goalAmount / this.getTimePeriodLength("month");
		
		this.monthlySum = new ArrayList<Double>();
		for (int i = 0; i < this.getTimePeriodLength("month"); ++i)
			this.monthlySum.add(this.monthlyGoalAmount);
		
			this.purposeOfPlan = purposeOfPlan;
	}

	public double getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(double goalAmount) {
		this.goalAmount = goalAmount;
	}
	
	public double getCurrentSavingAmount() {
		double currentSaingAmount = 0;
		for (Transaction tran : this.tranRecord) {
			currentSaingAmount += tran.getAmount();
		}
		return currentSaingAmount;
	}
	public String getPurposeOfPlan() {
		return this.purposeOfPlan;
	}
	public void setPurposeOfPlan(String purpose) {
		this.purposeOfPlan = purpose;
	}
	public void addTransaction(Transaction transaction) {
		this.tranRecord.add(transaction);
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
	
	public void updatePlan(Transaction newSaving) {
		this.addTransaction(newSaving);

		double currentMonth = this.getCurrentPointInTime("month");// e.g. 1st month, 3rd month
		int currentMonthIndex = (int) currentMonth - 1;// e.g. [0] == 1st month [2] == 3rd month
		double currentSavingAmount = this.monthlySum.get(currentMonthIndex) - newSaving.getAmount();
		
		this.monthlySum.set(currentMonthIndex, currentSavingAmount);
	}

	public String getPlan() {//for display
		double currentsavingAmount = this.getCurrentSavingAmount();

		Map<String, Double> hist = new HashMap<>();
		int noOfMonths = (int) super.getTimePeriodLength("month");
		LocalDate date = LocalDate.parse(super.getTimePeriod().get(0));
		for (int i=0; i<noOfMonths; ++i) {
			hist.put(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), (monthlySum.get(i)));
			date = date.plusMonths(1);
		}

		String summary, advice;
		int currentMonth = (int) super.getCurrentPointInTime("month");
		if (currentMonth<1)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double avgRepaid = Math.floor((this.goalAmount - currentsavingAmount) / currentMonth);
			int moreMonths = (int) Math.ceil(currentsavingAmount / avgRepaid);
			LocalDate endDate = LocalDate.parse(super.getTimePeriod().get(1));
			String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
			boolean likely = (noOfMonths < currentMonth+moreMonths) ? false : true;
			// String likelihoodString;
			double adjustAmount;
			
			summary = String.format("From the last %d month(s), you repaid $%f on average per month. "
									+ "You will need %d more month(s) to settle the loan. ",
									currentMonth, avgRepaid, moreMonths);

			if (!likely) {
				// likelihoodString = "not likely";
				adjustAmount = Math.ceil(currentsavingAmount / (noOfMonths - currentMonth));
				advice = String.format("You are not likely to accomplish this loan plan by %s as expected. "
									+ "save $%f monthly from now on!", endDateString, adjustAmount);
			}
			else {
				// likelihoodString = "very likely";
				advice = String.format("You are very likely to accomplish this loan plan by %s as expected. ", endDateString);
			}
			summary += advice;
		}

		Map<String, Object> plan = new HashMap<String, Object>();
		plan.put("total", this.goalAmount);
		plan.put("current", currentsavingAmount);
		plan.put("history", hist);
		plan.put("summary", summary);
		
//		return plan;
		return summary;//todo
	}

	//calculate how far current saving from goal saving
	public double predict_goal_saving(){
		return getGoalAmount() - getCurrentSavingAmount();
	}
}
