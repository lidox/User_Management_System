package de.cc.contract;

public class Premium implements Contract {

	public int getFreeData() {
		return 5000;
	}

	public int getFreeMinutes() {
		return 100;
	}

	public int getPricePerExtraMinute() {
		return 5;
	}


	public int getBasicFee() {
		return 5000;
	}
	
	@Override
	public String toString() {
		return "Premium";
	}

}
