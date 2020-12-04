package main;

public class ExPlainNotExist extends Exception{
	private static final long serialVersionUID = 1L;
	public ExPlainNotExist() {super("Plain does not exist!");}
	public ExPlainNotExist(String message) { super(message); }
}
