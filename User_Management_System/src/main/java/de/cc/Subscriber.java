package de.cc;

import de.cc.contract.Contract;
import de.cc.phone.Phone;

public class Subscriber {
	private String name;
	private String id;
	private int usedMinutes;
	private int usedDataVolume;
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
		return usedMinutes;
	}
	public void addUsedMinutes(int usedMinutes) {
		this.usedMinutes += usedMinutes;
	}
	public int getUsedDataVolume() {
		return usedDataVolume;
	}
	public void addUsedDataVolume(int usedDataVolume) {
		this.usedDataVolume += usedDataVolume;
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
	public String serialize() {
		return id + "," + name + "," + usedMinutes + "," + usedDataVolume
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
			usedMinutes = Integer.parseInt(parts[2]);
			usedDataVolume = Integer.parseInt(parts[3]);
			setContract((Contract) Class.forName(parts[4]).getConstructor().newInstance());
			setPhone((Phone) Class.forName(parts[5]).getConstructor().newInstance());
		} catch (Exception e) {
			throw new IllegalArgumentException("Data could not be correctly parsed");
		}
	}
	
	public String toString() {
		return String.format("262 42 %s %25s %4d %4d %15s %15s",
			getId(),
			getName(),
			getUsedMinutes(),
			getUsedDataVolume(),
			getPhone(),
			getContract()
		);
	}
}
