package personalAccounting;

public class CmdRecordTransaction {
	public void execute(String[] cmdParts) {
		User u = User.getInstance();
		double amount = Double.parseDouble(cmdParts[2]);
		
		try {
			if(cmdParts.length >= 9)
				u.addExpenseTransaciton(cmdParts[1], amount, cmdParts[3], cmdParts[4], cmdParts[5], cmdParts[6], cmdParts[7], cmdParts[8]);
			else
				u.addTransaction(cmdParts[1], amount, cmdParts[3], cmdParts[4], cmdParts[5], cmdParts[6], cmdParts[7]);
		}
		catch (ExPlainNotExist e) { e.printStackTrace(); }
		catch (ExAccountNotExist e) { e.printStackTrace(); }
		catch (ExUpdateBalanceErr e) { e.printStackTrace(); }
	}
}
