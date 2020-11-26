package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import corporateAccounting.Company;
import corporateAccounting.CompanyAccount;
import corporateAccounting.CompanyTransaction;
import corporateAccounting.InventoryStorageEntry;

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
		assertEquals("Error: Non-existant account is not a valid account.\n", outContent.toString());
	}
	@Test
	public void testIsValidTransaction2() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Non-existant account", 1000, "Some description");
		assertEquals(false, company.isValidTransaction(transaction));
		assertEquals("Error: Non-existant account is not a valid account.\n", outContent.toString());
	}
	@Test
	public void testIsValidTransaction3() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Notes-Payable", "Cash", 1000, "Pay off notes payable owed");
		assertEquals(false, company.isValidTransaction(transaction));
		assertEquals("Error: Cannot debit Notes-Payable account (insufficient balance)\n", outContent.toString());
	}
	@Test
	public void testIsValidTransaction4() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Notes-Receivable", 1000, "Receive cash from notes");
		assertEquals(false, company.isValidTransaction(transaction));
		assertEquals("Error: Cannot credit Notes-Receivable account (insufficient balance)\n", outContent.toString());
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
	@Test
	public void testIsValidTransaction8() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Cash", 1000, "Receive cash from cash");
		assertEquals(false, company.isValidTransaction(transaction));
		assertEquals("Error: Cannot debit and credit the same account\n", outContent.toString());
	}
	@Test 
	public void testRecordTransaction1() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction("0001", new Date(), "Cash", "Sales-Revenue", 5000, "Revenue from the sale of goods");
		company.recordTransaction(transaction);
		assertEquals("Transaction successfully added\n", outContent.toString());
	}
	@Test 
	public void testRecordTransaction2() {
		Company company = new Company();
		CompanyTransaction transaction = new CompanyTransaction("0001", new Date(), "Cash Spelled Wrong", "Sales-Revenue", 5000, "Revenue from the sale of goods");
		company.recordTransaction(transaction);
		assertEquals("Error: Cash Spelled Wrong is not a valid account.\nError: Cannot record this Transaction\n", outContent.toString());
	}
	@Test
	public void testGenerateClosingEntries() {
		Company company = new Company();
		CompanyTransaction revenueTransaction = new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Service-Revenue", 5000, "Revenue from service provided");
		CompanyTransaction expenseTransaction = new CompanyTransaction(company.generateNewID(), new Date(), "Advertising-Expense", "Cash", 1200, "Advertising");
		CompanyTransaction dividendTransaction = new CompanyTransaction(company.generateNewID(), new Date(), "Dividends-Cash", "Cash", 800, "Pay dividends to stock holders");
		CompanyTransaction cntrRvnTransaction = new CompanyTransaction(company.generateNewID(), new Date(), "Sales-Returns", "Cash", 500, "Customer returns defected goods");
		company.recordTransaction(revenueTransaction);
		company.recordTransaction(expenseTransaction);
		company.recordTransaction(dividendTransaction);
		company.recordTransaction(cntrRvnTransaction);
		assertEquals(5000-1200-800-500, company.generateClosingEntries(new Date()));
	}
	@Test
	public void testPurchaseInventory1() {
		Company company = new Company();
		ArrayList<InventoryStorageEntry> expected = new ArrayList<>();
		expected.add(new InventoryStorageEntry("ID", 5, 100));
		ArrayList<InventoryStorageEntry> actual = company.purchaseInventory(5, 100, new Date(), "Accounts-Payable");
		assertEquals("Transaction successfully added\nInventory successfully purchased and added to storage\n", outContent.toString());
		for (int i=0; i<expected.size(); i++) 
			assertTrue(expected.get(i).getUnitCost() == actual.get(i).getUnitCost() && expected.get(i).getUnits() == actual.get(i).getUnits());
	}
	@Test
	public void testPurchaseInventory2() {
		Company company = new Company();
		assertTrue(company.purchaseInventory(5, 100, new Date(), "Cash").isEmpty());
		assertEquals("Error: Cannot credit Cash account (insufficient balance)\nError: invalid purchase\n", outContent.toString());
	}
	@Test
	public void testSellInventory1() {
		Company company = new Company();
		//company.purchaseInventory(5, 100, new Date(), "Accounts-Payable");
		ArrayList<InventoryStorageEntry> storage = company.sellInventory(7, 50, new Date(), "Cash", "FIFO");
		assertTrue(storage.isEmpty());
		assertEquals("Error: not enough inventory in store\n", outContent.toString());
	}
	@Test
	public void testSellInventory2() {
		Company company = new Company();
		company.purchaseInventory(5, 100, new Date(), "Accounts-Payable");
		company.sellInventory(7, 50, new Date(), "Accounts-Payable", "FIFO");
		//assertTrue(company.sellInventory(7, 50, new Date(), "Accounts-Payable", "FIFO").isEmpty());
		assertEquals("Transaction successfully added\nInventory successfully purchased and added to storage\nError: Accounts-Payable is not an asset account, therefore cannot be debited for the sale of inventory\n", outContent.toString());
	}
	@Test
	public void testSellInventory3() {
		Company company = new Company();
		company.purchaseInventory(3, 30, new Date(), "Accounts-Payable");
		company.purchaseInventory(5, 40, new Date(), "Notes-Payable");
		company.purchaseInventory(6, 20, new Date(), "Notes-Payable");
		company.sellInventory(7, 50, new Date(), "Cash", "FIFO");
		ArrayList<InventoryStorageEntry> actual = company.getStorage();
		ArrayList<InventoryStorageEntry> expected = new ArrayList<>();
		expected.add(new InventoryStorageEntry("ID", 3, 0));
		expected.add(new InventoryStorageEntry("ID", 5, 20));
		expected.add(new InventoryStorageEntry("ID", 6, 20));
		for (int i=0; i<expected.size(); i++) 
			assertTrue(expected.get(i).getUnitCost() == actual.get(i).getUnitCost() && expected.get(i).getUnits() == actual.get(i).getUnits());
	}
	@Test
	public void testSellInventory4() {
		Company company = new Company();
		company.purchaseInventory(3, 30, new Date(), "Accounts-Payable");
		company.purchaseInventory(5, 40, new Date(), "Notes-Payable");
		company.purchaseInventory(6, 20, new Date(), "Notes-Payable");
		company.sellInventory(7, 50, new Date(), "Cash", "LIFO");
		ArrayList<InventoryStorageEntry> actual = company.getStorage();
		ArrayList<InventoryStorageEntry> expected = new ArrayList<>();
		expected.add(new InventoryStorageEntry("ID", 3, 30));
		expected.add(new InventoryStorageEntry("ID", 5, 10));
		expected.add(new InventoryStorageEntry("ID", 6, 0));
		for (int i=0; i<expected.size(); i++) 
			assertTrue(expected.get(i).getUnitCost() == actual.get(i).getUnitCost() && expected.get(i).getUnits() == actual.get(i).getUnits());
	}
	@Test
	public void testSellInventory5() {
		Company company = new Company();
		company.purchaseInventory(3, 30, new Date(), "Accounts-Payable");
		//company.purchaseInventory(5, 40, new Date(), "Notes-Payable");
		//company.purchaseInventory(6, 20, new Date(), "Notes-Payable");
		company.sellInventory(7, 10, new Date(), "Cash", "NEITHER-FIFO-NOR-LIFO");
		assertEquals("Transaction successfully added\nInventory successfully purchased and added to storage\nError: invalid selling method\n", outContent.toString());
	}
	@Test
	public void testSellInventory6() {
		Company company = new Company();
		company.sellInventory(7, 0, new Date(), "Cash", "FIFO");
		ArrayList<InventoryStorageEntry> actual = company.getStorage();
		ArrayList<InventoryStorageEntry> expected = new ArrayList<>();
		for (int i=0; i<expected.size(); i++) 
			assertTrue(expected.get(i).getUnitCost() == actual.get(i).getUnitCost() && expected.get(i).getUnits() == actual.get(i).getUnits());
	}
	@Test
	public void testSellInventory7() {
		Company company = new Company();
		company.sellInventory(7, 0, new Date(), "Cash", "LIFO");
		ArrayList<InventoryStorageEntry> actual = company.getStorage();
		ArrayList<InventoryStorageEntry> expected = new ArrayList<>();
		for (int i=0; i<expected.size(); i++) 
			assertTrue(expected.get(i).getUnitCost() == actual.get(i).getUnitCost() && expected.get(i).getUnits() == actual.get(i).getUnits());
	}
	@Test
	public void testPrintBalanceSheet1() {
		Company company = new Company();
		company.printBalanceSheet();
		String expected = new String(new char[128]).replace("\0", "-") + "\n";
		expected += String.format(" %-40s | %-40s | %-40s |", "ASSETS", "LIABILITY", "STOCK HOLDER'S EQUITY");
		expected += "\n------------------------------------------+------------------------------------------+------------------------------------------|\n";
		expected += String.format(" %-27s | %10s | %-70s | %10s |", "Asset balance: ", "0.0", "Liability & Stockholder's equity balance: ", "0.0");
		expected += "\n------------------------------------------+------------------------------------------+------------------------------------------|\n";
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintBalanceSheet2() {
		Company company = new Company();
		company.recordTransaction(new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Accounts-Payable", 5000, "Description 1"));
		company.recordTransaction(new CompanyTransaction(company.generateNewID(), new Date(), "Treasury-Stock", "Cash", 1000, "Description 2"));
		company.recordTransaction(new CompanyTransaction(company.generateNewID(), new Date(), "Depreciation-Expense", "Accumulated-Depreciation", 500, "Description 3"));
		company.recordTransaction(new CompanyTransaction(company.generateNewID(), new Date(), "Cash", "Common-Stock", 500, "Description 4"));
		company.printBalanceSheet();
		String headingFormat = " %-40s | %-40s | %-40s |";
		String balanceRowFormat = " %-27s | %10s | %-27s | %10s | %-27s | %10s |";
		String seperationLine = "\n------------------------------------------+------------------------------------------+------------------------------------------|\n";
		String expected = "Transaction successfully added\n";
		expected += expected;
		expected += expected;
		expected += new String(new char[128]).replace("\0", "-") + "\n";
		expected += String.format(headingFormat, "ASSETS", "LIABILITY", "STOCK HOLDER'S EQUITY");
		expected += seperationLine;
		expected += String.format(balanceRowFormat, "Cash", "4500.0", "Accounts-Payable", "5000.0", "Common-Stock", "500.0");
		expected += seperationLine;
		expected += String.format(balanceRowFormat, "Accumulated-Depreciation", "500.0", "", "", "Treasury-Stock", "1000.0");
		expected += seperationLine;
		expected += String.format(" %-27s | %10s | %-70s | %10s |", "Asset balance: ", "4000.0", "Liability & Stockholder's equity balance: ", "4500.0"); // the balance on the left and right are not the same (yet) because closing entries have not been generated
		expected += "\n------------------------------------------+------------------------------------------+------------------------------------------|\n";
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintJournal1() {
		Company company = new Company();
		company.printJournal();
		assertEquals("Transaction is empty\n", outContent.toString());
	}
	@Test
	public void testPrintJournal2() throws ParseException {
		Company company = new Company();
		company.recordTransaction(new CompanyTransaction(company.generateNewID(), new SimpleDateFormat("dd/MM/yyyy").parse("20/11/2020"), "Cash", "Accounts-Payable", 5000, "Description 1"));
		company.recordTransaction(new CompanyTransaction(company.generateNewID(), new SimpleDateFormat("dd/MM/yyyy").parse("20/11/2020"), "Cash", "Accounts-Payable", 2000, "Description 2"));
		company.printJournal();
		String format = "%-10s | %-15s | %-30s | %10s | %10s\n";
		String separationLine = "----------------------------------------------------------------------------------------\n";
		String expected = "Transaction successfully added\nTransaction successfully added\n";
		expected += separationLine;
		expected += String.format(format, "ID", "DATE", "ACCOUNT NAME & DESCRIPTION", "DEBIT", "CREDIT");
		expected += separationLine;
		expected += String.format(format, "10000", "2020-11-20", "Cash", "5000.0", "");
		expected += String.format(format, "", "", "Accounts-Payable", "", "5000.0");
		expected += String.format(format, "", "", "(Description 1)", "", "");
		expected += separationLine;
		expected += String.format(format, "10001", "2020-11-20", "Cash", "2000.0", "");
		expected += String.format(format, "", "", "Accounts-Payable", "", "2000.0");
		expected += String.format(format, "", "", "(Description 2)", "", "");
		expected += separationLine;
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintStorage1() {
		Company company = new Company();
		company.printStorage();

		String format = " %-30s | %-30s | %-30s |\n";
		String seperationLine = "--------------------------------+--------------------------------+--------------------------------\n";
		String expected = seperationLine;
		expected += String.format(format, "TRANSACTION ID", "UNIT COST", "# REMAINING UNITS");
		expected += seperationLine;
		assertEquals(expected, outContent.toString());
	}
	@Test
	public void testPrintStorage2() throws ParseException {
		Company company = new Company();
		company.purchaseInventory(5, 1000, new SimpleDateFormat("dd/MM/yyyy").parse("20/11/2020"), "Accounts-Payable");
		company.printStorage();

		String format = " %-30s | %-30s | %-30s |\n";
		String seperationLine = "--------------------------------+--------------------------------+--------------------------------\n";
		String expected = "Transaction successfully added\nInventory successfully purchased and added to storage\n";
		expected += seperationLine;
		expected += String.format(format, "TRANSACTION ID", "UNIT COST", "# REMAINING UNITS");
		expected += seperationLine;
		expected += String.format(format, "10000", "5.0", "1000");
		expected += seperationLine;
		assertEquals(expected, outContent.toString());
	}
}
