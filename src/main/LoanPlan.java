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
//	private double monthlyPayment;
	private ArrayList<Transaction> tranRecord;
//	private ArrayList<Double> monthlySum; //[0] monthlyPayment of 1st month, [1] monthlyPayment of 2nd month, ...
	private String debtOwner;
	
	public LoanPlan(String planName, ArrayList<String> timePeriod,double goalAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.goalAmount = goalAmount;
		this.currentAmount = 0;
		this.interestRate = interestRate;
		this.tranRecord = new ArrayList<Transaction>();

//		this.monthlySum = new ArrayList<Double>();//(Collections.nCopies(this.getTimePeriodLength("month"), this.calculateMonthlyAmount()))
//		for (int i=0;i<this.getTimePeriodLength("month");++i)
//			this.monthlySum.add(this.calculateMonthlyPayment());
		
		this.debtOwner = debtOwner;
	}
	
//	private double calculateMonthlyPayment() {//monthly loan payment
//		double monthRate = this.interestRate/12;
//		double dur = Math.ceil(super.getTimePeriodLength("month"));
//		double factor = monthRate*Math.pow(1+monthRate, dur) / (Math.pow(1+monthRate, dur)-1);
//		return this.goalAmount*factor;
//	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
//		this.monthlyPayment = calculateMonthlyPayment();
		//if insufficient past...
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
//		this.monthlyPayment = calculateMonthlyPayment();
		//if insufficient past...
	}
	
	public double getCurrentAmount() {
		return this.currentAmount;
//		double currentGoalAmount = 0;
//		for (Transaction tran : this.tranRecord) {
//			currentGoalAmount += tran.getAmount();
//		}
//		return currentGoalAmount;
	}
	@Override
	public void updatePlan(Transaction transaction) {
		this.tranRecord.add(transaction);
		this.currentAmount += transaction.getAmount();
//		double currentDur = this.getCurrentPointInTime("month");// e.g. 1st month, 3rd month
//		int currentDurIndex = (int) currentDur - 1;// e.g. [0] == 1st month [2] == 3rd month
//		double currentLoanAmount = this.monthlySum.get(currentDurIndex) - transaction.getAmount();
//		
//		if (0<currentLoanAmount)//monthlyAmount > repayment
//			this.monthlySum.set(currentDurIndex, currentLoanAmount);
//		else {
//			this.monthlySum.set(currentDurIndex, 0.0);
//			
//			if (currentDur!=this.getTimePeriodLength("month")) {//if this is not last month
//				double nextMonthAmount = this.monthlySum.get(currentDurIndex + 1);
//				this.monthlySum.set(currentDurIndex + 1, nextMonthAmount+currentLoanAmount);
//			}
//			//Todo: else notify user the transaction amount exceeded necessary
//		}
	}
	
	private double getAverageRepayment() {
		int currentDur = (int) super.getCurrentDuration();
		double average = Math.floor(this.currentAmount / currentDur);
		
		return average;
	}

	private boolean predictChanceOfPayment() {
//		double currentLoanAmount = this.getCurrentLoanAmount();

		int dur = (int) super.getDuration();
		int currentDur = (int) super.getCurrentDuration();

		int moreMonths = calculateRemainigTime();

		return (currentDur+moreMonths < dur) ? true : false;
	}
	
	public double getAverageLoanRepayment() {
//		double currentLoanAmount = this.getCurrentLoanAmount();
		int currentDur = (int) super.getCurrentDuration();
		double avg = Math.floor(this.currentAmount / currentDur);
//		System.out.println("avgLoanRepayment "+avg);
		return avg;
	}
	
	private int calculateRemainigTime() {
		if (this.goalAmount < this.currentAmount)
			return 0;
		
		double avgRepaid = this.getAverageRepayment();
		int remaining = (int) Math.ceil((this.goalAmount - this.currentAmount) / avgRepaid);
//		System.out.println("avgRepaid "+avgRepaid);
//		System.out.println("goalAmount "+this.goalAmount);
//		System.out.println("currentAmount "+this.currentAmount);
//		System.out.println("remaining "+remaining);
		return remaining;
	}
	
	@Override
	public String getPlan() {//for display
//		double currentLoanAmount = this.getCurrentLoanAmount();

//		Map<String, Double> hist = new HashMap<String, Double>();
		int dur = (int) super.getDuration();
//		LocalDate date = LocalDate.parse(super.getTimePeriod().get(0));
//		for (int i=0; i<dur; ++i) {
//			hist.put(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), monthlySum.get(i));
//			date = date.plusMonths(1);
//		}

		String summary, advice;
		int currentDur = (int) super.getCurrentDuration();
		if (currentDur<30)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double adjustAmount;
			double avgRepaid = this.getAverageLoanRepayment();
			int moreTime = this.calculateRemainigTime();

			summary = String.format("From the last %d day(s), you repaid $%.0f on average per day. "
									+ "You will need %d more day(s) to settle the loan. ",
									currentDur, avgRepaid, moreTime);
			
			LocalDate endDate = LocalDate.parse(super.getTimePeriod().get(1));
			String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
			
			boolean likely = predictChanceOfPayment();
			if (!likely) {
				adjustAmount = Math.ceil(this.currentAmount / (dur - currentDur));
				advice = String.format("You are not likely to accomplish this loan plan by %s as expected."
									+ " Save $%.0f daily from now on!", endDateString, adjustAmount);
			}
			else {
				advice = String.format("You are very likely to accomplish this loan plan by %s as expected.", endDateString);
			}
			summary += advice;
		}

//		Map<String, Object> plan = new HashMap<String, Object>();
//		plan.put("total", this.goalAmount);
//		plan.put("current", currentLoanAmount);
//		plan.put("history", hist);
//		plan.put("summary", summary);
		return summary;
	}
}
