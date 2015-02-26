package de.cc.contract;

public class Business implements Contract {

	int dataVolume = 2000;
	int basicFee = 300;
	
	public int getFreeData() {
		return dataVolume;
	}

	public int getFreeMinutes() {
		return 100;
	}

	public int getPricePerExtraMinute() {
		return 5;
	}

	public int getBasicFee() {
		return basicFee;
	}

	public void addData(int data, int price) {
		dataVolume += data;
		basicFee += price;
	}

	public void resetContract() {
		dataVolume = 2000;
		basicFee = 300;
	}

}
