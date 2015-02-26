package de.cc.phone;

public abstract class AbstractPhone implements Phone {
	private static final int[] signalQualities = {0, 10, 25, 50};
	
	protected abstract int[] getTroughputs();
	
	private double getSignalQuality() {
		return signalQualities[(int) (Math.random()*4)]/100.;
	}

	public int getThroughput() {
		for (int thoughput: getTroughputs()) {
			int mbits = (int) (getSignalQuality() * thoughput);
			if (mbits > 0) {
				return mbits;
			}
		}
		return 0;
	}

}
