package de.cc;

import java.util.ArrayList;
import java.util.List;

import de.cc.contract.Contract;
import de.cc.phone.Phone;
import de.cc.ran.RAN;

public class Subscriber {
	private String name;
	private String id;
	private List<Session> sessions = new ArrayList<Session>();
	private Contract contract;
	private Phone phone;
	private int additionalVolume;
	private int additionalCosts;
	
	public String getName() {
		return name;
	}
	/**
	 * Sets the subscriber's name. The name must not contain a comma
	 * 
	 * @param name The subscriber's name to set
	 * @throws IllegalArgumentException if name contains a comma
	 */
	public void setName(String name) {
		if (name.indexOf(',') >= 0) {
			throw new IllegalArgumentException("Name must not contain commata");
		}
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if (id.length() != 10) {
			throw new IllegalArgumentException("ID must have 10 digits");
		}
		this.id = id;
	}
	public int getUsedMinutes() {
		int sum = 0;
		for (Session s: sessions) {
			if (s.getService() == ServiceType.VOICE_CALL) {
				sum += s.getDauer();
			}
		}
		return sum;
	}
	public int getLeftMinutes() {
		return Math.max(contract.getFreeMinutes()-getUsedMinutes(), 0);
	}
	public int getUsedDataVolume() {
		int sum = 0;
		for (Session s: sessions) {
			if (s.getService() == ServiceType.BROWSING || s.getService() == ServiceType.VIDEO) {
				sum += s.getDataVolume();
			}
		}
		return sum;
	}
	public int getLeftDataVolume() {
		return Math.max(contract.getFreeData()+additionalVolume-getUsedDataVolume(), 0);
	}
	
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	public int useService(ServiceType service, int time) {
		RAN ran = phone.getRAN();
		int rate = 0;
		switch (service) {
		case VOICE_CALL:
			sessions.add(new Session(service, time));
			return 0;
		case BROWSING:
			rate = Math.min(ran.getThroughput(), 2);
			break;
		case VIDEO:
			rate = ran.getThroughput();
			break;
		}
		int volume = rate*time*60;
		int leftVolume = getLeftDataVolume();
		if (volume > leftVolume) {
			return (volume-leftVolume)/(rate*60);
		}
		sessions.add(new Session(service, time, volume, ran));
		return 0;
	}
	
	public List<Session> getSessions() {
		return sessions;
	}
	
	public void resetSessions() {
		sessions.clear();
	}
	
	/**
	 * add more data volume to the subscribers contract.
	 * In this case it's 1000 MB for 10 Euro.
	 */
	public void addData(){
		additionalCosts += 1000;
		additionalVolume += 1000;
	}
	
	public int getCurrentFee() {
		int extraMins = Math.max(getUsedMinutes() - contract.getFreeMinutes(), 0);
		return contract.getBasicFee()
			+ contract.getPricePerExtraMinute()*extraMins
			+ additionalCosts;
	}
	
	/**
	 * @return The subscriber transformed to a comma-separated line
	 */
	public String serialize() {
		String serialSession = "";
		for (Session session: sessions) {
			serialSession += "|" + session;
		}
		return id + "," + name + "," + additionalCosts + "," + additionalVolume
			+ "," + contract.getClass().getName() + "," + phone.getClass().getName()
			+ "," + (serialSession.isEmpty() ? "" : serialSession.substring(1));
	}
	
	/**
	 * Sets all data corresponding to given serialized form
	 * 
	 * @param data String-representation of the subscriber, as produced by serialize()
	 */
	public static Subscriber deserialize(String data) {
		String[] parts = data.split(",",-1);
		if (parts.length != 7) {
			throw new IllegalArgumentException("Wrong data format");
		}

		try {
			Subscriber sub = new Subscriber();
			sub.setId(parts[0]);
			sub.setName(parts[1]);
			sub.additionalCosts = Integer.parseInt(parts[2]);
			sub.additionalVolume = Integer.parseInt(parts[3]);
			sub.setContract((Contract) Class.forName(parts[4]).getConstructor().newInstance());
			sub.setPhone((Phone) Class.forName(parts[5]).getConstructor().newInstance());
			for(String session: parts[6].split("\\|")) {
				if (session.isEmpty()) {
					continue;
				}
				sub.sessions.add(Session.deserialize(session));
			}
			return sub;
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public String toString() {
		return String.format("262-42-%s - %-25s Voice: %4d min, Data: %4d MB, %-20s %s",
			getId(),
			getName(),
			getUsedMinutes(),
			getUsedDataVolume(),
			phone,
			contract
		);
	}
}
