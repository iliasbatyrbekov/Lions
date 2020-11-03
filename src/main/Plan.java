package main;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Plan {
	private String planID;
	private String planName;
	private ArrayList<Transaction> tranRecord;
	private SimpleDateFormat formatter;
	private ArrayList<Date> timePeriod;	
	private static int idGenSeed = 0;

	public Plan(String planName, ArrayList<String> timePeriodString) {
		this.planName = planName;
		this.planID = Integer.toString(idGenSeed++);
		this.tranRecord = new ArrayList<>();
		this.formatter = new SimpleDateFormat("yyyy-MM-dd"); // with time: "yyyy-MM-dd'T'HH:mm:ss"
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
		ArrayList<String> timePeriodString = new  ArrayList<>();
		for (Date date : this.timePeriod) {
			timePeriodString.add(formatter.format(date));
		}
		return timePeriodString;
	}
	public void setTimePeriod(ArrayList<String> timePeriodString) {
		if (!this.timePeriod.isEmpty())
			this.timePeriod.clear();

		for (String dateString : timePeriodString) {
			try {
				timePeriod.add(formatter.parse(dateString));
			} catch (ParseException e) {
				System.out.println("Exception: wrong date format...");
				e.printStackTrace();
			}		
		}
	}
	
}
