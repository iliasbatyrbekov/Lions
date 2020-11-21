package main;

public class CmdAddAccount {
	public void execute(String[] cmdParts) {
		
		User u = User.getInstance();
		double balance = Double.parseDouble(cmdParts[3]);
		double asset = Double.parseDouble(cmdParts[4]);
		double debt = Double.parseDouble(cmdParts[5]);
		double interest = Double.parseDouble(cmdParts[6]);
		
		u.addAccount(cmdParts[1], cmdParts[2], balance, asset, debt, interest, cmdParts[7]);
	}
}
