package de.cc.controller;

import de.cc.ServiceType;
import de.cc.Subscriber;
import de.cc.ran.RAN;

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
			System.out.println("Select subscriber: ");
			int indexOfSubscriber = controller.searchSubscriber();
			Subscriber sub = controller.getSubscribers().get(indexOfSubscriber);
			
			System.out.print("");
			System.out.printf("The user '%s' has %.2f MB left data and %.2f left minutes\n",
				sub.getName(), sub.getLeftDataVolume()/8., sub.getLeftSeconds()/60.
			);
			
			System.out.print("Select service type: ");
			ServiceType serviceType = getServiceType(controller.readString()); 
			
			System.out.print("Select time (in minutes): ");
			int time = 60*getTime(controller.readString());
			
			//hat er genug datenvolumen?
			RAN ran = sub.getPhone().getRAN();
			int left = sub.useService(serviceType, time, ran);
			while(left>0){
				System.out.println("Do you want extra 1 GB for 10 Euro?");
				if(wantMore()){
					sub.addData();
				}
				else{
					sub.useService(serviceType, (time-left), ran);
					break;
				}
				left = sub.useService(serviceType, time, ran);
			}
			

			
			//System.out.println(sub.getName()+ serviceType + time);
			//sub.useService(serviceType, time);
		
			System.out.println(String.format("262-42-%s - %-8s %s, Left MB: %.2f MB",
                sub.getId(),
                sub.getName(),
                sub.getSessions().get(sub.getSessions().size()-1).toString(),
                sub.getLeftDataVolume()/8.
            ));
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		controller.printStartMenu();
		
	}
	
	private boolean wantMore() {
		String readString = controller.readString();
		String type = readString.toLowerCase();
		
		if(type.equals("no") || type.equals("n"))
			return false;
		
		if(type.equals("yes") || type.equals("y"))
			return true;
		
		
		//if(type.equals("quit") || type.equals("q"))
		//	throw new IllegalArgumentException("");
		
		System.out.println("Incorrect answer. Yes or no are accepted. Type again");
		return wantMore();
	}

	/**
	 * check if input is a valid service type
	 * @param userInput
	 *    the users input
	 * @return
	 *    String the service type
	 */
	public ServiceType getServiceType(String userInput) {
		String type = userInput.toLowerCase();
		
		boolean isBrowser = type.equals("browser");
		if(isBrowser)
			return ServiceType.BROWSING;
		
		boolean isVideo = type.equals("video");
		if(isVideo)
			return ServiceType.VIDEO;
		
		boolean isVoice = type.equals("voice");
		if(isVoice || type.equals("voicecall") || type.equals("voice call"))
			return ServiceType.VOICE_CALL; 
		
		
		throw new IllegalArgumentException("The service type is not valid. Choose browser, video or voice");
	}

	/**
	 * converts time string to int (in minutes)
	 * @param readString
	 *    String the users input
	 * @return 
	 *    int the time in minutes 
	 */
	public int getTime(String readString) {
		int result=0;
		try {
			result = Integer.parseInt(readString);
			
			if(result<1){
				throw new IllegalArgumentException("Time must be greater 0.");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Time must be an integer.");
		}

		return result;
	}
	
}
