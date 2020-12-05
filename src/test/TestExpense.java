package test;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

import personalAccounting.ExAccountNotExist;
import personalAccounting.ExAccountTypeNotSupported;
import personalAccounting.ExPlainNotExist;
import personalAccounting.ExUpdateBalanceErr;
import personalAccounting.Expense;
import personalAccounting.Transaction;
import personalAccounting.User;


public class TestExpense {
	@Test
	public void testAddExpenseTransaction() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		User u = User.getInstance();
		u.addAccount("Cash", "1", 200.0, 1.0, 1.0, "2020-01-29");
		u.addPlan("Car", "2020-11-12", "2021-12-24");
		u.addExpenseTransaciton("Expense", 1000.0, "1", "0", "Description", "2020-11-12", "Car", "Parents");
	}
	
	@Test
	public void testGetCategory() {
		User u = User.getInstance();
		Transaction t = u.findTransactionRecord(0);
		String out = ((Expense)t).getCategory();
		assertEquals("Car", out);
	}
	
	@Test
	public void testGetMember() {
		User u = User.getInstance();
		Transaction t = u.findTransactionRecord(0);
		String out = ((Expense)t).getMember();
		assertEquals("Parents", out);
	}
}
