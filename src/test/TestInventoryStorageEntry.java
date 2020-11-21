package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import corporateAccounting.InventoryStorageEntry;

public class TestInventoryStorageEntry {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); 

	@Before
	public void setUpStreams() { 
	    System.setOut(new PrintStream(outContent)); 
	}

	@After
	public void cleanUpStreams() { 
	    System.setOut(null); 
	}
	
	@Test
	public void testDecreaseUnits1() {
		InventoryStorageEntry entry = new InventoryStorageEntry("0001", 3, 500);
		entry.decreaseUnits(30);
		assertEquals(470, entry.getUnits());
	}
	@Test
	public void testDecreaseUnits2() {
		InventoryStorageEntry entry = new InventoryStorageEntry("0001", 3, 500);
		entry.decreaseUnits(530);
		assertEquals("The amount to decrease is larger than what is left. Action denied.\n", outContent.toString());
	}
}
