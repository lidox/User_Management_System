package de.cc.contract;

public interface Contract {
	
	/**
	 * 
	 * @return
	 * 	   get amount for minutes included in the contract
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
	 *    get the price per extra minute 
	 */
	int getPricePerExtraMinute();
}
