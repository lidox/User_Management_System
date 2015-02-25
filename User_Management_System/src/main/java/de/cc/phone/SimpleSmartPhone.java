package de.cc.phone;

public class SimpleSmartPhone implements Phone {

	public int getThroughput() {
		Integer strenght = (int)(Math.random()*4); 
		
		switch(strenght){
		case 3:
			return (int) (10*0.5);
		case 2:
			return (int) (10*0.25);
		case 1:
			return (int) (10*0.1);
		default:
			return 0;
		}
	}

}
