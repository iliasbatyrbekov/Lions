package main;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String [] args) throws FileNotFoundException{

		Scanner inputFileName = new Scanner(System.in);
		System.out.print("Please input the file pathname: ");
		String filepathname = inputFileName.nextLine();
		Scanner A = new Scanner(new File(filepathname));
		
		A.close();
		inputFileName.close();
	}
}