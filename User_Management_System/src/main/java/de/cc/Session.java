package de.cc;

import de.cc.ran.AbstractRAN;
import de.cc.ran.GSM;
import de.cc.ran.RAN;

public class Session {
	
	private int time;
	private int dataVolume;
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
	
	public int getSignalQualityInt() {
		return ran.getQualityValue();
	}
	
	public double getSignalQuality() {
		return getSignalQualityInt()/100.;
	}
	
	public RAN getRAN() {
		return ran;
	}
	
	/**
	 * @return The session transformed to a semicolon-separated line
	 */
	public String serialize() {
		return time + ";" + dataVolume + ";" + ran.serialize() + ";" + service;
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
			session.ran = AbstractRAN.deserialize(parts[2]);
			session.service = ServiceType.valueOf(parts[3]);
			return session;
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public String toString() {
		return String.format("Service: %s, Duration: %.2f min, Signal: %s, Datavolume: %.2f MB",
			service, time/60., ran, dataVolume/8.
		);
	}
}
