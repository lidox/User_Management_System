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
			
			//hat er genug datenvolumen?
			int left = sub.useService(serviceType, time);
			while(left>0){
				System.out.println("Do you want extra 1 GB for 10 Euro?");
				if(wantMore(controller.readString(".+"))){
					sub.addData();
				}
				else{
					sub.useService(serviceType, (time-left));
					break;
				}
				left = sub.useService(serviceType, time);
			}
			

			
			//System.out.println(sub.getName()+ serviceType + time);
			//sub.useService(serviceType, time);
		
			System.out.println(String.format("262-42-%s - %-8s Minutes: %4s, ServiceType: %4s, Used MB: %4s, Left MB: %4s MB, signal quality: %4s",
                    sub.getId(),
                    sub.getName(),
                    time,
                    serviceType.toString(),
                    sub.getSessions().get(sub.getSessions().size()-1).getDataVolume(),
                    sub.getLeftDataVolume(),
                    sub.getSessions().get(sub.getSessions().size()-1)));
			
		} catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		controller.printStartMenu();
		
	}
	
	private boolean wantMore(String readString) {
		String type = readString.toLowerCase();
		
		if(type.equals("no") || type.equals("n"))
			return false;
		
		if(type.equals("yes") || type.equals("y"))
			return true;
		
		
		//if(type.equals("quit") || type.equals("q"))
		//	throw new IllegalArgumentException("");
		
		System.out.println("Incorrect answer. Yes or no are accepted. Type again");
		return wantMore(readString);
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