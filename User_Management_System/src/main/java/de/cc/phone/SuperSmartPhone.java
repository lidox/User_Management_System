package de.cc.phone;

import de.cc.ran.HSPA;
import de.cc.ran.LTE;
import de.cc.ran.RAN;

public class SuperSmartPhone extends AbstractPhone {

	@Override
	protected RAN[] getRANs() {
		return new RAN[]{new LTE(), new HSPA()};
	}

}
