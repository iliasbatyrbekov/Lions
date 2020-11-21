package main;

public class ExUpdateBalanceErr extends Exception{
	private static final long serialVersionUID = 1L;
	public ExUpdateBalanceErr() {super("Balance could not be updated");}
	public ExUpdateBalanceErr(String message) { super(message); }
}
