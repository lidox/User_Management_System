package de.cc;

import java.util.ArrayList;
import java.util.List;

import de.cc.contract.Contract;
import de.cc.phone.Phone;

public class Subscriber {
	private String name;
	private String id;
	private List<Session> sessions = new ArrayList<Session>();
	private Contract contract;
	private Phone phone;
	
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
			if (s.getService() == ServiceType.VOICE_CALL) {
				sum += s.getDauer();
			}
		}
		return sum;
	}
	public int getLeftDataVolume() {
		return Math.max(contract.getFreeData()-getUsedDataVolume(), 0);
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
	
	public void useService(ServiceType service, int time) {
		int volume = 0;
		switch (service) {
		case VOICE_CALL:
			sessions.add(new Session(service, time));
			return;
		case BROWSING:
			volume = Math.min(phone.getThroughput(), 2)*time;
			sessions.add(new Session(service, time, volume));
			break;
		case VIDEO:
			volume = phone.getThroughput()*time;
			sessions.add(new Session(service, time, volume));
			break;
		}
	}
	
	public List<Session> getSessions() {
		return sessions;
	}
	
	public void resetSessions() {
		sessions.clear();
	}
	
	/**
	 * @return The subscriber transformed to a comma-separated line
	 */
	public String serialize() {
		return id + "," + name + "," + sessions
				+ "," + contract.getClass().getName() + "," + phone.getClass().getName();
	}
	
	/**
	 * Sets all data corresponding to given serialized form
	 * 
	 * @param data String-representation of the subscriber, as produced by serialize()
	 */
	public void deserialize(String data) {
		String[] parts = data.split(",");
		if (parts.length != 6) {
			throw new IllegalArgumentException("Wrong data format");
		}

		try {
			setId(parts[0]);
			setName(parts[1]);
			//TODO: session
			setContract((Contract) Class.forName(parts[4]).getConstructor().newInstance());
			setPhone((Phone) Class.forName(parts[5]).getConstructor().newInstance());
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public String toString() {
		String phone = getPhone().getClass().getName();
		String contract = getContract().getClass().getName();
		return String.format("262-42-%s - %-25s Voice: %4d min, Data: %4d MB, %-20s %s",
			getId(),
			getName(),
			getUsedMinutes(),
			getUsedDataVolume(),
			phone.substring(phone.lastIndexOf(".")+1),
			contract.substring(contract.lastIndexOf(".")+1)
		);
	}
}
