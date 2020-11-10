package main;

// import java.util.Collections;
// import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoanPlan extends Plan{
	private double loanAmount;
	private double interestRate;
	private double monthlyAmount;
	private ArrayList<Double> log; //[month: loanAmount]
	private String debtOwner;
	
	public LoanPlan(String planName, ArrayList<String> timePeriod,double loanAmount, double interestRate, String debtOwner) {
		super(planName, timePeriod);
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.monthlyAmount = this.calculateMonthlyAmount();
		this.log = new ArrayList<Double>();//(Collections.nCopies(this.getTimePeriodLength("month"), this.calculateMonthlyAmount()))
		for (int i=0;i<this.getTimePeriodLength("month");++i)
			this.log.add(monthlyAmount);
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
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	public double calculateMonthlyAmount() {
		double monthRate = this.interestRate/12;
		double noOfMonths = Math.ceil(super.getTimePeriodLength("month"));
		double factor = monthRate*Math.pow(1+monthRate, noOfMonths) / (Math.pow(1+monthRate, noOfMonths)-1);
		return this.loanAmount*factor;
	}

	public void updatePlan(double repayment) {
		double updatedAmount = this.monthlyAmount - repayment;
		double currentMonth = this.getCurrentPointInTime("month");// e.g. 3rd month
		
		if (0<updatedAmount)//monthlyAmount > repayment
			this.log.set((int) currentMonth, updatedAmount);
		else {
			this.log.set((int) currentMonth, 0.0);
			
			if (currentMonth!=this.getTimePeriodLength("month")) {//if this is last month
				double nextMonthAmount = this.log.get((int) currentMonth+1);
				this.log.set((int) currentMonth+1, nextMonthAmount+updatedAmount);
			}
			//Todo: else notify user the transaction amount exceeded necessary
		}
	}

	public void getPlan() {//for display
		double currentLoanAmount = 0;
		for (double loanAmount : this.log)
			currentLoanAmount += loanAmount;

		Map<String, Double> hist = new HashMap<String, Double>();
		LocalDate month = LocalDate.parse(super.getTimePeriod().get(0));
		for (int i=0; i<super.getTimePeriodLength("month"); ++i) {
			hist.put(month.toString(), log.get(i));
			month = month.plusMonths(1);
		}

		//summary

		Map<String, Object> plan = new HashMap<String, Object>();
		plan.put("total", this.loanAmount);
		plan.put("current", currentLoanAmount);
		plan.put("history", hist);
		// plan.put("summary", );
	}
}
