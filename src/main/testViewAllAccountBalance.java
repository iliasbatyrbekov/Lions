package main;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testViewAllAccountBalance {
	private ArrayList<Transaction> tranHist;
	private ArrayList<Account>accList=new ArrayList<Account>();
	private ArrayList<Plan> planList;

	//testcase when there are 3 accounts, showing the total balance of each acc
	@Test
	public void testQ1_1(){
		Account acc1=new Account("shawn", "luke", 2500);
		Account acc2=new Account("luke", "Bryan", 2000);
		Account acc3=new Account("bryan", "luke", 1500);
		accList.add(acc1);
		accList.add(acc2);
		accList.add(acc3);
		HashMap<String, Double> expected = new HashMap<>();
		expected.put("shawn", 2500.0);
		expected.put("luke", 2000.0);
		expected.put("bryan", 1500.0);
		View view = new View(tranHist, accList, planList);
		HashMap<String, Double> result = view.viewAllAccountBalance();
		System.out.print(result);
		assertEquals(expected, result);
	}
}


