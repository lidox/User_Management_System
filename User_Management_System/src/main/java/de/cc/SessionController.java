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
			System.out.print("Select subscriber: ");
			int indexOfSubscriber = controller.searchSubscriber();
			Subscriber sub = controller.getSubscribers().get(indexOfSubscriber);
			
			System.out.print("The user '" +sub.getName()+ "' ");
			System.out.println(" has "+sub.getLeftDataVolume() +" MB left data and "+ sub.getLeftMinutes()+ " left minutes");
			
			System.out.print("Select service type: ");
			ServiceType serviceType = getServiceType(controller.readString(".+")); 
			
			System.out.print("Select time (in minutes): ");
			int time = getTime(controller.readString(".+"));
			
			System.out.println(sub.getName()+ serviceType + time);
			// berechnung auf den benutzer anwenden
			//sub.useService(serviceType, time);
		
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		controller.printStartMenu();
		
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
