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
		for (Subscriber sub: controller.getSubscribers()) {
			System.out.printf("%s, Cost: %.2f €", sub.toString(), sub.getCurrentFee()/100.);
			List<Session> listSess = sub.getSessions();
			for (Session session : listSess) {
				System.out.println("    " + session + "\n");
			}
			sub.resetSessions();
		}
		controller.printStartMenu();
		
	}
}
