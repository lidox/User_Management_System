package de.cc.phone;

import de.cc.ran.HSPA;
import de.cc.ran.RAN;

public class FeaturePhone extends AbstractPhone {

	@Override
	protected RAN[] getRANs() {
		return new RAN[]{new HSPA()};
	}
	
	@Override
	public String toString() {
		return "FeaturePhone";
	}
}
