package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import corporateAccounting.*;
import personalAccounting.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Please select the accounting system to use (personal/corporate): ");
		if (in.next().equals("corporate")) {
			System.out.println("Welcome to our accounting system. It provides functions such as recording tractions, inventory purchases, recording inventory sales, viewing balance sheet/journal/storage, etc. Please select whether to use our cli (command line interface) service or to directly input the commands via a text file of testcases: (cli/textfile) ");
			if (in.next().equals("textfile")) {
				System.out.println("Please enter the filename of the text file: ");
				in = new Scanner(new File(in.next()));
			}
			CorporateMain.corporateMain(in);
		} else {
			PersonalMain.personalMain();
		}
	}

}
