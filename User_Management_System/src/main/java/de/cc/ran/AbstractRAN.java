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
	
	public int getQualityValue() {
		return quality;
	}
	
	public String serialize() {
		return getClass().getName() + ":" + quality;
	}
	
	public static AbstractRAN deserialize(String data) {
		String[] parts = data.split(":");
		if (parts.length != 2) {
			throw new IllegalArgumentException("Wrong data format");
		}

		try {
			AbstractRAN ran = (AbstractRAN) Class.forName(parts[0]).getConstructor().newInstance();
			ran.quality = Integer.parseInt(parts[1]);
			return ran;
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public abstract String getName();
	
	@Override
	public String toString() {
		return getName() + " (" + quality + "%)";
	}

}
