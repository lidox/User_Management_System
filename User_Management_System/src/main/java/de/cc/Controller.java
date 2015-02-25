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
	
	public String readString(){
		return sc.next();
	}
	
	public int readInt(){
		return sc.nextInt();
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
	}
}
