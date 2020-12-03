package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.Account;
import main.CashAccount;

public class TestCashAccount {
	
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
	public void testCashAccount() {
		Account account = new CashAccount("1", 0.0);
		assertEquals(-1, account.updateBalance(-10.0));
	}
	@Test
	public void testCashAccount1() {
		Account account = new CashAccount("1", 0.0);
		assertEquals(1, account.updateBalance(10.0));
	}
}
