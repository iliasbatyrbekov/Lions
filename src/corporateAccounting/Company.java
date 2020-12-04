package corporateAccounting;
import java.util.*;

public class Company {
	private HashMap<String, CompanyAccount> accountList;
	private ArrayList<CompanyTransaction> journal;
	private ArrayList<InventoryStorageEntry> storage;
	//private int totalUnits;
	private int currentID;
	
	public Company() {
		accountList = CompanyAccount.getAllInitAccounts();
		journal = new ArrayList<CompanyTransaction>();
		storage = new ArrayList<InventoryStorageEntry>();
		currentID = 10000;
	}
	
	public String generateNewID() {
		return String.valueOf(currentID++);
	}
	
	public HashMap<String, CompanyAccount> getAccountList() {
		return accountList;
	}
	
	public ArrayList<InventoryStorageEntry> getStorage() {
		return storage;
	}
	
	public boolean isValidTransaction(CompanyTransaction trans) {
		
		CompanyAccount accountToDebit = accountList.get(trans.getDebittedAccount());
		CompanyAccount accountToCredit = accountList.get(trans.getCredittedAccount());
		
		if (accountToCredit == null) {
			System.out.print("Error: " + trans.getCredittedAccount() + " is not a valid account.\n");
			return false;
		}
		if (accountToDebit == null) {
			System.out.print("Error: " + trans.getDebittedAccount() + " is not a valid account.\n");
			return false;
		}
		if (accountToCredit.equals(accountToDebit)) {
			System.out.print("Error: Cannot debit and credit the same account\n");
			return false;
		}
		
		//for credit account type, if debit means decrease -> may have credit balance < 0
		if(accountToDebit.getAccountType() == CompanyAccountType.CREDIT_ACCOUNT) {
			if(accountToDebit.getBalance() - trans.getAmount() < 0) {
				System.out.print("Error: Cannot debit " + trans.getDebittedAccount() + " account (insufficient balance)\n");
				return false;
			}
		}
		
		//for debit account type, if credit means decrease -> may have insufficient fund
		if(accountToCredit.getAccountType() == CompanyAccountType.DEBIT_ACCOUNT) {
			if(accountToCredit.getBalance() - trans.getAmount() < 0) {
				System.out.print("Error: Cannot credit " + trans.getCredittedAccount() + " account (insufficient balance)\n");
				return false;
			}
		}
		
		return true;
	}
	
	public void recordTransaction(CompanyTransaction trans) {
		if(isValidTransaction(trans)) {
			//record in journal
			journal.add(trans);
			
			//also update the accounts balance
			accountList.get(trans.getDebittedAccount()).recordTransaction(trans);
			accountList.get(trans.getCredittedAccount()).recordTransaction(trans);
			System.out.print("Transaction successfully added\n");
		} else {
			System.out.print("Error: Cannot record this Transaction\n");
			//error message may be shown in isValidTransaction()
		}
	}
	/*public ArrayList<CompanyTransaction> getJournal() {
		return journal;
	}*/
	
	public void printJournal() {
		if(journal.size()==0) {
			System.out.print("Transaction is empty\n");
		}
		for(int i=0; i<journal.size(); i++) {
			if(i == 0) {
				journal.get(i).printTransaction(true);
			} else {
				journal.get(i).printTransaction(false);
			}
		}
	}
	
