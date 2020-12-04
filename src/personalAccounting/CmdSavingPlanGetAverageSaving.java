package personalAccounting;
import java.util.ArrayList;

public class CmdSavingPlanGetAverageSaving {
	public void execute(String[] cmdParts) {
		ArrayList<String> timePeriod = new ArrayList<>();
        timePeriod.add(cmdParts[1]);
        timePeriod.add(cmdParts[2]);
		SavingPlan savingPlan = new SavingPlan("SavingPlan1", timePeriod, 10000.0, "Education");
		savingPlan.updatePlan(new Transaction(1234, Integer.parseInt(cmdParts[3]), "Account1", "Semester A", "2020-10-01"));

        System.out.print("Your average saving from start date to today is " + "$" + savingPlan.getAverageSaving() + "/day");
	}
}
