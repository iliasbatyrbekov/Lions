package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class SavingPlan extends Plan {
	private double goalAmount;
	private double currentAmount;
	private double savingAmount;
	private ArrayList<Transaction> transHist; // [month: savingAmount]
	private String purposeOfPlan;

	public SavingPlan(String planName, ArrayList<String> timePeriod, double savingAmount, String purposeOfPlan) {
		super(planName, timePeriod);
		this.savingAmount = savingAmount;
		this.transHist = new ArrayList<Transaction>();// (Collections.nCopies(this.getTimePeriodLength("month"),
											// this.calculateMonthlyAmount()))
//		for (int i = 0; i < this.getTimePeriodLength("month"); ++i)
//			this.transHist.add(monthlyAmount);
		this.purposeOfPlan = purposeOfPlan;
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
	@SuppressWarnings("rawtypes")
	public Map predict_average_saving(ArrayList<String> arrayList) {
		User user = new User();
		
		Map<ArrayList<String>, Double> average_saving_period = new HashMap<>();
		Double average_saving = 0.0, total_saving = 0.0, count = 1.0;
		
		for(Transaction trans_hist : user.getTransactionList("2020-11-11", "2020-11-15")) {
			total_saving += trans_hist.getAmount();
			count++;
		}

		average_saving = total_saving / count;
		average_saving_period.put(arrayList, average_saving);
		return average_saving_period;
	}
	
	public void updatePlan(Transaction newSaving) {
		double updatedAmount = this.currentAmount + newSaving.getAmount();
		transHist.add(updatedAmount);
	}

	public void getPlan() {//for display
		double currentsavingAmount = 0;
		for (Transaction savingAmount : this.transHist)
			currentsavingAmount += savingAmount.getAmount();

		Map<String, Transaction> hist = new HashMap<String, Transaction>();
		int noOfMonths = (int) super.getTimePeriodLength("month");
		LocalDate date = LocalDate.parse(super.getTimePeriod().get(0));
		for (int i=0; i<noOfMonths; ++i) {
			hist.put(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), (transHist.get(i)));
			date = date.plusMonths(1);
		}

		String summary, advice;
		int currentMonth = (int) super.getCurrentPointInTime("month");
		if (currentMonth<1)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double avgRepaid = Math.floor((this.savingAmount - currentsavingAmount) / currentMonth);
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
		plan.put("total", this.savingAmount);
		plan.put("current", currentsavingAmount);
		plan.put("history", hist);
		plan.put("summary", summary);
	}

	//calculate how far current saving from goal saving
	public double predict_goal_saving(){
		return getGoalAmount() - getCurrentAmount();
	}
}
