package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.User;
import main.ExAccountNotExist;
import main.ExAccountTypeNotSupported;
import main.ExPlainNotExist;
import main.ExUpdateBalanceErr;
import main.Income;
//import main.Plan;
import main.SavingPlan;
import main.Transaction;

public class TestUserAddTransUpdatePlan {
	User user;
//	Plan plan;
	SavingPlan savingPlan1, savingPlan2;
	
	@BeforeEach
	public void setUp() throws Exception {}
	
	public void tearDown() {}
	
//	@SuppressWarnings("deprecation")	
//	@Test
//	public void testAddTransaction() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr {
//		User u = User.getInstance();
//		
//		Income in = new Income(1, 500.0, "0", "NA", "2020-11-18", "Car");
//		u.getTransactionList(null, null).add(in);
//		Transaction t = u.findTransactionRecord(1);
//		u.listAllTransactionRecords();
//		assertEquals(t, in);
//	}
	@Test
	public void testUpdatePlan() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		User user = User.getInstance();
		//   addAccount(String accountType,String accID, double balance, double debt, double interest, String withdrawDate);
		user.addAccount("Cash", "0", 0.0, 12.0, 1.9, "2019-01-09");
		
		ArrayList<String> timePeriod1 = new ArrayList<>();
        timePeriod1.add("2020-09-01");
        timePeriod1.add("2020-12-31");
        savingPlan1 = new SavingPlan("SavingPlan1", timePeriod1, 10000.0, "Education");
        user.addPlan(savingPlan1);
        
        ArrayList<String> timePeriod2 = new ArrayList<>();
        timePeriod2.add("2020-06-01");
        timePeriod2.add("2020-11-30");
        savingPlan2 = new SavingPlan("SavingPlan2", timePeriod2, 90000.0, "Housing");
        user.addPlan(savingPlan2);
        
        double originalAmount = savingPlan2.getCurrentAmount();
//        System.out.println("originalAmount "+originalAmount);
		
		user.addTransaction("Expense", 1000.0, "0", "1", "savingPlan2", "2020-11-18", "Education");
        user.addTransaction("Expense", 12000.0, "0", "1", "savingPlan2", "2020-11-20", "Car");
        
        double newAmount = savingPlan2.getCurrentAmount();
//        System.out.println("newAmount "+newAmount);
        assertEquals(13000.0, newAmount, 1);
	}
}
