package com.team1241.frc2016.pid;

import java.io.*;
import java.util.*;

public class Constants {
	public Hashtable<String, Double> values;

	public Constants(String fileName) {
		System.out.println("Loading " + fileName);
		File file = new File("/text/" + fileName);
		
		
		
		try {
			Scanner in = new Scanner(file);
			while(in.hasNext()) {

			}

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find " + fileName);
			try {
				PrintWriter out = new PrintWriter (new FileWriter("/text/" + fileName));
				out.close();
				System.out.println("Created " + fileName);
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				System.out.println("Unable to create " + fileName);
			}
		}
	}

}
