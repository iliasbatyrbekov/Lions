package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testViewExpensebyCategory {
	@Test
	public void test1() {
		ArrayList<Transaction> TL = new ArrayList<>();
		ArrayList<Account> AL = new ArrayList<>();
		ArrayList<Plan> PL = new ArrayList<>();
		Expense t = new Expense("123");
		t.setCategory("Expense");
		t.setAmount(10000);
		TL.add(t);
		AL.add(new Account("456", "password"));
		PL.add(new Plan());

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyCategory();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Expense: 10000.0");

		assertEquals(expected, result);
		System.out.println("Expected: " + expected);
	}
}
