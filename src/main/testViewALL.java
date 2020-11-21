package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class testViewALL {
	ArrayList<Transaction> TL = new ArrayList<Transaction>();
	ArrayList<Account> AL = new ArrayList<Account>();
	ArrayList<Plan> PL = new ArrayList<Plan>();

	//testcase when there are 3 accounts, showing the total balance of each acc
	@Test
	public void testViewAllAccountBalance(){
		Account acc1 = new Account("shawn", 2500);
		Account acc2 = new Account("luke", 2000);
		Account acc3 = new Account("bryan", 1500);
		AL.add(acc1);
		AL.add(acc2);
		AL.add(acc3);
		HashMap<String, Double> expected = new HashMap<>();
		expected.put("shawn", 2500.0);
		expected.put("luke", 2000.0);
		expected.put("bryan", 1500.0);
		View view = new View(TL, AL, PL);
		HashMap<String, Double> result = view.viewAllAccountBalance();
		assertEquals(expected, result);
	}

	//testcase when there is no record found
	@Test
	public void test_2(){
		Account acc1=new Account("", 0);
		AL.add(acc1);
		HashMap<String, Double> expected = new HashMap<>();
		expected.put("", (double)0);
		View view = new View(TL, AL, PL);
		HashMap<String, Double> result = view.viewAllAccountBalance();
		assertEquals(expected, result);
	}
	
	@Test
	public void test_3() {
		ArrayList<Transaction> TL = new ArrayList<>();
		ArrayList<Account> AL = new ArrayList<>();
		ArrayList<Plan> PL = new ArrayList<>();
		Expense t = new Expense(0, 10000, "123", null, null);
		t.setCategory("Food");
		Expense t1 = new Expense(0, 10000, "123", null, null);
		t1.setCategory("Food");
		Expense t2 = new Expense(0, 10000, "123", null, null);
		t2.setCategory("Drinks");
		TL.add(t);
		TL.add(t1);
		TL.add(t2);
		
		Income incometran=new Income(5,1000.0,"","","");
		TL.add(incometran);

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyCategory();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Drinks: 10000.0");
		expected.add("Food: 20000.0");

		assertEquals(expected, result);
		System.out.println("Expected: " + expected);
	}
	
	@Test
	public void test_4() {
		ArrayList<Transaction> TL = new ArrayList<>();
		ArrayList<Account> AL = new ArrayList<>();
		ArrayList<Plan> PL = new ArrayList<>();
		Expense Child = new Expense(1, 1000.0, "", "", "");
		Child.setMember("Child");
		TL.add(Child);

		Expense Parent = new Expense(2, 2000.0, "", "", "");
		Parent.setMember("Parent");
		TL.add(Parent);

		Expense Grandparent = new Expense(3, 3000.0, "", "", "");
		Grandparent.setMember("Grandparent");
		TL.add(Grandparent);
		
		Expense Grandparent2 = new Expense(4, 6000.0, "", "", "");
		Grandparent2.setMember("Grandparent");
		TL.add(Grandparent2);
		
		Income incometran=new Income(5,1000.0,"","","");
		TL.add(incometran);

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyMember();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Parent: 2000.0");
		expected.add("Child: 1000.0");
		expected.add("Grandparent: 9000.0");

		assertEquals(expected, result);
	}
	
	@Test
	public void test_5() {
		Expense t = new Expense(0, 10000, "Cash", null, null);
		Expense t1 = new Expense(0, 10000, "CreditCard", null, null);
		Expense t2 = new Expense(0, 10000, "Cash", null, null);
		TL.add(t);
		TL.add(t1);
		TL.add(t2);
		
		Income incometran=new Income(5,1000.0,"","","");
		TL.add(incometran);

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyAccount();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Cash: 20000.0");
		expected.add("CreditCard: 10000.0");

		assertEquals(expected, result);
	}
}