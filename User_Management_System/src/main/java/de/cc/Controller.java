package de.cc;

import java.util.Scanner;


public class Controller {
	
	private Scanner sc = new Scanner(System.in);
	
	public Controller() {
	}
	
	public void printStartMenu(){
		System.out.println("(1) Subscriber Management");
		System.out.println("(2) Session Management");
		System.out.println("(3) Invoice");
	}
	
	public String readString(){
		return sc.next();
	}
	
	public int readInt(){
		return sc.nextInt();
	}
}