	// TODO: only print the ones of non-zero balance
	public void printBalanceSheet() {
		String seperationLine = "\n------------------------------------------+------------------------------------------+------------------------------------------|\n";
		String headingFormat = " %-40s | %-40s | %-40s |";
		String balanceRowFormat = " %-27s | %10s | %-27s | %10s | %-27s | %10s |";
		String bottomLineFormat = " %-27s | %10s | %-70s | %10s |";
		
		System.out.print(new String(new char[128]).replace("\0", "-") + "\n");
		System.out.format(headingFormat, "ASSETS", "LIABILITY", "STOCK HOLDER'S EQUITY");
		System.out.print(seperationLine);
		
		int balanceSheetSize = 0;
		ArrayList<CompanyAccount> assetToPrint = new ArrayList<>();
		ArrayList<CompanyAccount> liabilityToPrint = new ArrayList<>();
		ArrayList<CompanyAccount> seToPrint = new ArrayList<>();
		// # of asset accounts to include in the balance sheet
		for (String assAcc: CompanyAccount.assetAccountNames) {
			if (accountList.get(assAcc).getBalance() != 0) 
				assetToPrint.add(accountList.get(assAcc));
		}
		for (String assAcc: CompanyAccount.contraAssetAccountNames) {
			if (accountList.get(assAcc).getBalance() != 0) 
				assetToPrint.add(accountList.get(assAcc));
		}
		// # of liability accounts to include in the balance sheet
		for (String liaAcc: CompanyAccount.liabilityAccountNames) {
			if (accountList.get(liaAcc).getBalance() != 0)
				liabilityToPrint.add(accountList.get(liaAcc));
		}
		// # of stockholder's equity accounts to include in the balance sheet
		for (String seAcc: CompanyAccount.stockHoldersEquityAccountNames) {
			if (accountList.get(seAcc).getBalance() != 0) 
				seToPrint.add(accountList.get(seAcc));
		}
		for (String seAcc: CompanyAccount.contrastockHoldersEquityAccountNames) {
			if (accountList.get(seAcc).getBalance() != 0) 
				seToPrint.add(accountList.get(seAcc));
		}
		
		balanceSheetSize = Math.max(assetToPrint.size(), Math.max(liabilityToPrint.size(), seToPrint.size()));

		double totalAssetBalance = 0;
		double totalLiabilityPlusSEBalance = 0;
		for (int i=0; i < balanceSheetSize; i++) {
			String assetAcc, assetBal, liabAcc, liabBal, seAcc, seBal;
			assetAcc = assetBal = liabAcc = liabBal = seAcc = seBal = "";
			//Assets
			if(i < assetToPrint.size()) {
				assetAcc = assetToPrint.get(i).getAccountName();
				assetBal = String.valueOf(assetToPrint.get(i).getBalance());
				if (assetToPrint.get(i).getAccountType() == CompanyAccountType.DEBIT_ACCOUNT)
					totalAssetBalance += assetToPrint.get(i).getBalance();
				else 
					totalAssetBalance -= assetToPrint.get(i).getBalance();
			}
			//Liabilities
			if(i < liabilityToPrint.size()) {
				liabAcc = liabilityToPrint.get(i).getAccountName();
				liabBal = String.valueOf(liabilityToPrint.get(i).getBalance());
				if (liabilityToPrint.get(i).getAccountType() == CompanyAccountType.CREDIT_ACCOUNT)
					totalLiabilityPlusSEBalance += liabilityToPrint.get(i).getBalance();
				else
					totalLiabilityPlusSEBalance -= liabilityToPrint.get(i).getBalance();
			}
			//Stockholders' equity
			if(i < seToPrint.size()) {
				seAcc = seToPrint.get(i).getAccountName();
				seBal = String.valueOf(seToPrint.get(i).getBalance());
				if (seToPrint.get(i).getAccountType() == CompanyAccountType.CREDIT_ACCOUNT)
					totalLiabilityPlusSEBalance += seToPrint.get(i).getBalance();
				else
					totalLiabilityPlusSEBalance -= seToPrint.get(i).getBalance();
			}
			System.out.format(balanceRowFormat, assetAcc, assetBal, liabAcc, liabBal, seAcc, seBal);
			System.out.print(seperationLine);
		}
		System.out.format(bottomLineFormat, "Asset balance: ", String.valueOf(totalAssetBalance), "Liability & Stockholder's equity balance: ", String.valueOf(totalLiabilityPlusSEBalance));
		System.out.print(seperationLine);
		
	}
	
	public void printStorage() {
		String format = " %-30s | %-30s | %-30s |\n";
		String seperationLine = "--------------------------------+--------------------------------+--------------------------------\n";
		System.out.print(seperationLine);
		System.out.format(format, "TRANSACTION ID", "UNIT COST", "# REMAINING UNITS");
		System.out.print(seperationLine);
		for (InventoryStorageEntry entry: storage) {
			System.out.format(format, entry.getTransID(), entry.getUnitCost(), entry.getUnits());
			System.out.print(seperationLine);
		}
	}
	
