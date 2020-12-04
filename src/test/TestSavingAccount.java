package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

import personalAccounting.SavingAccount;

public class TestSavingAccount {
	
	@Test
	public void testSavingAccount() {
		SavingAccount account = new SavingAccount("1", 0.0, 1.0, "2020-11-11");
		account.setInterest(10.0);
		assertEquals(10.0, account.getInterest());
		account.setWithdrawDate("2020-12-12");
		assertEquals("2020-12-12", account.getWithdrawDate());
	}
}
