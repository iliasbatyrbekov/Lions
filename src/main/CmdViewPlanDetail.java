package main;

public class CmdViewPlanDetail {
	public void execute(String[] cmdParts) {
		User u = User.getInstance();
		u.getPlanDetailInfo(cmdParts[1]);
	}
}
