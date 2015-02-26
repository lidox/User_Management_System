package de.cc.controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.cc.ServiceType;

public class TestSessionController {

	private static SessionController con;
	
	@BeforeClass
	public static void setUp(){
		con = new SessionController(null);
	}
	
	@Test
	public void testGetTime1() {
		assertEquals(10, con.getTime("10"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetTime3() {
		assertEquals(10, con.getTime("-8"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetTime4() {
		assertEquals(10, con.getTime("test"));
	}
	
	@Test
	public void testGetServiceType1() {
		assertEquals(ServiceType.BROWSING, con.getServiceType("browser"));
	}
	
	@Test
	public void testGetServiceType2() {
		assertEquals(ServiceType.VIDEO, con.getServiceType("vidEo"));
	}
	
	@Test
	public void testGetServiceType3() {
		assertEquals(ServiceType.VOICE_CALL, con.getServiceType("voice"));
	}
	
	@Test
	public void testGetServiceType4() {
		assertEquals(ServiceType.VOICE_CALL, con.getServiceType("voicecall"));
	}
	
	@Test
	public void testGetServiceType5() {
		assertEquals(ServiceType.VOICE_CALL, con.getServiceType("voice call"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetServiceType6() {
		assertEquals(ServiceType.VOICE_CALL, con.getServiceType("bla"));
	}

}
