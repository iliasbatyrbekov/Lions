package corporateAccounting;

import java.util.Scanner;  // Import the Scanner class
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import main.Transaction;

public class Main {

	public static void main(String[] args) throws ParseException {
		Company myCompany = new Company();
		
		// TODO Auto-generated method stub
		System.out.println("Welcome to our accounting system. It provides three main functions, namely [A] recording tractions, [B] recording inventory purchases, and [C] recording inventory sales. Please specify which service you need by entering the corresponding letter [A/B/C]: ");
		Scanner in = new Scanner(System.in);
		switch (in.next()) {
			case "A":
				System.out.println("Please enter the date of the transaction (dd/mm/yyyy):");
				Date date = new SimpleDateFormat("dd/MM/yyyy").parse(in.next());
				System.out.println("Please enter the account to debit: ");
				String debAcc = in.next();
				System.out.println("Please enter the account to credit: ");
				String creAcc = in.next();
				System.out.println("Please enter the amount to debit/credit: ");
				double amount = Double.parseDouble(in.next());
				System.out.println("Please enter a short description regarding the transaction: ");
				String desc = in.next();
				CompanyTransaction transaction = new CompanyTransaction(myCompany.generateNewID(), date, debAcc, creAcc, amount, desc);
				if (myCompany.isValidTransaction(transaction)) {
					myCompany.recordTransaction(transaction);
					System.out.println("Transaction successfully added.");
				} else {
					System.out.println("Invalid transaction.");
				}
				
				break;
			case "B":
				System.out.println("Please enter the units ");
				break;
			case "C":
				System.out.println("C");
				break;
			//default:
			//	System.out.println("Invalid input, please enter again [A/B/C]: ");
		}
	}

}
