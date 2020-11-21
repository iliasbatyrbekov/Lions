package main;

public class CmdRecordTransaction {
	public void execute(String[] cmdParts) {
		
		User u = User.getInstance();
		double amount = Double.parseDouble(cmdParts[2]);
		
		try {
			u.addTransaction(cmdParts[1], amount, cmdParts[3], cmdParts[4], cmdParts[5], cmdParts[6]);
		}
		catch (ExPlainNotExist e) { e.printStackTrace(); }
		catch (ExAccountNotExist e) { e.printStackTrace(); }
	}
}
