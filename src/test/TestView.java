package test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import main.Transaction;
import main.Account;
import main.Plan;
import main.View;
import main.Income;
import main.Expense;

public class TestView {
	ArrayList<Transaction> TL = new ArrayList<Transaction>();
	ArrayList<Account> AL = new ArrayList<Account>();
	ArrayList<Plan> PL = new ArrayList<Plan>();

	@Test
	public void testViewAllAccountBalance_01(){
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

	@Test
	public void testViewAllAccountBalance_02(){
		Account acc1=new Account("", 0);
		AL.add(acc1);
		HashMap<String, Double> expected = new HashMap<>();
		expected.put("", (double)0);
		View view = new View(TL, AL, PL);
		HashMap<String, Double> result = view.viewAllAccountBalance();
		assertEquals(expected, result);
	}
	
	@Test
	public void testViewExpensebyCategory() {
		Expense t = new Expense(0, 10000, "123", null, null, "Food");
		Expense t1 = new Expense(0, 10000, "123", null, null, "Food");
		Expense t2 = new Expense(0, 10000, "123", null, null, "Drinks");
		TL.add(t);
		TL.add(t1);
		TL.add(t2);
		
		Income incometran=new Income(5,1000.0,"","","", null);
		TL.add(incometran);

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyCategory();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Drinks: 10000.0");
		expected.add("Food: 20000.0");

		assertEquals(expected, result);
	}
	
	@Test
	public void testViewExpensebyMember() {
		Expense Child = new Expense(0, 1000.0, "", "", "", "");
		Child.setMember("Child");
		TL.add(Child);

		Expense Parent = new Expense(0, 2000.0, "", "", "", "");
		Parent.setMember("Parent");
		TL.add(Parent);

		Expense Grandparent = new Expense(0, 3000.0, "", "", "", "");
		Grandparent.setMember("Grandparent");
		TL.add(Grandparent);
		
		Expense Grandparent2 = new Expense(0, 6000.0, "", "", "", "");
		Grandparent2.setMember("Grandparent");
		TL.add(Grandparent2);
		
		Income incometran=new Income(5,1000.0,"","","", null);
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
	public void testViewExpensebyAccount() {
		Expense t = new Expense(0, 10000, "Cash", null, null, null);
		Expense t1 = new Expense(0, 10000, "CreditCard", null, null, null);
		Expense t2 = new Expense(0, 10000, "Cash", null, null, null);
		TL.add(t);
		TL.add(t1);
		TL.add(t2);
		
		Income incometran=new Income(5,1000.0,"","","", null);
		TL.add(incometran);

		View myView = new View(TL, AL, PL);

		ArrayList<String> result = myView.viewExpensebyAccount();

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Cash: 20000.0");
		expected.add("CreditCard: 10000.0");

		assertEquals(expected, result);
	}
}