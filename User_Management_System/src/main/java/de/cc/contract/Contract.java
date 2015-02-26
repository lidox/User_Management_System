package de.cc.contract;

public interface Contract {
	
	/**
	 * 
	 * @return
	 * 	   get amount of data included in the contract
	 */
	int getFreeData();
	
	/**
	 * 
	 * @return
	 *    get amount of free minutes included in the contract
	 */
	int getFreeMinutes();
	
	/**
	 * 
	 * @return
	 *    get the price per extra minute in cent
	 */
	int getPricePerExtraMinute();
	
	/**
	 * 
	 * @return
	 *    get the amount of basic fee included in the contract in cent
	 */
	int getBasicFee(); 
	
	/**
	 * add data volume and corresponding amount of money 
	 * @param data
	 *    the data volume to be added
	 * @param price
	 *    the amount of money (in cent) for the data
	 */
	void addData(int data, int price);
	
	/**
	 * Reset contact details to basics
	 */
	void resetContract();
}
