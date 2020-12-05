package personalAccounting;
import java.io.*;
import java.text.ParseException;
import java.util.*;

//import corporateAccounting.*;

public class PersonalMain {

	public static void personalMain() throws FileNotFoundException, ParseException, NumberFormatException, ExAccountTypeNotSupported, ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while(!exit) {
			User u = User.getInstance();
			System.out.printf("\n==============================================================================="
					+ "\n\nWelcome to Lions3 personal fincance management system!");
			System.out.printf("\n\n---------------------------------------------REFERENCE------------------------------------------\n"
					+ "%-40s | %-40s \n%-40s | %-40s | %-40s\n%-40s | %-40s | %-40s\n%-40s | %-40s | %-40s\n%-40s | %-40s\n"
					+ "------------------------------------------------------------------------------------------------\n\n\nPlease specify which function you will like to use:  ",
					"[T] Input Text File", "[HOME] return to home page", "[AA] Add Account", "[AP] Add Plan", "[RC] Record Transaction",
					"[LA] List All Account", "[LP] List All Plan", "[LT] List All Transaction",
					"[VEA] View Expense by Account", "[VEC] View Expense by Category", "[VEM] View Expense by Member",
					"[VP] View Plan Detail", "[CAS] Calculate Average Saving");
			String ui = in.next();
			if(ui.equals("HOME")) { exit = true; }
			else if(ui.equals("T")) {
				System.out.print("Please input the file pathname: ");
				String filepathname = in.next();
				Scanner inFile = new Scanner(new File(filepathname));
				while(inFile.hasNext()) {
					String cmdLine = inFile.nextLine().trim();
					if(cmdLine.contentEquals("")) continue;
					System.out.println("\n> " + cmdLine);
					
					
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
			}
			else {
				String cmdLine = "";
				String[] cmdParts;
				ArrayList<String> timePeriod = new ArrayList<>();
				switch(ui) {
					case "AP":
						System.out.printf("Please Input Plan Type: ([L] Loan Plan / [S] Saving Plan \n");
						switch (in.next()) {
							case "L":
								System.out.println("Please input with following format for Loan Plan: "
										+ "\nName|From Date|To Date|Goal Amount|Interest Rate|Debt Owner"
										+ "\tExample: LoanPlan1|2020-10-01|2021-02-01|20000.0|40.0|debtowner");
								in.nextLine();
								cmdLine = in.nextLine().trim();
								cmdParts = cmdLine.split("\\|");
								timePeriod.add(cmdParts[1]);
								timePeriod.add(cmdParts[2]);
								u.addLoanPlan(cmdParts[0], timePeriod, Double.parseDouble(cmdParts[3]), Double.parseDouble(cmdParts[4]), cmdParts[5]);
								break;
							case "S":
								System.out.println("Please input with following format for Saving Plan: "
										+ "\nName|From Date|To Date|Goal Amount|Description"
										+ "\tExample: SavingPlan1|2020-10-01|2021-02-01|100000.0|description");
								in.nextLine();
								cmdLine = in.nextLine().trim();
								cmdParts = cmdLine.split("\\|");
								timePeriod.add(cmdParts[1]);
								timePeriod.add(cmdParts[2]);
								u.addSavingPlan(cmdParts[0], timePeriod, Double.parseDouble(cmdParts[3]), cmdParts[4]);
								break;
						}
						break;
					case "AA":
						System.out.printf("Please Input Account Type: ([C] Cash Account / [S] Saving Account / [CC] Credit Card Account / [D] Debit Account\n");
						switch (in.next()) {
							case "C":
								System.out.println("Please input with following format for Cash Account: "
										+ "\nAccount ID|Balance"
										+ "\tExample: 1|1200");
								cmdLine = in.next().trim();
								cmdParts = cmdLine.split("\\|");
								u.addAccount("Cash", cmdParts[0], Double.parseDouble(cmdParts[1]), 0.0, 0.0, "");
								break;
							case "S":
								System.out.println("Please input with following format for Saving Account: "
										+ "\nAccount ID|Balance|Interest|Withdraw Date"
										+ "\tExample: 2|34000|0|0.9|2020-01-09");
								cmdLine = in.next().trim();
								cmdParts = cmdLine.split("\\|");
								u.addAccount("Saving", cmdParts[0], Double.parseDouble(cmdParts[1]), 0.0, Double.parseDouble(cmdParts[2]), cmdParts[3]);
								break;
							case "CC":
								System.out.println("Please input with following format for Credit Card Account: "
										+ "\nAccount ID|Balance"
										+ "\tExample: 0|1200");
								cmdLine = in.next().trim();
								cmdParts = cmdLine.split("\\|");
								u.addAccount("Cash", cmdParts[0], Double.parseDouble(cmdParts[1]), 0.0, 0.0, "");
								break;
							case "D":
								System.out.println("Please input with following format for Debit Account: "
										+ "\nAccount ID|Balance"
										+ "\tExample: 4|100");
								cmdLine = in.next().trim();
								cmdParts = cmdLine.split("\\|");
								u.addAccount("Cash", cmdParts[0], Double.parseDouble(cmdParts[1]), 0.0, 0.0, "");
								break;
						}
						break;
					case "RC":
						System.out.printf("Please Input Account Type: ([E] Expense / [RV] Transfer Receive / [RM] Transfer Remit / [I] Income\n");
						switch (in.next()) {
							case "E":
								System.out.println("Please input with following format for Expense Transaction: "
										+ "\nAmount|Account ID|Plan ID|Description|Date|Category|Member"
										+ "\tExample: -100|0|1|My girlfriend spent too much|2020-11-19|car|girlfriend");
								in.nextLine();
								cmdLine = in.nextLine().trim();
								cmdParts = cmdLine.split("\\|");
								u.addExpenseTransaciton("Expense", Double.parseDouble(cmdParts[0]), cmdParts[1], cmdParts[2], cmdParts[3], cmdParts[4], cmdParts[5], cmdParts[6]);
								break;
							case "I":
								System.out.println("Please input with following format for Income Transaction: "
										+ "\nAmount|Account ID|Plan ID|Description|Date|Category"
										+ "\tExample: 300|1|0|I earned too much|2020-11-13|salary");
								in.nextLine();
								cmdLine = in.nextLine().trim();
								cmdParts = cmdLine.split("\\|");
								u.addTransaction("Income", Double.parseDouble(cmdParts[0]), cmdParts[1], cmdParts[2], cmdParts[3], cmdParts[4], cmdParts[5]);
								break;
							case "RV":
								System.out.println("Please input with following format for Income Transaction: "
										+ "\nAmount|Account ID|Plan ID|Description|Date|Category"
										+ "\tExample: 500|0|1|My mom loved me|2020-11-11|null");
								in.nextLine();
								cmdLine = in.nextLine().trim();
								cmdParts = cmdLine.split("\\|");
								u.addTransaction("TransferReceive", Double.parseDouble(cmdParts[0]), cmdParts[1], cmdParts[2], cmdParts[3], cmdParts[4], cmdParts[5]);
								break;
							case "RM":
								System.out.println("Please input with following format for Income Transaction: "
										+ "\nAmount|Account ID|Plan ID|Description|Date|Category"
										+ "\tExample: -400|1|0|My gf again la|2020-11-17|null");
								in.nextLine();
								cmdLine = in.nextLine().trim();
								cmdParts = cmdLine.split("\\|");
								u.addTransaction("TransferRemit", Double.parseDouble(cmdParts[0]), cmdParts[1], cmdParts[2], cmdParts[3], cmdParts[4], cmdParts[5]);
								break;
						}
						break;
					case "LA":
						u.listAllAccounts();
						break;
					case "LP":
						u.listAllPlan();
						break;
					case "LT":
						u.listAllTransactionRecords();
						break;
					case "VEA":
						u.viewExpenseByAccount();
						break;
					case "VEC":
						u.viewExpenseByCategory();
						break;
					case "VEM":
						u.viewExpenseByMember();
						break;
					case "VP":
						System.out.println("Please Specify the Plan ID you want to view: ");
						u.getPlanDetailInfo(in.next().trim());
						break;
					case "CAS":
						System.out.println("Please input with following format: "
								+ "\nFrom Date|To Date|Amount"
								+ "\tExample: 2020-09-01|2021-02-01|1000");
						in.nextLine();
						cmdLine = in.nextLine().trim();
						cmdParts = cmdLine.split("\\|");
						timePeriod.add(cmdParts[0]);
				        timePeriod.add(cmdParts[1]);
						SavingPlan savingPlan = new SavingPlan("SavingPlan1", timePeriod, 10000.0, "Education");
						savingPlan.updatePlan(new Transaction(1234, Integer.parseInt(cmdParts[2]), "Account1", "Semester A", "2020-10-01"));

				        System.out.print("Your average saving from start date to today is " + "$" + savingPlan.getAverageSaving() + "/day");
				        break;
					default:
						System.out.println("Invalid input, please refer to the available commands listed in the reference!");
						break;
				}
						
			}
		}
	}
}