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
	private double loanAmount;
	private double interestRate;
	private double monthlyPayment;
	private ArrayList<Transaction> tranRecord;
	private ArrayList<Double> monthlySum; //[0] monthlyPayment of 1st month, [1] monthlyPayment of 2nd month, ...
	private String debtOwner;
	
	public LoanPlan(String planName, ArrayList<String> timePeriod,double loanAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.tranRecord = new ArrayList<Transaction>();

		this.monthlySum = new ArrayList<Double>();//(Collections.nCopies(this.getTimePeriodLength("month"), this.calculateMonthlyAmount()))
		for (int i=0;i<this.getTimePeriodLength("month");++i)
			this.monthlySum.add(this.calculateMonthlyPayment());
		
		this.debtOwner = debtOwner;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
		this.monthlyPayment = calculateMonthlyPayment();
		//if insufficient past...
	}
	public void addTransaction(Transaction transaction) {
		this.tranRecord.add(transaction);
	}
	public String getDebtOwner() {
		return debtOwner;
	}
	public void setDebtOwner(String debtOwner) {
		this.debtOwner = debtOwner;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
		this.monthlyPayment = calculateMonthlyPayment();
		//if insufficient past...
	}
	public double getCurrentLoanAmount() {
		double currentLoanAmount = 0;
		for (Transaction tran : this.tranRecord) {
			currentLoanAmount += tran.getAmount();
		}
		return currentLoanAmount;
	}
	
	public double calculateMonthlyPayment() {//monthly loan payment
		double monthRate = this.interestRate/12;
		double noOfMonths = Math.ceil(super.getTimePeriodLength("month"));
		double factor = monthRate*Math.pow(1+monthRate, noOfMonths) / (Math.pow(1+monthRate, noOfMonths)-1);
		return this.loanAmount*factor;
	}

	public void updatePlan(Transaction transaction) {
		this.addTransaction(transaction);

		double currentMonth = this.getCurrentPointInTime("month");// e.g. 1st month, 3rd month
		int currentMonthIndex = (int) currentMonth - 1;// e.g. [0] == 1st month [2] == 3rd month
		double currentLoanAmount = this.monthlySum.get(currentMonthIndex) - transaction.getAmount();
		
		if (0<currentLoanAmount)//monthlyAmount > repayment
			this.monthlySum.set(currentMonthIndex, currentLoanAmount);
		else {
			this.monthlySum.set(currentMonthIndex, 0.0);
			
			if (currentMonth!=this.getTimePeriodLength("month")) {//if this is not last month
				double nextMonthAmount = this.monthlySum.get(currentMonthIndex + 1);
				this.monthlySum.set(currentMonthIndex + 1, nextMonthAmount+currentLoanAmount);
			}
			//Todo: else notify user the transaction amount exceeded necessary
		}
	}

	private boolean predictChanceOfPayment() {
		double currentLoanAmount = this.getCurrentLoanAmount();

		int noOfMonths = (int) super.getTimePeriodLength("month");
		int currentMonth = (int) super.getCurrentPointInTime("month");

		double avgRepaid = Math.floor((this.loanAmount - currentLoanAmount) / currentMonth);
		int moreMonths = (int) Math.ceil(currentLoanAmount / avgRepaid);

		return (noOfMonths < currentMonth+moreMonths) ? false : true;
	}
	@Override
	public String getPlan() {//for display
		double currentLoanAmount = this.getCurrentLoanAmount();

		Map<String, Double> hist = new HashMap<String, Double>();
		int noOfMonths = (int) super.getTimePeriodLength("month");
		LocalDate date = LocalDate.parse(super.getTimePeriod().get(0));
		for (int i=0; i<noOfMonths; ++i) {
			hist.put(date.format(DateTimeFormatter.ofPattern("MMM yyyy")), monthlySum.get(i));
			date = date.plusMonths(1);
		}

		String summary, advice;
		int currentMonth = (int) super.getCurrentPointInTime("month");
		if (currentMonth<1)
			summary = "Not enough data to generate summary. Please check again after a while...";
		else {
			double adjustAmount;
			double avgRepaid = Math.floor((this.loanAmount - currentLoanAmount) / currentMonth);
			int moreMonths = (int) Math.ceil(currentLoanAmount / avgRepaid);

			summary = String.format("From the last %d month(s), you repaid $%f on average per month. "
									+ "You will need %d more month(s) to settle the loan. ",
									currentMonth, avgRepaid, moreMonths);
			
			LocalDate endDate = LocalDate.parse(super.getTimePeriod().get(1));
			String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
			
			boolean likely = predictChanceOfPayment();
			if (!likely) {
				adjustAmount = Math.ceil(currentLoanAmount / (noOfMonths - currentMonth));
				advice = String.format("You are not likely to accomplish this loan plan by %s as expected. "
									+ "save $%f monthly from now on!", endDateString, adjustAmount);
			}
			else {
				advice = String.format("You are very likely to accomplish this loan plan by %s as expected. ", endDateString);
			}
			summary += advice;
		}

		Map<String, Object> plan = new HashMap<String, Object>();
//		plan.put("total", this.loanAmount);
//		plan.put("current", currentLoanAmount);
//		plan.put("history", hist);
		plan.put("summary", summary);
		return summary;//todo
	}
}
