package de.cc.ran;

public abstract class AbstractRAN implements RAN {
	
	private static final int[] signalQualities = {0, 10, 25, 50};
	private int quality;
	
	public AbstractRAN() {
		quality = signalQualities[(int) (Math.random()*4)];
	}
	
	public int getThroughput() {
		return quality * getMaxThroughput() / 100;
	}

	protected abstract int getMaxThroughput();
	
	public String getQuality() {
		switch(quality) {
		default:
			return "n/a";
		case 10:
			return "low";
		case 25:
			return "medium";
		case 50:
			return "high";
		}
	}

}
