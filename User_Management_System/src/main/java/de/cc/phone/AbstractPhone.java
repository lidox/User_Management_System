package de.cc.phone;
import de.cc.ran.RAN;

public abstract class AbstractPhone implements Phone {
	
	protected abstract RAN[] getRANs();

	public RAN getRAN() {
		RAN[] rans = getRANs();
		int i = 0;
		for (; i < rans.length; i++) {
			if (rans[i].getThroughput() > 0) {
				break;
			}
		}
		return rans[Math.min(i, rans.length-1)];
	}

}
