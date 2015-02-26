package de.cc;



/**
 * this class handles all about Session Management 
 */
public class SessionController {
	
	private Controller controller;
	
	public SessionController(Controller controller) {
		this.controller = controller;
	}
	
	public void printSessionManagementMenu(){
		System.out.println("(1) Simulate session");
		System.out.println("(2) Return");

		switch (controller.readInt()) {
		case 1:
			simulateSession();
			break;
		case 2:
			controller.printStartMenu();
			break;
		default:
			System.out.println("Illegal Input");
			this.printSessionManagementMenu();
		}
	}

	public void simulateSession() {
		try {
			String sessionType;
			String subscriberId;
			String time;
			String service;
			
			System.out.print("Select subscriber: ");
			// get subscriber
			
			System.out.print("Select session type: ");
			// 
			
			System.out.print("Select time (in minutes): ");
			//
			
			System.out.print("Select service type: ");
			//
			
		
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		controller.printStartMenu();
		
	}
	
	public String getFreeMinutesAndData(Subscriber sub){
		StringBuilder ret = new StringBuilder();
		ret.append("Der User '"+ sub.getName() +"' hat noch ");
		ret.append("X" + " Freiminuten und " + "Y" + " Datenvolumen.");
		return ret.toString();
	}
	
	/**
	 * Get a subscriber by IMSI
	 * @param IMSI
	 *    the IMSI of the subscriber 
	 * @return
	 * 	  if subscriber not exists return null otherwise return subscriber 
	 */
	public Subscriber getSubscribor(String IMSI){
		for(Subscriber item:controller.getSubscriber()){
			if(item.getId().equals(IMSI.substring(5))){
				return item;
			}
		}
		return null;
	}

	/**
	 * Simulates a subscribers session
	 * @param sessionType
	 * @param subscriberId
	 * @param time
	 * @param service
	 */
	public void simulate(String sessionType,String subscriberId,String time, String service){

	}
	
	
	
}
