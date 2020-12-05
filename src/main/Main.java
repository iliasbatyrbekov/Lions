package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import corporateAccounting.*;
import personalAccounting.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParseException, NumberFormatException, ExAccountTypeNotSupported, ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			System.out.print("                                                                          `--..``   .:.             \r\n" + 
					"                                                                     `.:+oyNNNmddhyyyNNh+`          \r\n" + 
					"                                                           `.-/os+/-`.-+MMMMMMMMMMMMMMMMMm-         \r\n" + 
					"                                   ``..-://///:-..`        .-ymNMMMNdyosMMMMMMMMMMMMMMMMMMm+.       \r\n" + 
					"                             `.-/ohdmNMMMMMMMMMMMNdho-`      yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNho:.`` \r\n" + 
					"                         `.+ydNMMMMMMMMMMMMMMMMMMMMMMMds/:.:/NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMmdy\r\n" + 
					" `                   `.+ymMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo\r\n" + 
					"oN/               `:sdMMMMMNy//yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN \r\n" + 
					"MMd`          `-+hNMMMMMNh+`   `MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN: \r\n" + 
					"mMMd/.````-/shNMMMMMNho:`      `hMMMMMMMMMMMMMMM                      MMMMMMMMMMMMMMMMMMNNNMMMMM` \r\n" + 
					".dMMMMNNNMMMMMNdyo:.             -yMMMMMMMMMMMMM       LIONS3         MMMMMMMMMMMMMMMMMMMy.  .:+o+  \r\n" + 
					"  -+ssssso+/-`                     :NMMMMMMMMMMM                      MMMMMMMMMMMMMMMNy:           \r\n" + 
					"                                    sMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo/-              \r\n" + 
					"                                    yMMMMMMmMMMMMMdNMMMMMMMMMMMMMMMMMMMMMMMMMdosNMy                 \r\n" + 
					"                                   +MMMMMMd.MMMMMMd./ymMMMMMMMMMMMMMMMMMMMMMM/  s/                  \r\n" + 
					"                                  :MMMMMMM::MMMMMMMNho/hMMMMMMMMMMMMMMMMm+-:o-                      \r\n" + 
					"                                   :yNMMMMd/ohhhmNMMMMMMMMMMMMMMMMMMMMMo`                           \r\n" + 
					"                                     `/hMMMMNmddhy/oMMMMMMMMMMMMMs--//.                             \r\n" + 
					"                                        .smMMMMMMMNdMMMMMMMMMMMm:                                   \r\n" + 
					"                                          `.-::/oMMMMMMMMMMMMNs.                                    \r\n" + 
					"                                                -MMMMMmddddho-                                      \r\n" + 
					"                                                /MMMMN`````                                         \r\n" + 
					"                                               `hMMMMMy:.                                           \r\n" + 
					"                                               `odNMMMmdo                                           \r\n" + 
					"                                                 `.:/:.`                                            \r\n" + 
					"                                                                                                    \r\n" + 
					"");
			System.out.printf("%75s\n", "Welcome To Lions3 Finance Management System!");
			System.out.printf("%85s\n", "Please select the accounting system to use ([P] personal/ [C] corporate/ [E] exit):");
			String input = in.nextLine().trim();
			if (input.equals("C")) {
				Scanner corporateIn = new Scanner(System.in);
				System.out.println("Welcome to our Lions3 Corporation Accounting System!!"
						+ "\nIt provides functions such as recording tractions, inventory purchases, recording inventory sales, viewing balance sheet/journal/storage, etc."
						+ "\nPlease select whether to use our cli (command line interface) service or to directly input the commands via a text file of testcases: ([CLI] cli / [T] textfile) ");
				if (in.next().toUpperCase().equals("T")) {
					System.out.println("Please enter the filename of the text file: ");
					corporateIn = new Scanner(new File(in.next()));
				}
				CorporateMain.corporateMain(corporateIn);
			} else if (input.toUpperCase().equals("P")){
				PersonalMain.personalMain();
			} else if (input.toUpperCase().equals("E")){
				exit = true;
			} else {
				System.out.println("Please input the correct command ([P] personal/ [C] corporate/ [E] exit):");
			}
		}
		in.close();
	}

}
