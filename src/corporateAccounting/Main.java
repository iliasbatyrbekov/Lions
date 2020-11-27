package corporateAccounting;

import java.util.Scanner;  // Import the Scanner class
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import main.Transaction;

public class Main {

	public static void main(String[] args) throws ParseException, FileNotFoundException {
		Company myCompany = new Company();
		
		// TODO Auto-generated method stub
		boolean programFinished = false;
		//Scanner in = new Scanner(new File("C:\\Users\\hsiangtai2\\Desktop\\Lions\\src\\corporateAccounting\\in2.txt"));
		//Scanner in = new Scanner(new File("C:\\Users\\tai10\\Desktop\\CS3343 project\\Lions\\src\\corporateAccounting\\in2.txt"));
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to our accounting system. It provides functions such as recording tractions, inventory purchases, recording inventory sales, viewing balance sheet/journal/storage, etc. Please specify which service you need by entering the corresponding command. Type \"help\" to see the full list of available commands.");
		String allCommands = "==========================================================================\nREC: record transaction\n";
		allCommands += "B: purchase inventory\n";
		allCommands += "S: sell inventory\n";
		allCommands += "CLS: perform closing entry\n";
		allCommands += "J: view the journal (chronological record of all transactions)\n";
		allCommands += "TL: view the T ledger of a specific account\n";
		allCommands += "BS: view the balance sheet\n";
		allCommands += "ST: view the storage entries\n";
		allCommands += "Q: quit the program\n";
		allCommands += "HELP: view the list of available commands\n==========================================================================";
		while (!programFinished) {
			System.out.println(">> ");
			switch (in.next().toUpperCase()) {
				case "REC":
					System.out.println("Please enter the date of the transaction (dd/mm/yyyy), the account to debit, the account to credit, the amount to debit/credit, and a short description regarding the transaction: ");
					CompanyTransaction transaction = new CompanyTransaction(myCompany.generateNewID(), new SimpleDateFormat("dd/MM/yyyy").parse(in.next()), in.next(), in.next(), Double.parseDouble(in.next()), in.nextLine());
					if (myCompany.isValidTransaction(transaction)) {
						myCompany.recordTransaction(transaction);
					} else {
						System.out.println("Invalid transaction.");
					}
					break;
				case "B":
					System.out.println("Please enter the unit cost, units to buy, the date of the purchase, and the account to credit: ");
					myCompany.purchaseInventory(in.nextDouble(), in.nextInt(), new SimpleDateFormat("dd/MM/yyyy").parse(in.next()), in.next());
					break;
				case "S":
					System.out.println("Please enter the unit price, units to sell, the date of the sale, the account to debit, and the cost method (LIFO/FIFO): ");
					myCompany.sellInventory(in.nextDouble(), in.nextInt(), new SimpleDateFormat("dd/MM/yyyy").parse(in.next()), in.next(), in.next());
					break;
				case "J":
					myCompany.printJournal();
					break;
				case "CLS":
					System.out.println("Please enter the date to perform closing entry: ");
					myCompany.generateClosingEntries(new SimpleDateFormat("dd/MM/yyyy").parse(in.next()));
					break;
				case "TL":
					System.out.println("Please enter the name of the account to print");
					myCompany.getAccountList().get(in.next()).printTLedger();
					break;
				case "BS":
					myCompany.printBalanceSheet();
					break;
				case "ST":
					myCompany.printStorage();
					break;
				case "Q":
					programFinished = true;
					break;
				case "HELP":
					System.out.println(allCommands);
					break;
				default:
					System.out.println("Invalid input, please refer to the following list of available commands: ");
					System.out.println(allCommands);
					break;
			}
		}
	}

}
