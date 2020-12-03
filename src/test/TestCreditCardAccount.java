package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import main.CreditCardAccount;

public class TestCreditCardAccount {
	
	@Test
	public void testCreditAccount() {
		CreditCardAccount account = new CreditCardAccount("1", 1.0);
		account = new CreditCardAccount("1", 0.0, 1.0, "2020-11-11");
		account.setInterest(10.0);
		assertEquals(10.0, account.getInterest());
		account.setPaymentDate("2020-12-12");
		assertEquals("2020-12-12", account.getPaymentDate());
	}
}
 