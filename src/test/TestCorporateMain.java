package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Scanner;

import org.junit.Test;

import corporateAccounting.CorporateMain;

public class TestCorporateMain {
	@Test
	public void testCorporateMain() throws FileNotFoundException, ParseException {
		Scanner fileIn = new Scanner(new File("testCorporate.txt"));
		CorporateMain.corporateMain(fileIn);
	}
}
