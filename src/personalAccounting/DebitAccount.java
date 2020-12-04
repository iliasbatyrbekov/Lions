package personalAccounting;

public class DebitAccount extends Account {

	public DebitAccount(String accID, double balance) {
		super(accID, balance);
	}
	
	@Override
	public int updateBalance(double updateToBalance) {
		if (this.getBalance() + updateToBalance < 0) {
			return -1;
		}
		return super.updateBalance(updateToBalance);
	}
}
