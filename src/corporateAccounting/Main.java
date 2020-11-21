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
		Scanner in = new Scanner(new File("C:\\Users\\hsiangtai2\\Desktop\\Lions\\src\\corporateAccounting\\in.txt"));
		while (!programFinished) {
			System.out.println("Welcome to our accounting system. It provides three main functions, namely [A] recording tractions, [B] recording inventory purchases, and [C] recording inventory sales. Please specify which service you need by entering the corresponding letter [A/B/C]: ");
			switch (in.next()) {
				case "A":
					System.out.println("Please enter the date of the transaction (dd/mm/yyyy), the account to debit, the account to credit, the amount to debit/credit, and a short description regarding the transaction: ");
					CompanyTransaction transaction = new CompanyTransaction(myCompany.generateNewID(), new SimpleDateFormat("dd/MM/yyyy").parse(in.next()), in.next(), in.next(), Double.parseDouble(in.next()), in.nextLine());
					if (myCompany.isValidTransaction(transaction)) {
						myCompany.recordTransaction(transaction);
						System.out.println("Transaction successfully added.");
					} else {
						System.out.println("Invalid transaction.");
					}
					break;
				case "B":
					System.out.println("Please enter the unit cost, units to buy, the date of the purchase, and the account to credit: ");
					myCompany.purchaseInventory(in.nextDouble(), in.nextInt(), new SimpleDateFormat("dd/MM/yyyy").parse(in.next()), in.next());
					break;
				case "C":
					System.out.println("Please enter the unit price, units to sell, the date of the sale, the account to debit, and the cost method (LIFO/FIFO): ");
					myCompany.sellInventory(in.nextDouble(), in.nextInt(), new SimpleDateFormat("dd/MM/yyyy").parse(in.next()), in.next(), in.next());
					break;
				case "D":
					myCompany.printJournal();
					break;
				case "E":
					System.out.println("Please enter the name of the account to print");
					myCompany.getAccountList().get(in.next()).printTLedger();
					break;
				case "F":
					System.out.println("Please enter the date to perform closing entry: ");
					myCompany.generateClosingEntries(new SimpleDateFormat("dd/MM/yyyy").parse(in.next()));
					break;
				case "G":
					myCompany.printBalanceSheet();
					break;
				case "H":
					myCompany.printStorage();
					break;
				case "Z":
					programFinished = true;
					break;
				//default:
				//	System.out.println("Invalid input, please enter again [A/B/C]: ");
			}
		}
	}

}
