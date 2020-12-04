package personalAccounting;

public class ExTransactionNotExist extends Exception{
	private static final long serialVersionUID = 1L;
	public ExTransactionNotExist() {super("Transaction does not exist!");}
	public ExTransactionNotExist(String message) { super(message); }
}
