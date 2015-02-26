package de.cc.contract;

public class Business implements Contract {
	
	public int getFreeData() {
		return 2000;
	}

	public int getFreeMinutes() {
		return 100;
	}

	public int getPricePerExtraMinute() {
		return 5;
	}

	public int getBasicFee() {
		return 3000;
	}

}
