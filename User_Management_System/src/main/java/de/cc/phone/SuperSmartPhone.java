package de.cc.phone;

public class SuperSmartPhone extends AbstractPhone {

	@Override
	protected int[] getTroughputs() {
		return new int[]{100, 10};
	}

}
