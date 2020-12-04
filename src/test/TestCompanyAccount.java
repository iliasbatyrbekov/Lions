/*package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import corporateAccounting.CompanyAccount;

public class TestCompanyAccount {
	
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
	public void testPrintTLedger1() {
		CompanyAccount.getAllInitAccounts().get("Cash").printTLedger();
		System.out.println("HEY");
		assertEquals("1", outContent.toString());
	}
}*/
package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import corporateAccounting.CompanyAccount;
import corporateAccounting.CompanyTransaction;
import personalAccounting.Account;
import personalAccounting.Transaction;

public class TestCompanyAccount {
	
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
	public void testPrintTLedger1() {
		CompanyAccount.getAllInitAccounts().get("Cash").printTLedger();
		String seperationLine = "\n--------------------------------------------------------------\n";
		String expected = String.format("%30s", "CASH") + seperationLine;
		expected += (String.format("%-30s | %-30s", "Debit", "Credit") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "TransID", "Amount","TransID","Amount") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "Total", 0.0, "", "") + seperationLine);
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintTLedger2() {
		CompanyAccount account = CompanyAccount.getAllInitAccounts().get("Notes-Payable");
		account.recordTransaction(new CompanyTransaction("123", new Date(), "Cash", "Notes-Payable", 2000.0, "Borrow money on note"));
		account.printTLedger();
		String seperationLine = "\n--------------------------------------------------------------\n";
		String expected = String.format("%30s", "NOTES-PAYABLE") + seperationLine;
		expected += (String.format("%-30s | %-30s", "Debit", "Credit") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "TransID", "Amount","TransID","Amount") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "", "", "123", 2000.0) + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "", "", "Total", 2000.0) + seperationLine);
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintTLedger3() {
		CompanyAccount account = CompanyAccount.getAllInitAccounts().get("Cash");
		account.recordTransaction(new CompanyTransaction("123", new Date(), "Cash", "Notes-Payable", 2000.0, "Borrow money on note"));
		account.printTLedger();
		String seperationLine = "\n--------------------------------------------------------------\n";
		String expected = String.format("%30s", "CASH") + seperationLine;
		expected += (String.format("%-30s | %-30s", "Debit", "Credit") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "TransID", "Amount","TransID","Amount") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "123", 2000.0, "", "") + seperationLine);
		expected += (String.format("%-15s|%-15s|%-15s|%-15s", "Total", 2000.0, "", "") + seperationLine);
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testRecordTransaction1() {
		CompanyAccount account = CompanyAccount.getAllInitAccounts().get("Cash");
		account.recordTransaction(new CompanyTransaction("123", new Date(), "Cash", "Notes-Payable", 2000.0, "Borrow money"));
		account.recordTransaction(new CompanyTransaction("123", new Date(), "Notes-Payable", "Cash", 500.0, "Return a portion of the money"));
		assertEquals(1500, account.getBalance());
	}
	@Test
	public void testRecordTransaction2() {
		CompanyAccount account = CompanyAccount.getAllInitAccounts().get("Notes-Payable");
		account.recordTransaction(new CompanyTransaction("123", new Date(), "Cash", "Notes-Payable", 2000.0, "Borrow money"));
		account.recordTransaction(new CompanyTransaction("123", new Date(), "Notes-Payable", "Cash", 500.0, "Return a portion of the money"));
		assertEquals(1500, account.getBalance());
	}
}

