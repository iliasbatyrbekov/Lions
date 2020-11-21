package main;

public class CmdAddPlan {
	public void execute(String[] cmdParts) {
		
		User u = User.getInstance();
		u.addPlan(cmdParts[1], cmdParts[2], cmdParts[3]);
	}
}
