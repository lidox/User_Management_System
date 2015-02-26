package de.cc;

import de.cc.contract.Budget;
import de.cc.contract.Business;
import de.cc.contract.Contract;
import de.cc.contract.Premium;
import de.cc.phone.FeaturePhone;
import de.cc.phone.Phone;
import de.cc.phone.SimpleSmartPhone;
import de.cc.phone.SuperSmartPhone;

public class SubscriberController {

	private Controller controller;

	public SubscriberController(Controller controller) {
		this.controller = controller;
	}

	public void printSubscriberManagementMenu() {
		System.out.println("(1) Add Subscriber");
		System.out.println("(2) Edit Subscriber");
		System.out.println("(3) Remove Subscriber");
		System.out.println("(4) List Subscribers");
		System.out.println("(5) Return");

		switch (controller.readInt()) {
		case 1:
			addSubscriber();
			break;
		case 2:
			// editSubscriber();
			break;
		case 3:
			// Remove Subscriber();
			break;
		case 4:
			listSubscribers();
			break;
		case 5:
			controller.printStartMenu();
			break;
		default:
			System.out.println("Illegal Input");
			this.printSubscriberManagementMenu();
		}
	}

	public void addSubscriber() {
		try {
			Subscriber sub = new Subscriber();

			System.out.print("Subscriber Name: ");
			sub.setName(controller.readString(".+"));

			System.out.print("Subscriber MSIN: ");
			sub.setId(controller.readString("\\d{10}"));

			System.out.println("Subscriber Terminal Type: ");
			sub.setPhone(inputTerminalType());

			System.out.println("Subscriber Subscription Type: ");
			sub.setContract(inputSubscriptionType());

			controller.getSubscriber().add(sub);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		controller.printStartMenu();

	}

	public Phone inputTerminalType() {
		System.out.println("(1) FeaturePhone");
		System.out.println("(2) SimpleSmartPhone");
		System.out.println("(3) SuperSmartPhone");

		switch (controller.readInt()) {
		case 1:
			return new FeaturePhone();
		case 2:
			return new SimpleSmartPhone();
		case 3:
			return new SuperSmartPhone();
		default:
			System.out.println("Illegal Input");
			return this.inputTerminalType();
		}
	}

	public Contract inputSubscriptionType() {
		System.out.println("(1) Budget");
		System.out.println("(2) Business");
		System.out.println("(3) Premium");

		switch (controller.readInt()) {
		case 1:
			return new Budget();
		case 2:
			return new Business();
		case 3:
			return new Premium();
		default:
			System.out.println("Illegal Input");
			return this.inputSubscriptionType();
		}
	}

	public void removeSubscriber() {

		listSubscribers();

		Subscriber sub = new Subscriber();

		int index = controller.searchsubscriber();

		if (index != -1) {
			controller.getSubscriber().remove(index);
		}

		controller.printStartMenu();

	}

	public void listSubscribers() {
		for (Subscriber sub : controller.getSubscriber()) {
			System.out.println(sub);
		}
		controller.printStartMenu();
	}

}
