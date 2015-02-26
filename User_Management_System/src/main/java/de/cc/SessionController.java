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
		System.out.println(".:: SESSION MANAGEMENT ::.");
		System.out.println("(1) Select User");

		
//		switch (controller.readString()) {
//		case "":
//			//printSubscriberManagement();
//			break;
//		default:
//			System.out.println("Illegal Input");
//			this.printSessionManagementMenu();;
//		}
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