	public double generateClosingEntries(Date closingDate) {
		String retEarnAccName = "Retained-Earnings";  // name of the account in which to record the closing entries
		for (String revAccName: CompanyAccount.revenueAccountNames) {
			double amount = accountList.get(revAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction(generateNewID(), closingDate, revAccName, retEarnAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		for (String expAccName: CompanyAccount.expenseAccountNames) {
			double amount = accountList.get(expAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction(generateNewID(), closingDate, retEarnAccName, expAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		for (String divAccName: CompanyAccount.dividendAccountNames) {
			double amount = accountList.get(divAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction(generateNewID(), closingDate, retEarnAccName, divAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		for (String contraRevAccName: CompanyAccount.contraRevenueAccountNames) {
			double amount = accountList.get(contraRevAccName).getBalance();
			if (amount != 0) {
				CompanyTransaction closingTransaction = new CompanyTransaction(generateNewID(), closingDate, retEarnAccName, contraRevAccName, amount, "Closing entry");
				recordTransaction(closingTransaction);
			}
		}
		return accountList.get("Retained-Earnings").getBalance();
	}
	
	//for simple testing
	/*public static void main(String args[]) {
		test generateClosingEntries
		Date tday = new Date();
		CompanyTransaction testTrans1=new CompanyTransaction("id12334", tday, "Cash", "AccountsPayable", 1000.0, "Borrow from bank");
		CompanyTransaction testTrans2=new CompanyTransaction("id12335", tday, "Inventory", "Cash", 1000.0, "Buy Inventory");
		CompanyTransaction testTrans3=new CompanyTransaction("id12336", tday, "Cash", "CommonStock", 1000.0, "Issue Common Stock");
		CompanyTransaction testTran4 = new CompanyTransaction("777", tday, "Cash", "Service Revenue", 300, "Provide soccer training");
		Company lukecompany=new Company();
		lukecompany.journal.add(testTrans1);
		lukecompany.journal.add(testTrans2);
		lukecompany.journal.add(testTrans3);
		lukecompany.recordTransaction(testTran4);
		lukecompany.generateClosingEntries();
		lukecompany.printJournal();
		
		
		Date tday = new Date();
		Company lukecompany = new Company();
		lukecompany.recordTransaction(new CompanyTransaction("id12334", tday, "Cash", "Accounts-Payable", 1000.0, "Borrow from bank"));
		lukecompany.purchaseInventory(3, 50, tday, "Cash");
		lukecompany.purchaseInventory(5, 100, tday, "Cash");
		lukecompany.sellInventory(7, 180, tday, "Cash", "FIFO");
		
		lukecompany.printJournal();
		
	}*/

	// TODO function for total units
	private int getTotalUnits() {
		int totalUnits = 0;
		for (InventoryStorageEntry entry: storage) 
			totalUnits += entry.getUnits();
		return totalUnits;
	}
	
	//need to replace recordtransaction with isValidtransaction()
	public ArrayList<InventoryStorageEntry> purchaseInventory(double unitCost, int unitsToBuy, Date dt, String credittedAccount){
		//record purchase transaction
		double amount = unitCost*unitsToBuy;
		CompanyTransaction trans = new CompanyTransaction(generateNewID(), dt, "Inventory", credittedAccount, amount, "Purchase Inventory");
		
		//add to company's journal
		if(isValidTransaction(trans)) {
			recordTransaction(trans);
			InventoryStorageEntry entry = new InventoryStorageEntry(trans.getTransactionID(), unitCost, unitsToBuy);
			storage.add(entry);
			System.out.print("Inventory successfully purchased and added to storage\n");
		} else {
			System.out.print("Error: invalid purchase\n");
		}
		
		return storage;
	}
	
	public ArrayList<InventoryStorageEntry> sellInventory(double unitPrice, int unitsToSell, Date date, String debittedAccount, String costMethod){
		//if (unitsToSell > getTotalUnits()) System.out.println("Not enough inventory in store");
		//record revenue transaction
		double amount = unitPrice*unitsToSell;
		CompanyTransaction revTrans = new CompanyTransaction(generateNewID(), date, debittedAccount, "Sales-Revenue", amount, "Revenue from Goods sold");
		if(unitsToSell <= getTotalUnits() && accountList.get(debittedAccount).getAccountCategory() == CompanyAccountCategory.ASSET) {

			double cost = 0.0;
			//record cost transaction
			if(costMethod.equals("FIFO")) {
				for(InventoryStorageEntry entry: storage) {
					if(unitsToSell <= 0) {
						break;
					} else {
						int unitsSold = Math.min(entry.getUnits(), unitsToSell);
						unitsToSell -= unitsSold;
						entry.decreaseUnits(unitsSold);
						cost += unitsSold * entry.getUnitCost();
					}
				}
			} else if(costMethod.equals("LIFO")) {
				for(int i=storage.size()-1; i>=0; i--) {
					InventoryStorageEntry entry = storage.get(i);
					if(unitsToSell <= 0) {
						break;
					} else {
						int unitsSold = Math.min(entry.getUnits(), unitsToSell);
						unitsToSell -= unitsSold;
						entry.decreaseUnits(unitsSold);
						cost += unitsSold * entry.getUnitCost();
					}
				}
			} else {
				System.out.print("Error: invalid selling method\n");
				return storage;
			}
			CompanyTransaction costTrans = new CompanyTransaction(generateNewID(), date, "Cost-of-Goods-Sold", "Inventory", cost, "Cost of Goods sold");
			recordTransaction(revTrans);
			recordTransaction(costTrans);
			
		} else if (unitsToSell > getTotalUnits()) {
			System.out.print("Error: not enough inventory in store\n");
		} else { // accountList.get(debittedAccount).getAccountCategory() != CompanyAccountCategory.ASSET
			System.out.print("Error: " + debittedAccount + " is not an asset account, therefore cannot be debited for the sale of inventory\n");
		}
		return storage;
	}

}
