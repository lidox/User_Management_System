package de.cc.phone;

import org.junit.Test;

public class TestSimpleSmartPhone {

	@Test
	public void testGetThroughput() {
		callGetThroughput();
	}
	
	public boolean callGetThroughput() {
		Integer strenght = (int)(Math.random()*4); 
		if(strenght >= 0 && strenght <= 3){
			return true;
		}
		return false;
	}
	
}
