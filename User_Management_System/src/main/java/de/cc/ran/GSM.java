/**
 * 
 */
package de.cc.ran;

/**
 * @author ewilzwe
 *
 */
public class GSM extends AbstractRAN {

	@Override
	protected int getMaxThroughput() {
		return 0;
	}
	
	@Override
	public String getName() {
		return "GSM";
	}

}
