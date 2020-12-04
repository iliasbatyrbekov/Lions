package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personalAccounting.ExAccountNotExist;
import personalAccounting.ExAccountTypeNotSupported;
import personalAccounting.ExPlainNotExist;
import personalAccounting.ExUpdateBalanceErr;
import personalAccounting.Income;
import personalAccounting.Plan;
import personalAccounting.SavingPlan;
import personalAccounting.Transaction;
import personalAccounting.User;

public class TestUserUtilityFunction {
	
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
	public void testAddAccountCredit() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addAccount("Credit", "0", 1000.0, 0.0, 0.0, "0");
		String res = u.checkAccount("Credit", "0");
		assertEquals(res, "Account type mismatch");
	}
	
	@Test
	public void testAddAccountSaving() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addAccount("Saving", "2", -100.0, 0.0, 0.9, "2020-01-09");
		String res = u.checkAccount("Saving", "2");
		assertEquals(res, "Added in Saving Account");
	}
	
	@Test
	public void testAddAccountDebit() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addAccount("Debit", "1", -100.0, 0.0, 0.9, "2020-01-09");
		String res = u.checkAccount("Debit", "1");
		assertEquals(res, "Added in Debit Account");
	}
	@Test
	public void testAddAccountCash() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addAccount("Cash", "4", 200.0, 1.0, 1.0, "2020-01-29");
		String res = u.checkAccount("Cash", "4");
		assertEquals(res, "Added in Cash Account");
	}
	@Test
	public void testAddAccountNull() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addAccount("Cash", "6", -1340.0, 12.0, 1.9, "2019-01-09");
		String res = u.checkAccount("Saving", "6");
		assertEquals(res, "Account type mismatch");
	}
	
	@Test
	public void testAddAccountNotFound() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		String res = u.checkAccount("Saving", "7");
		u.listAllAccounts();
		assertEquals(res, "Account not found");
	}
	
	@Test
	public void testAddAccountNoTypeSupported() {
		User u = User.getInstance();
		try {
			u.addAccount("CCSSS", "6", -1340.0, 12.0, 1.9, "2019-01-09");
			assertEquals(outContent.toString(), "There is no CCSSS type.");
		} catch (ExAccountTypeNotSupported e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testPlan() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		ArrayList<String> tp = new ArrayList<>();
		tp.add("2020-11-12");
		tp.add("2020-12-24");
		Plan p = new Plan("Car", tp);
		u.getPlanList().add(p);
		
		u.addPlan("Car", "2020-11-12", "2021-12-24");
		Plan outPlan = u.searchPlan("0");
		u.listAllPlan();
		assertEquals(p, outPlan);
	}
	
	@Test
	public void testPlanNotFound() throws ExAccountTypeNotSupported {
		User u = User.getInstance();
		Plan outPlan = u.searchPlan("10");

		assertEquals(outPlan, null);
	}
	
	
	@Test
	public void testAddTransaction() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
		User u = User.getInstance();
		
		Income in = new Income(1, 500.0, "0", "NA", "2020-11-18", "Car");
		u.getTransactionList(null, null).add(in);
		Transaction t = u.findTransactionRecord(1);
		u.listAllTransactionRecords();
		assertEquals(t, in);
	}
	
	@Test
	public void testAddTransactionFromUser() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addPlan("Car", "2020-11-12", "2021-12-24");
		u.addAccount("Cash", "0", -1340.0, 12.0, 1.9, "2019-01-09");
		u.addTransaction("TransferReceive", 12500.0, "0", "0", "NA", "2020-11-18", "Scholarship");
		u.addTransaction("Expense", 12000.0, "0", "0", "NA", "2020-11-18", "car");
		u.addTransaction("Income", 19500.0, "0", "0", "NA", "2020-11-18", "Salary");
		u.addTransaction("TransferRemit", 12000.0, "0", "0", "NA", "2020-11-18", "Tuition Fee");
		
		assertEquals(u.getAccountList().size(), 4);
	}
	
	@Test
	public void testAddErrTrans() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addPlan("Car", "2020-11-12", "2021-12-24");
		u.addAccount("Cash", "0", -1340.0, 12.0, 1.9, "2019-01-09");
		u.addTransaction("Transfer", 12500.0, "0", "0", "NA", "2020-11-18", "Scholarship");
		
		assertEquals(u.getAccountList().size(), 3);
	}
	
	@Test
	public void testBalanceUpdateError() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addPlan("Car", "2020-11-12", "2021-12-24");
		u.addAccount("Cash", "0", -1340.0, 12.0, 1.9, "2019-01-09");
		u.addTransaction("Expense", -12500.0, "0", "0", "NA", "2020-11-18", "Scholarship");
		
		assertEquals(u.getAccountList().size(), 7);
	}
}
