package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import corporateAccounting.CompanyTransaction;

public class TestCompanyTransaction {
	
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
	public void testPrintTransaction1() {
		CompanyTransaction transaction = new CompanyTransaction("0001", new Date(), "Dividend", "Cash", 5000, "Pay dividends");
		transaction.printTransaction(false);
		String tableformat = "%-10s | %-15s | %-30s | %10s | %10s";
		String separationLine = "----------------------------------------------------------------------------------------\n";
		String expected = String.format(tableformat, "0001", "2020-11-21", "Dividend", "5000.0", "") + "\n" + String.format(tableformat, "", "", "Cash", "", "5000.0") + "\n" + String.format(tableformat, "", "", "(Pay dividends)", "", "") + "\n" + separationLine;
		//System.out.println(expected);
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintTransaction2() {
		CompanyTransaction transaction = new CompanyTransaction("0001", new Date(), "Dividend", "Cash", 5000, "Pay dividends");
		transaction.printTransaction(true);
		String tableformat = "%-10s | %-15s | %-30s | %10s | %10s";
		String separationLine = "----------------------------------------------------------------------------------------\n";
		String expected = String.format(tableformat, "0001", "2020-11-21", "Dividend", "5000.0", "") + "\n" + String.format(tableformat, "", "", "Cash", "", "5000.0") + "\n" + String.format(tableformat, "", "", "(Pay dividends)", "", "") + "\n" + separationLine;
		//System.out.println(expected);
		assertEquals(expected, outContent.toString());
	}
}
