package de.cc.controller;

import java.util.List;

import de.cc.Session;
import de.cc.Subscriber;

/**
 * This class does the invoice for each subscriber
 */
public class InvoiceController {
	
	private Controller controller;
	
	public InvoiceController(Controller controller) {
		this.controller = controller;
	}
	
	public void printInvoiceMenu(){
		System.out.println("(1) Do invoice");
		System.out.println("(2) Return");

		switch (controller.readInt()) {
		case 1:
			doInvoice();
			break;
		case 2:
			controller.printStartMenu();
			break;
		default:
			System.out.println("Illegal Input");
			this.printInvoiceMenu();
		}
	}

	/**
	 * do invoice for each subscriber
	 */
	private void doInvoice() {
		List<Subscriber> listSub = controller.getSubscribers();
		for (Subscriber subscriber: listSub) {
			System.out.println(subscriber.toString() + " Cost: " + subscriber.invoice());
			List<Session> listSess = subscriber.getSessions();
			for (Session session : listSess) {
				System.out.println(session.toString());
			}
			subscriber.resetSessions();
			controller.printStartMenu();
		}
		
	}
}
