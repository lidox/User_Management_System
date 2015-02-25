package de.cc.phone;

import org.junit.Test;

public class TestFeaturePhone {

	@Test
	public boolean testGetThroughput() {
		Integer strenght = (int)(Math.random()*4); 
		if(strenght >= 0 && strenght <= 3){
			return true;
		}
		return false;
	}
}
