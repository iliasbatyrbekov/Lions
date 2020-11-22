package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import corporateAccounting.CompanyTransaction;
import main.Account;
import main.Transaction;

public class TestAccount {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 

	@Before
	public void setUpStreams() { 
	    System.setOut(new PrintStream(outContent)); 
	}

	@After
	public void cleanUpStreams() { 
	    System.setOut(null); 
	}
	
	@Test
	public void testListAll2() {
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		allAccounts.add(new Account("1", 0.0, 0.0));
		String tableFormat = "%-20s %-20s %-20s\n%-20s %-20f %-20f\n";
		String expString = String.format(tableFormat, "Account ID", "Debt", "Balance", "1", 0.0, 0.0);
		Account.listAll(allAccounts);
		assertEquals(expString, outContent.toString());
	}
	
	@Test
	public void testListAll1() {
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		allAccounts.add(new Account("1", 0.0));
		String tableFormat = "%-20s %-20s %-20s\n%-20s %-20f %-20f\n";
		String expString = String.format(tableFormat, "Account ID", "Debt", "Balance", "1", 0.0, 0.0);
		Account.listAll(allAccounts);
		assertEquals(expString, outContent.toString());
	}
	
	@Test
	public void testListAll3() {
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		allAccounts.add(new Account("1"));
		String tableFormat = "%-20s %-20s %-20s\n%-20s %-20f %-20f\n";
		String expString = String.format(tableFormat, "Account ID", "Debt", "Balance", "1", 0.0, 0.0);
		Account.listAll(allAccounts);
		assertEquals(expString, outContent.toString());
	}
	
	@Test
	public void testListAll4() {
		ArrayList<Account> allAccounts = new ArrayList<Account>();
		allAccounts.add(new Account("1"));
		allAccounts.get(0).getaccID();
		allAccounts.get(0).getBalance();
		allAccounts.get(0).getDebt();
		allAccounts.get(0).updateBalance(1.0);
		String tableFormat = "%-20s %-20s %-20s\n%-20s %-20f %-20f\n";
		String expString = String.format(tableFormat, "Account ID", "Debt", "Balance", "1", 0.0, 1.0);
		Account.listAll(allAccounts);
		assertEquals(expString, outContent.toString());
	}

}
