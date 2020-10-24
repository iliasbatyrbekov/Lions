package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class testViewExpensebyMember {
	@Test
	public void test1() {
		ArrayList<Transaction> TL = new ArrayList<>();
		ArrayList<Account> AL = new ArrayList<>();
		ArrayList<Plan> PL = new ArrayList<>();
		Expense t = new Expense("123");
		t.setMember("Member A");
		t.setAmount(10000);
		TL.add(t);
		AL.add(new Account("456", "password"));
		PL.add(new Plan());

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyMember();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Member A: 10000.0");

		assertEquals(expected, result);
		System.out.println("Expected: " + expected);
	}
}
