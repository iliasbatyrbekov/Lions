import java.sql.Date;

public class CompanyTransaction {

	private String debittedAccount;
	private String credittedAccount;
	private Date date;
	private String transactionID;

	public CompanyTransaction(String debittedAccount) {
		
	}
	
	public String getDebittedAccount() {
		// TODO - implement Transaction.getDebittedAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param debittedAccount
	 */
	public void setDebittedAccount(String debittedAccount) {
		// TODO - implement Transaction.setDebittedAccount
		throw new UnsupportedOperationException();
	}

	public String getCredittedAccount() {
		// TODO - implement Transaction.getCredittedAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param credittedAccount
	 */
	public void setCredittedAccount(String credittedAccount) {
		// TODO - implement Transaction.setCredittedAccount
		throw new UnsupportedOperationException();
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionID() {
		return this.transactionID;
	}

	/**
	 * 
	 * @param transactionID
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

}