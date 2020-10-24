package main;

import java.util.ArrayList;
import java.util.Date;

public class Plan {
	private String planID;
	private String planName;
	private ArrayList<Date> timePeriod;
	
	private static int idGenSeed = 0;
	
	public Plan(String planName, ArrayList<Date> timePeriod) {
		this.timePeriod = (ArrayList<Date>) timePeriod.clone();
		this.planName = planName;
		this.planID = Integer.toString(idGenSeed++);
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
	public ArrayList<Date> getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(ArrayList<Date> timePeriod) {
		this.timePeriod = timePeriod;
	}
	
}
