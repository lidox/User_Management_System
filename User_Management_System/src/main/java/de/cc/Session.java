package de.cc;

import de.cc.ran.AbstractRAN;
import de.cc.ran.GSM;
import de.cc.ran.RAN;

public class Session {
	private static final int[] signalQualities = {0, 10, 25, 50};
	
	private int time;
	private int dataVolume;
	private int signalQuality;
	private RAN ran;
	private ServiceType service;
	
	private Session() {
		
	}
	
	public Session(ServiceType service, int time) {
		this(service, time, 0, new GSM());
	}
	
	public Session(ServiceType service, int time, int dataVolume, RAN ran) {
		this.service = service;
		this.time = time;
		this.dataVolume = dataVolume;
		this.ran = ran;
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
	
	public RAN getRAN() {
		return ran;
	}
	
	/**
	 * @return The session transformed to a semicolon-separated line
	 */
	public String serialize() {
		return time + ";" + dataVolume + ";" + signalQuality + ";" + ran.serialize() + ";" + service;
	}
	
	/**
	 * Sets all data corresponding to given serialized form
	 * 
	 * @param data String-representation of the session, as produced by serialize()
	 */
	public static Session deserialize(String data) {
		String[] parts = data.split(";");
		if (parts.length != 5) {
			throw new IllegalArgumentException("Wrong data format");
		}

		try {
			Session session = new Session();
			session.time = Integer.parseInt(parts[0]);
			session.dataVolume = Integer.parseInt(parts[1]);
			session.signalQuality = Integer.parseInt(parts[2]);
			session.ran = AbstractRAN.deserialize(parts[3]);
			session.service = ServiceType.valueOf(parts[4]);
			return session;
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public String toString() {
		return String.format("Service: %s, Duration: %.2f min, Signal-Quality: %d%% (%s), Datavolume: %d MB",
			service, time/60., signalQuality, ran, dataVolume
		);
	}
}
