package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SavingPlan extends Plan {
	private double goalAmount;
	private double currentAmount;
	private double loanAmount;
	private double interestRate;
	private double monthlyAmount;
	private ArrayList<Double> log; // [month: loanAmount]
	private String debtOwner;

	public SavingPlan(String planName, ArrayList<String> timePeriod, double loanAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.monthlyAmount = this.calculateMonthlyAmount();
		this.log = new ArrayList<Double>();// (Collections.nCopies(this.getTimePeriodLength("month"),
											// this.calculateMonthlyAmount()))
		for (int i = 0; i < this.getTimePeriodLength("month"); ++i)
			this.log.add(monthlyAmount);
		this.debtOwner = debtOwner;
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
		Map<Date, Double> average_saving_period = new HashMap<>();
		Double average_saving = 0.0, total_saving = 0.0, count = 1.0;
		ArrayList<Transaction> transaction_history = new ArrayList<>();
		
		for(Transaction trans_hist : transaction_history) {
			total_saving += trans_hist.getAmount();
			count++;
		}

		average_saving = total_saving / count;
		average_saving_period.put(timePeriod, average_saving);
		return average_saving_period;
	}

	public double calculateMonthlyAmount() {
		double monthRate = this.interestRate/12;
		double noOfMonths = Math.ceil(super.getTimePeriodLength("month"));
		double factor = monthRate*Math.pow(1+monthRate, noOfMonths) / (Math.pow(1+monthRate, noOfMonths)-1);
		return this.loanAmount*factor;
	}

	public void getPlan() {//for display
		double currentLoanAmount = 0;
		for (double loanAmount : this.log)
			currentLoanAmount += loanAmount;

		Map<String, Double> hist = new HashMap<String, Double>();
		int noOfMonths = (int) super.getTimePeriodLength("month");
		LocalDate date = LocalDate.parse(super.getTimePeriod().get(0));
		for (int i=0; i<noOfMonths; ++i) {
			hist.put(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), log.get(i));
			date = date.plusMonths(1);
		}

		String summary, advice;
		int currentMonth = (int) super.getCurrentPointInTime("month");
		if (currentMonth<1)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double avgRepaid = Math.floor((this.loanAmount - currentLoanAmount) / currentMonth);
			int moreMonths = (int) Math.ceil(currentLoanAmount / avgRepaid);
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
				adjustAmount = Math.ceil(currentLoanAmount / (noOfMonths - currentMonth));
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
		plan.put("total", this.loanAmount);
		plan.put("current", currentLoanAmount);
		plan.put("history", hist);
		plan.put("summary", summary);
	}

	//calculate how far current saving from goal saving
	public double predict_goal_saving(){
		return getGoalAmount() - getCurrentAmount();
	}
}
