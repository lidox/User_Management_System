/**
 * 
 */
package de.cc.ran;

/**
 * @author ewilzwe
 *
 */
public class HSPA extends AbstractRAN {

	/* (non-Javadoc)
	 * @see de.cc.ran.AbstractRAN#getMaxThroughput()
	 */
	@Override
	protected int getMaxThroughput() {
		return 10;
	}
	
	@Override
	public String getName() {
		return "HSPA";
	}

}
