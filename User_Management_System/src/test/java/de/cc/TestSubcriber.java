package de.cc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSubcriber {
	
	private static final String SERIALIZATION = "1234567890,Max Muster,10,50,de.cc.contract.Budget,de.cc.phone.SimpleSmartPhone";
	
	private Subscriber sub;

	@Before
	public void setUp() throws Exception {
		sub = new Subscriber();
	}

	@Test
	public void testSetId() {
		sub.setId("1234567890");
		assertEquals("1234567890", sub.getId());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetIdFailShort() {
		sub.setId("");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetIdFailLong() {
		sub.setId("12346578901");
	}

	@Test
	public void testSetName() {
		sub.setName("Max Muster");
		assertEquals("Max Muster", sub.getName());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetNameFailComma() {
		sub.setName("Muster, Max");
	}

	@Test
	public void testSerialize() {
		sub.deserialize(SERIALIZATION);
		assertEquals(SERIALIZATION, sub.serialize());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSerializeFailDataCount() {
		sub.deserialize("1234567890,Max Muster,10,50,de.cc.contract.Budget");
	}

}
