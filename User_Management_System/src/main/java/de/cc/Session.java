package de.cc;

public class Session {
	private static final int[] signalQualities = {0, 10, 25, 50};
	
	private int time;
	private int dataVolume;
	private int signalQuality;
	private ServiceType service;
	
	public Session(ServiceType service, int time) {
		this(service, time, 0);
	}
	
	public Session(ServiceType service, int time, int dataVolume) {
		this.service = service;
		this.time = time;
		this.dataVolume = dataVolume;
		signalQuality = signalQualities[(int) (Math.random()*4)];
	}

	public int getDauer() {
		return time;
	}

	public ServiceType getService() {
		return service;
	}
	
	public int getDataVolume() {
		return dataVolume;
	}
	
	public double getSignalQuality() {
		return signalQuality/100.;
	}
	
	/**
	 * @return The session transformed to a semicolon-separated line
	 */
	public String serialize() {
		return time + ";" + dataVolume + ";" + signalQuality + ";" + service;
	}
	
	/**
	 * Sets all data corresponding to given serialized form
	 * 
	 * @param data String-representation of the session, as produced by serialize()
	 */
	public void deserialize(String data) {
		String[] parts = data.split(";");
		if (parts.length != 4) {
			throw new IllegalArgumentException("Wrong data format");
		}

		try {
			time = Integer.parseInt(parts[0]);
			dataVolume = Integer.parseInt(parts[1]);
			signalQuality = Integer.parseInt(parts[2]);
			service = ServiceType.valueOf(parts[3]);
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public String toString() {
		return String.format("Service: %s, Duration: %d min, Signal-Quality: %d%%, Datavolume: %d MB",
			service, time, signalQuality, dataVolume
		);
	}
}
