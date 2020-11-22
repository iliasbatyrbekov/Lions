package main;

import java.util.ArrayList;
import java.util.Map;
// import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
// import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
//import java.time.Duration;
import java.text.ParseException;

public class Plan {
	private String planID;
	private String planName;
	// private ArrayList<Transaction> tranRecord;
	private DateTimeFormatter formatter;
	private ArrayList<LocalDate> timePeriod;
	private static int idGenSeed = 0;

	public Plan(String planName, ArrayList<String> timePeriodString) {
		this.planName = planName;
		this.planID = Integer.toString(idGenSeed++);
		// this.tranRecord = new ArrayList<Transaction>();
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
	// public void addTransaction(Transaction transaction) {
	// 	this.tranRecord.add(transaction);
	// }
	public ArrayList<String> getTimePeriod() {
		ArrayList<String> timePeriodString = new ArrayList<>();
		for (LocalDate date : this.timePeriod) {
			timePeriodString.add(formatter.format(date));
		}
		return timePeriodString;
	}
	
	public ArrayList<LocalDate> getTimePeriodDatesArr() {
		return timePeriod;
	}
	
	public void setTimePeriod(ArrayList<String> timePeriodString) {
		if (!this.timePeriod.isEmpty())
			this.timePeriod.clear();

		for (String dateString : timePeriodString) {		
			this.timePeriod.add(LocalDate.parse(dateString, this.formatter));
		}
	}
	
	public long getDuration() {//duration between startDate and endDate
//		Period period = Period.between(this.timePeriod.get(0), this.timePeriod.get(1));
//		return period.getDays();
		
		long days = ChronoUnit.DAYS.between(this.timePeriod.get(0), this.timePeriod.get(1));
		return days;
		
//		if (mode=="day")
//			return period.getDays();
//		else //mode=="month"
//			return period.getMonths();
	}

	public long getCurrentDuration() {//duration between startDate and now()
//		Period period = Period.between(this.timePeriod.get(0), LocalDate.now());
//		return period.getDays();
		
		long days = ChronoUnit.DAYS.between(this.timePeriod.get(0), LocalDate.now());
		return days;
		
//		if(mode=="day")
//			return period.getDays();
//		else
//			return period.getMonths();
	}

	public String getPlan() {return "getPlan() works";};

	public static void listAll(ArrayList<Plan> planList) {
//		System.out.printf("%-30s %1.7f %-20s %-30s %-20s:", "Transaction ID", "Amount", "Account ID", "Description", "Create Date");
		for(Plan plan : planList) {
//			Map<String, Object> detail = plan.getPlan();
			plan.getPlan();
//			Map<String, Double> tranHist = (Map<String, Double>) detail.get("hist");

			System.out.printf("%-10s %-20s %-20s %-20s\n",
				"Plan ID", "Plan Name", "Start Date", "End Date");
			System.out.printf("%-10s %-20s %-20s %-20s \n",
				plan.planID, plan.planName, plan.timePeriod.get(0), plan.timePeriod.get(1));
			
//			 for (Map<String, Double> record : tranHist) {
//			 	System.out.printf("%-20s %-20s:");
//			 }
//			System.out.print(detail.get("summary"));
		}

		//id name startDate endDate  
		//%1.7f
	}
}
