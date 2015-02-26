package de.cc.contract;

public class Premium implements Contract {

	int dataVolume = 5000;
	int basicFee = 500;
	
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
		dataVolume = 5000;
		basicFee = 500;
	}

}
