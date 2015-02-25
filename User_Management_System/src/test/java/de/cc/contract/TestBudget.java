package de.cc.contract;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBudget {
	private static Contract conctract;

	@BeforeClass
	public static void setUp(){
		conctract = new Budget();
	}
	
	@Test
	public void testBasicFee1() {
		assertEquals(100, conctract.getBasicFee()); 
	}
	
	@Test
	public void testMinutesIncluded1() {
		assertEquals(0, conctract.getFreeMinutes()); 
	}
	
	@Test
	public void testPricePerExtraMinute1() {
		assertEquals(10, conctract.getPricePerExtraMinute()); 
	}
	
	@Test
	public void testDataVolume1() {
		assertEquals(500, conctract.getFreeData()); 
	}
}
