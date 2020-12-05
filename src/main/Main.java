package main;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import corporateAccounting.*;
import personalAccounting.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParseException, NumberFormatException, ExAccountTypeNotSupported, ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
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
		System.out.printf("%85s\n", "Please select the accounting system to use (personal/corporate):");
		if (in.next().equals("corporate")) {
			CorporateMain.corporateMain();
		} else {
			PersonalMain.personalMain();
		}
		in.close();
	}

}
