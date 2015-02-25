package de.cc.contract;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBusiness {
	private static Contract conctract;

	@BeforeClass
	public static void setUp(){
		conctract = new Business();
	}
	
	@Test
	public void testBasicFee1() {
		assertEquals(300, conctract.getBasicFee()); 
	}
	
	@Test
	public void testMinutesIncluded1() {
		assertEquals(100, conctract.getFreeMinutes()); 
	}
	
	@Test
	public void testPricePerExtraMinute1() {
		assertEquals(5, conctract.getPricePerExtraMinute()); 
	}
	
	@Test
	public void testDataVolume1() {
		assertEquals(2000, conctract.getFreeData()); 
	}
}
