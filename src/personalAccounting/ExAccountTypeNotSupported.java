package personalAccounting;

public class ExAccountTypeNotSupported extends Exception{
	private static final long serialVersionUID = 1L;
	public ExAccountTypeNotSupported() {super("Account type is not supported!");}
	public ExAccountTypeNotSupported(String message) { super(message); }
}
