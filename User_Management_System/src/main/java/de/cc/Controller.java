package de.cc;

import java.util.List;
import java.util.Scanner;

public class Controller {
	
	private Scanner sc = new Scanner(System.in);
	private Exporter exp = new Exporter();
	private List<Subscriber> subs;
	private SubscriberController subCon= new SubscriberController(this);
	private SessionController sessionCon= new SessionController(this);
	
	public Controller() {
		subs = exp.importSubscribers();
		printStartMenu();
	}
	
	public List<Subscriber> getSubscribers() {
		return subs;
	}

	/**
	 * Outputs a list of all subscribers
	 */
	public void listSubscribers() {
		for (Subscriber sub: getSubscribers()) {
			System.out.println(sub);
		}
	}
	
	/**
	 * Waits for input of name or MSIN, loops until match is found or search is quitted
	 * 
	 * @return Index of found subscriber
	 * @throws IllegalStateException if search is quitted
	 */
	public int searchSubscriber() {
		listSubscribers();
		System.out.println("Insert MSIN or name to search for (insert 'q' to quit search)");
		String search = readString(".+");
		if ("q".equals(search)) {
			throw new IllegalStateException("Search for subscriber was quitted by user");
		}
		int i = 0;
		for (Subscriber sub: subs) {
			if (sub.getId().equals(search) || sub.getName().equals(search)) {
				return i;
			}
			i++;
		}
		System.out.println("No match found!");
		return searchSubscriber();
	}
	
	public String readString(){
		return sc.nextLine();
	}
	
	public String readString(String pattern){
		String in = sc.next(pattern);
		sc.reset();
		return in;
	}
	
	public int readInt(){
		int in = sc.nextInt();
		sc.reset();
		return in;
	}
	
	public void printStartMenu(){
		System.out.println("(1) Subscriber Management");
		System.out.println("(2) Session Management");
		System.out.println("(3) Invoice");
		System.out.println("(4) Save & Close");
		
		switch (readInt()) {
		case 1:
			subCon.printSubscriberManagementMenu();
			break;
		case 2:
			sessionCon.printSessionManagementMenu();
			break;
		case 3:
			//printInvoice();
			break;
		case 4:
			close();
			break;
		default:
			System.out.println("Illegal Input");
			printStartMenu();
		}
	}
	
	public void close() {
		exp.export(subs);
		sc.close();
	}
}
