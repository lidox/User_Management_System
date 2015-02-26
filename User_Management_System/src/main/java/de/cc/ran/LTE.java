/**
 * 
 */
package de.cc.ran;

/**
 * @author ewilzwe
 *
 */
public class LTE extends AbstractRAN {

	/* (non-Javadoc)
	 * @see de.cc.ran.AbstractRAN#getMaxThroughput()
	 */
	@Override
	protected int getMaxThroughput() {
		return 100;
	}

}
