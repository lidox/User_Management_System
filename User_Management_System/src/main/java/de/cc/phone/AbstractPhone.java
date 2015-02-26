package de.cc.phone;
import de.cc.ran.RAN;

public abstract class AbstractPhone implements Phone {
	
	protected abstract RAN[] getRANs();

	public int getThroughput() {
		for (RAN ran: getRANs()) {
			int mbits = ran.getThroughput();
			if (mbits > 0) {
				return mbits;
			}
		}
		return 0;
	}

}
