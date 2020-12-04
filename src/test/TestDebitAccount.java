package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import personalAccounting.Account;
import personalAccounting.DebitAccount;

public class TestDebitAccount {
	
	@Test
	public void testDebitAccount() {
		Account account = new DebitAccount("1", 0.0);
		assertEquals(-1, account.updateBalance(-10.0));
	}
	@Test
	public void testDebitAccount1() {
		Account account = new DebitAccount("1", 0.0);
		assertEquals(1, account.updateBalance(10.0));
	}
}
