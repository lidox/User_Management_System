package de.cc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Exporter {

	public void export(List<Subscriber> list) {
		try {
			PrintWriter printWriter = new PrintWriter(new File("data.csv"));
			for (Subscriber sub : list) {
				printWriter.println(sub.serialize());
			}
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Subscriber> importSubscribers() {
		List<Subscriber> list = new ArrayList<Subscriber>();
		try {
			Scanner scanner = new Scanner(new File("data.csv"));
			while (scanner.hasNextLine()) {
				try {
					list.add(Subscriber.deserialize(scanner.nextLine()));
				} catch (IllegalArgumentException e) {
					continue;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
		}
		return list;
	}

}
