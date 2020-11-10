package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class BudgetPlan extends Plan {

	private Map<String, Double> actualExpense;
	private Map<String, Double> goalAmount;
	private ArrayList<String> members;
	
	public BudgetPlan(String planName, 
			ArrayList<String> timePeriod, 
			Map<String, Double> actualExpense, 
			Map<String, Double> goalAmount, 
			ArrayList<String> members){
		
		super(planName, timePeriod);
		this.actualExpense = actualExpense;
		this.goalAmount = goalAmount;
		this.members = members;
	}
	
	public Map<String, Double> getGoalAmount() {
		return goalAmount;
	}
	public void setGoalAmount(Map<String, Double> goalAmount) {
		this.goalAmount = goalAmount;
	}
	public Map<String, Double> getActualExpense() {
		return actualExpense;
	}
	public void setActualExpense(Map<String, Double> actualExpense) {
		this.actualExpense = actualExpense;
	}
	public ArrayList<String> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	
}
