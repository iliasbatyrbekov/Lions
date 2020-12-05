package test;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personalAccounting.ExAccountNotExist;
import personalAccounting.ExPlainNotExist;
import personalAccounting.ExUpdateBalanceErr;
import personalAccounting.Income;
import personalAccounting.Transaction;
import personalAccounting.User;

public class TestTransaction {
	
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
	public void testAddTransaction() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		User u = User.getInstance();
		
		Income in = new Income(1, 500.0, "0", "NA", "2020-11-18", "Car");
		u.getTransactionList(null, null).add(in);
		Transaction t = u.findTransactionRecord(1);
		assertEquals(t, in);
	}
	
	@Test
	public void listAllTransaction() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		Transaction t = new Transaction(1, 100.0, "1", "Description", "2020-10-29");
		User u = User.getInstance();
		ArrayList<Transaction> tr = u.getTransactionList("2020-10-12", "2021-12-12");
		t.listAll(tr);
		assertEquals(outContent.toString(), "%-15s %-20s %-20s %-30s %-20s\n Transaction ID  Amount Account ID Description Create Date");
	}
	
	@Test
	public void searchTransaction() {
		Transaction t = new Transaction(1, 100.0, "1", "Description", "2020-10-29");
		User u = User.getInstance();
		ArrayList<Transaction> tr = u.getTransactionList("2020-10-12", "2021-12-12");
		Transaction outT = t.searchTransaction(tr, 1);
		assertEquals(outT, t);
	}
}
