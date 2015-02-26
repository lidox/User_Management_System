package de.cc.contract;

public class Budget implements Contract {
	
	int dataVolume = 500;
	int basicFee = 100;

	public int getFreeData() {
		return dataVolume;
	}

	public int getFreeMinutes() {
		return 0;
	}

	public int getPricePerExtraMinute() {
		return 10;
	}

	public int getBasicFee() {
		return basicFee;
	}

	@Override
	public void addData(int data, int price) {
		dataVolume += data;
		basicFee += price;
	}

	@Override
	public void resetContract() {
		dataVolume = 500;
		basicFee = 100;
	}

	
}
