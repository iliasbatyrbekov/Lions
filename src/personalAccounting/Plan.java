package personalAccounting;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;

public class Plan {
	private String planID;
	private String planName;
	private DateTimeFormatter formatter;
	private ArrayList<LocalDate> timePeriod;
	private static int idGenSeed = 0;

	public Plan(String planName, ArrayList<String> timePeriodString) {
		this.planName = planName;
		this.planID = Integer.toString(idGenSeed++);
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // to also take in time, use "yyyy-MM-dd'T'HH:mm:ss"
		this.timePeriod = new ArrayList<>();
		this.setTimePeriod(timePeriodString);
	}

	public String getPlanID() {
		return planID;
	}
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	public ArrayList<String> getTimePeriod() {
		ArrayList<String> timePeriodString = new ArrayList<>();
		for (LocalDate date : this.timePeriod) {
			timePeriodString.add(formatter.format(date));
		}
		return timePeriodString;
	}
	
	public void setTimePeriod(ArrayList<String> timePeriodString) {
		if (!this.timePeriod.isEmpty())
			this.timePeriod.clear();

		for (String dateString : timePeriodString) {		
			this.timePeriod.add(LocalDate.parse(dateString, this.formatter));
		}
	}
	
	public boolean isBetween(String dateString) {//check whether input date is within plan period
		LocalDate startDate = this.timePeriod.get(0);
		LocalDate endDate = this.timePeriod.get(1);
		LocalDate transactionDate = LocalDate.parse(dateString);
		
		if (transactionDate.compareTo(startDate)==-1 || transactionDate.compareTo(endDate)==1)
			return false;
		return true;
	}
	
	public long getDuration(String untilDate) {//duration between startDate and endDate
		long days;
		if (untilDate.isEmpty())// if no specific date, return total number of days of plan
			days = ChronoUnit.DAYS.between(this.timePeriod.get(0), this.timePeriod.get(1));
		else {
			days = ChronoUnit.DAYS.between(this.timePeriod.get(0), LocalDate.parse(untilDate));
		}
		return days;
	}
	
	public void updatePlan(Transaction transaction) {;}

	public String getPlan() {return "";}
		
	public ArrayList<LocalDate> getTimePeriodDatesArr(){
		return timePeriod;
	}
	
	public static void listAll(ArrayList<Plan> planList) {
		for(Plan plan : planList) {
			plan.getPlan();

			System.out.printf("%-10s %-20s %-20s %-20s\n",
				"Plan ID", "Plan Name", "Start Date", "End Date");
			System.out.printf("%-10s %-20s %-20s %-20s \n",
				plan.planID, plan.planName, plan.timePeriod.get(0), plan.timePeriod.get(1));
		}
	}
}
