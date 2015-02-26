package de.cc.controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.cc.SessionController;

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

}
