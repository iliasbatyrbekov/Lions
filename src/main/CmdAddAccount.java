package main;

public class CmdAddAccount {
	public void execute(String[] cmdParts) {
		
		User u = User.getInstance();
		String type = cmdParts[1];
		
		
		try {
			double balance = Double.parseDouble(cmdParts[3]);
			double debt = Double.parseDouble(cmdParts[4]);
			double interest = Double.parseDouble(cmdParts[5]);
			u.addAccount(cmdParts[1], cmdParts[2], balance, debt, interest, cmdParts[6]);
			
		} catch (ExAccountTypeNotSupported e) {
			e.printStackTrace();
		}
	}
}
