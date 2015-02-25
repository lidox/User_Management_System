package de.cc;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestController {
	
	private static Controller con;
	
	@BeforeClass
	public static void setUp(){
		con = new Controller();
	}

	@Test
	public void testReadString() {
		System.out.print("get string:");
		System.out.println(con.readString());
	}

}
