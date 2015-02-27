package de.cc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.cc.Exporter;
import de.cc.ServiceType;
import de.cc.Subscriber;
import de.cc.contract.Budget;
import de.cc.contract.Business;
import de.cc.contract.Contract;
import de.cc.contract.Premium;
import de.cc.phone.FeaturePhone;
import de.cc.phone.Phone;
import de.cc.phone.SimpleSmartPhone;
import de.cc.phone.SuperSmartPhone;
import de.cc.ran.RAN;

public class Controller {
	
	private Scanner sc = new Scanner(System.in);
	private Exporter exp = new Exporter();
	private List<Subscriber> subs;
	private SubscriberController subCon= new SubscriberController(this);
	private SessionController sessionCon= new SessionController(this);
	private InvoiceController invoiceCon= new InvoiceController(this);
	
	public Controller() {
		subs = exp.importSubscribers();
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
		String search = readString();
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
		String in = sc.nextLine().trim();
		sc.reset();
		return in;
	}
	
	public int readInt(){
		int in=-1;
		try {
			in = Integer.parseInt(sc.nextLine().trim());
		} catch (Throwable e) {
			sc.reset();
			return in;
		}
		sc.reset();
		return in;
	}
	
	public void printStartMenu(){
		System.out.println("(1) Subscriber Management");
		System.out.println("(2) Session Management");
		System.out.println("(3) Invoice");
		System.out.println("(4) Save & Close");
		System.out.println("(5) Simulation");
		
		int i = readInt();
		try {
			switch (i) {
			case 1:
				subCon.printSubscriberManagementMenu();
				break;
			case 2:
				sessionCon.printSessionManagementMenu();
				break;
			case 3:
				invoiceCon.printInvoiceMenu();
				break;
			case 4:
				close();
				break;
			case 5:
				simulate();
				break;
			default:
				System.out.println("Illegal Input");
				printStartMenu();
			}
		} catch (Exception e) {
			System.out.println("An internal error occured. Back to main menu...");
			printStartMenu();
		}
	}
	
	public void save() {
		exp.export(subs);
	}
	
	public void close() {
		save();
		sc.close();
	}
	
	public void simulate() {
		final Contract[] contracts = {new Budget(), new Business(), new Premium()};
		final Phone[] phones = {new FeaturePhone(), new SimpleSmartPhone(), new SuperSmartPhone()};

		List<Subscriber> oldSubs = new ArrayList<Subscriber>(subs);
		subs.clear();
		
		System.out.print("Creating simulated subscribers ... ");
		
		for (int c=0; c<3; c++) {
			for (int p=0; p<3; p++) {
				
				int orderNum = (c+1)*(p+1);
				String msinTpl = "000000"+orderNum+"%03d";
				
				for (int i=0; i<1000; i++) {
					Subscriber sub = new Subscriber();
					sub.setId(String.format(msinTpl, i));
					sub.setName("test" + orderNum + "-" + i);
					sub.setContract(contracts[c]);
					sub.setPhone(phones[p]);
					subs.add(sub);
				}
			}
		}
		
		System.out.print("finished.\nSimulating sessions ... ");
		
		long revenue = 0;
		for (Subscriber sub: subs) {
			for (int i=0;i<85;i++) {
				sub.useService(ServiceType.VOICE_CALL, 600, sub.getPhone().getRAN());
			}
			for (int i=0;i<10;i++) {
				RAN ran = sub.getPhone().getRAN();
				while(0 < sub.useService(ServiceType.BROWSING, 600, ran)) {
					sub.addData();
				}
			}
			for (int i=0;i<5;i++) {
				RAN ran = sub.getPhone().getRAN();
				while(0 < sub.useService(ServiceType.VIDEO, 600, ran)) {
					sub.addData();
				}
			}
			revenue += sub.getCurrentFee();
			sub.resetSessions();
		}
	
		System.out.println("finished.\n\nCalculated revenue: " + revenue/100 + " €");
		
		subs = oldSubs;
		printStartMenu();
	}
}
