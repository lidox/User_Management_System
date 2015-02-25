package de.cc.contract;

public class Budget implements Contract {

	public int getFreeData() {
		return 500;
	}

	public int getFreeMinutes() {
		return 0;
	}

	public int getPricePerExtraMinute() {
		return 10;
	}

	public int getBasicFee() {
		return 100;
	}

}
