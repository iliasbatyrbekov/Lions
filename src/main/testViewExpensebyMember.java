package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class testViewExpensebyMember {
	ArrayList<Transaction> TL = new ArrayList<>();
	ArrayList<Account> AL = new ArrayList<>();
	ArrayList<Plan> PL = new ArrayList<>();
	@Test
	public void test1() {
		Expense Child = new Expense(0, 1000.0, "", "", new Date());
		Child.setMember("Child");
		TL.add(Child);

		Expense Parent = new Expense(0, 2000.0, "", "", new Date());
		Parent.setMember("Parent");
		TL.add(Parent);

		Expense Grandparent = new Expense(0, 3000.0, "", "", new Date());
		Grandparent.setMember("Grandparent");
		TL.add(Grandparent);

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyMember();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Parent: 2000.0");
		expected.add("Child: 1000.0");
		expected.add("Grandparent: 3000.0");

		System.out.println("Expected: " + expected);
		System.out.println("Expected: " + result);
		assertEquals(expected, result);
	}
}
