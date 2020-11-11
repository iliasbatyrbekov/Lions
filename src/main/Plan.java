package main;

import java.util.ArrayList;
// import java.util.Date;
import java.time.LocalDate;
// import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.text.ParseException;

public class Plan {
	private String planID;
	private String planName;
	private ArrayList<Transaction> tranRecord;
	private DateTimeFormatter formatter;
	private ArrayList<LocalDate> timePeriod;	
	private static int idGenSeed = 0;

	public Plan(String planName, ArrayList<String> timePeriodString) {
		this.planName = planName;
		this.planID = Integer.toString(idGenSeed++);
		this.tranRecord = new ArrayList<>();
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
		ArrayList<String> timePeriodString = new  ArrayList<>();
		for (LocalDate date : this.timePeriod) {
			timePeriodString.add(formatter.format(date));
		}
		return timePeriodString;
	}
	public void setTimePeriod(ArrayList<String> timePeriodString) {
		if (!this.timePeriod.isEmpty())
			this.timePeriod.clear();

		for (String dateString : timePeriodString) {		
			timePeriod.add(LocalDate.parse(dateString, this.formatter));
		}
	}
	
	public long getTimePeriodLength(String mode) {
		Duration dur = Duration.between(this.timePeriod.get(0), this.timePeriod.get(1));
		
		if (mode=="day")
			return dur.toDays();
		else //mode=="month"
			return dur.toDays()/30;
	}

	public long getCurrentPointInTime(String mode) {
		Duration dur = Duration.between(this.timePeriod.get(0), LocalDate.now());

		if(mode=="day")
			return dur.toDays();
		else
			return dur.toDays()/30;
	}
}
