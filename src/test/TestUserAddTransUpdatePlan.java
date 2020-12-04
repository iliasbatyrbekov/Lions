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

import personalAccounting.ExAccountNotExist;
import personalAccounting.ExAccountTypeNotSupported;
import personalAccounting.ExPlainNotExist;
import personalAccounting.ExUpdateBalanceErr;
import personalAccounting.Income;
import personalAccounting.SavingPlan;
import personalAccounting.Transaction;
import personalAccounting.User;

public class TestUserAddTransUpdatePlan {
	User user;
//	Plan plan;
	SavingPlan savingPlan1, savingPlan2;
	
	@BeforeEach
	public void setUp() throws Exception {}
	
	public void tearDown() {}
	
//	@Test
//	public void testUpdatePlan() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
//		class SavingPlanStub extends SavingPlan {
//			public double getCurrentAmount() {
//				return 13000.0;
//			}
//		}
//		
//		user = User.getInstance();
//		user.addAccount("Cash", "0", 0.0, 12.0, 1.9, "2019-01-09");
//		
//		ArrayList<String> timePeriod1 = new ArrayList<>();
//        timePeriod1.add("2020-09-01");
//        timePeriod1.add("2020-12-31");
//        savingPlan1 = new SavingPlanStub("SavingPlan1", timePeriod1, 10000.0, "Education");
//        user.addPlan(savingPlan1);
//        
//        ArrayList<String> timePeriod2 = new ArrayList<>();
//        timePeriod2.add("2020-06-01");
//        timePeriod2.add("2020-11-30");
//        savingPlan2 = new SavingPlanStub("SavingPlan2", timePeriod2, 90000.0, "Housing");
//        user.addPlan(savingPlan2);
//		
//		user.addTransaction("Expense", 1000.0, "0", "1", "savingPlan2", "2020-11-18", "Education");
//        user.addTransaction("Expense", 12000.0, "0", "1", "savingPlan2", "2020-11-20", "Car");
//        
//        double newAmount = savingPlan2.getCurrentAmount();
//        assertEquals(13000.0, newAmount, 1);
//	}
	
	@Test
	public void testUpdatePlan() throws ExPlainNotExist, ExAccountNotExist, ExUpdateBalanceErr, ExAccountTypeNotSupported {
		user = User.getInstance();
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
		
		user.addTransaction("Expense", 1000.0, "0", "1", "savingPlan2", "2020-11-18", "Education");
        user.addTransaction("Expense", 12000.0, "0", "1", "savingPlan2", "2020-11-20", "Car");
        
        double newAmount = savingPlan2.getCurrentAmount();
        assertEquals(13000.0, newAmount, 1);
	}
}
