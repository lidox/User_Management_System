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
		System.out.println("Select User:");
		System.out.println("(1) Select User");

		
		switch (controller.readInt()) {
		case 1:
			//printSubscriberManagement();
			break;
		case 2:
			//printSessionManagement();
			break;
		case 3:
			//printInvoice();
			break;
		case 4:
			//close();
			break;
		case 5:
			controller.printStartMenu();
			break;
		default:
			System.out.println("Illegal Input");
			this.printSessionManagementMenu();;
		}
	}
	
	public String getFreeMinutesAndData(Subscriber sub){
		StringBuilder ret = new StringBuilder();
		ret.append("Der User '"+ sub.getName() +"' hat noch ");
		ret.append("X" + " Freiminuten und " + "Y" + " Datenvolumen.");
		return ret.toString();
	}
	
}
