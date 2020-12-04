package personalAccounting;

public class ExAccountNotExist extends Exception{
	private static final long serialVersionUID = 1L;
	public ExAccountNotExist() {super("Account does not exist!");}
	public ExAccountNotExist(String message) { super(message); }
}
