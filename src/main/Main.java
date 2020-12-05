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
		System.out.println("Please select the accounting system to use (personal/corporate): ");
		if (in.next().equals("corporate")) {
			CorporateMain.corporateMain();
		} else {
			PersonalMain.personalMain();
		}
		in.close();
	}

}
