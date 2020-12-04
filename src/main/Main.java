package main;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String [] args) throws FileNotFoundException{

		Scanner inputFileName = new Scanner(System.in);
		System.out.print("Please input the file pathname: ");
		String filepathname = inputFileName.nextLine();
		Scanner inFile = new Scanner(new File(filepathname));
		while(inFile.hasNext()) {
			String cmdLine = inFile.nextLine().trim();
			if(cmdLine.contentEquals("")) continue;
			System.out.println("\n> " + cmdLine);
			User u = User.getInstance();
			
			String[] cmdParts = cmdLine.split("\\|");
			if(cmdParts[0].equals("RecordTransaction"))
				(new CmdRecordTransaction()).execute(cmdParts);
			else if(cmdParts[0].equals("ListTransactions"))
				u.listAllTransactionRecords();
			else if (cmdParts[0].equals("AddPlan"))
				(new CmdAddPlan()).execute(cmdParts);
			else if (cmdParts[0].equals("ListAllPlan"))
				u.listAllPlan();
			else if (cmdParts[0].equals("ViewPlanDetail"))
				(new CmdViewPlanDetail()).execute(cmdParts);
			else if (cmdParts[0].equals("AddAccount"))
				(new CmdAddAccount()).execute(cmdParts);
			else if (cmdParts[0].equals("ListAllAccount"))
				u.listAllAccounts();
			else if (cmdParts[0].equals("ViewExpenseByAccount"))
				u.viewExpenseByAccount();
			else if (cmdParts[0].equals("ViewExpenseByCategory"))
				u.viewExpenseByCategory();
			else if (cmdParts[0].equals("ViewExpenseByMember"))
				u.viewExpenseByMember();
			else if (cmdParts[0].equals("SavingPlanGetAverageSaving"))
				(new CmdSavingPlanGetAverageSaving()).execute(cmdParts);
		}
		
		if (inFile!=null) inFile.close();
		inputFileName.close();
	}
}