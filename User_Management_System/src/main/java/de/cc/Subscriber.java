package de.cc;

public class Subscriber {
	private String name;
	private String id;
	private int usedMinutes;
	private int usedDataVolume;
	
	public String getName() {
		return name;
	}
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
	
	public String serialize() {
		return id + "," + name + "," + usedMinutes + "," + usedDataVolume;
	}
	
	public void deserialize(String data) {
		String[] parts = data.split(",");
		if (parts.length != 4) {
			throw new IllegalArgumentException("Wrong data format");
		}
		setId(parts[0]);
		setName(parts[1]);
		usedMinutes = Integer.parseInt(parts[2]);
		usedDataVolume = Integer.parseInt(parts[3]);
	}
}
