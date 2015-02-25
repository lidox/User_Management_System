package de.cc;

public class SubscriberController {

	private Controller controller;
	
	public SubscriberController(Controller controller) {
		this.controller = controller;
	}
	
	public void printSubscriberManagementMenu(){
		System.out.println("(1) Add Subscriber");
		System.out.println("(2) Edit Subscriber");
		System.out.println("(3) Remove Subscriber");
		System.out.println("(4) List Subscribers");
		System.out.println("(5) Return");
		
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
			this.printSubscriberManagementMenu();
		}
	}
	
}
