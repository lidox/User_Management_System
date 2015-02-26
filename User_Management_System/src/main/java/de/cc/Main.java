package de.cc;

import de.cc.controller.Controller;

public class Main {

	public static void main(String[] args) {
		System.out.println("..:: Subscription Management by CallControl ::..\n");
		try {
			new Controller().printStartMenu();
		} catch (Throwable e) {
			System.out.println("A fatal, unrecoverable error occurred. The program will quit now.");
		}
	}

}
