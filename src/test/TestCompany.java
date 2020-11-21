package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import corporateAccounting.Company;
import corporateAccounting.CompanyAccount;
import corporateAccounting.CompanyTransaction;

public class TestCompany {
	
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
	public void testIsValidTransaction1() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Non-existant account", "Cash", 1000, "Some description");
		assertEquals(false, company.isValidTransaction(transaction));
	}
	@Test
	public void testIsValidTransaction2() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Non-existant account", 1000, "Some description");
		assertEquals(false, company.isValidTransaction(transaction));
	}
	@Test
	public void testIsValidTransaction3() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Notes-Payable", "Cash", 1000, "Pay off notes payable owed");
		assertEquals(false, company.isValidTransaction(transaction));
	}
	@Test
	public void testIsValidTransaction4() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Notes-Receivable", 1000, "Receive cash from notes");
		assertEquals(false, company.isValidTransaction(transaction));
	}
	@Test
	public void testIsValidTransaction5() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Accounts-Payable", 1000, "Borrow money from the bank");
		assertEquals(true, company.isValidTransaction(transaction));
	}
	@Test
	public void testIsValidTransaction6() {
		Company company = new Company();
		company.getAccountList().get("Cash").setBalance(1000);
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Equipment", "Cash", 1000, "Buy equipment with cash");
		assertEquals(true, company.isValidTransaction(transaction));
	}
	@Test
	public void testIsValidTransaction7() {
		Company company = new Company();
		company.getAccountList().get("Deferred-Revenue").setBalance(1200);  // simulate increasing deferred revenue by $1200 ($100/month for a year)
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Deferred-Revenue", "Service-Revenue", 100, "Provide a month of service");
		assertEquals(true, company.isValidTransaction(transaction));
	}
	/*@Test void testRecordTransaction1() {
		Company company = new Company();
		
	}*/
}
