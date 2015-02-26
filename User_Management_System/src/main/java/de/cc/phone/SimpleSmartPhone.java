package de.cc.phone;

import de.cc.ran.HSPA;
import de.cc.ran.RAN;

public class SimpleSmartPhone extends AbstractPhone {

	@Override
	protected RAN[] getRANs() {
		return new RAN[]{new HSPA()};
	}

}
