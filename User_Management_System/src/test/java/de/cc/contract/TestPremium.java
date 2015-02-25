package de.cc.contract;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestPremium {
	
	private static Contract conctract;

	@BeforeClass
	public static void setUp(){
		conctract = new Premium();
	}
	
	@Test
	public void testBasicFee1() {
		assertEquals(500, conctract.getBasicFee()); 
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
		assertEquals(5000, conctract.getFreeData()); 
	}

}
