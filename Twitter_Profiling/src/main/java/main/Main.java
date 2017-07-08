package main;

import twitterOntology.main.*;
import influenceOntology.main.*;
import datasetReader.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		String user;
		IdsReader reader;
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);

		String mod = choiceMod();
		
		long startTime=System.currentTimeMillis();

		switch (mod) {
		case "111":
			System.out.print("Insert User ID:");
			user = br.readLine();
			MainTwitterOntology.CreateTwitterOntologyExtended(Long.parseLong(user));
			break;
		case "112":
			System.out.print("Insert User ID:");
			user = br.readLine();
			MainTwitterOntology.CreateTwitterOntologyCompact(Long.parseLong(user));
			break;
		case "121":
			reader = new IdsReader("data/checkedUsers.txt");
			while (reader.hasNext())
				MainTwitterOntology.CreateTwitterOntologyExtended(reader.nextIsAnother());
			break;
		case "122":
			reader = new IdsReader("data/checkedUsers.txt");
			while (reader.hasNext())
				MainTwitterOntology.CreateTwitterOntologyCompact(reader.nextIsAnother());
			break;
		case "211":
			System.out.print("Insert User ID:");
			user = br.readLine();
			MainInfluenceOntology.CreateInfluenceOntologyExtended(Long.parseLong(user));
			break;
		case "212":
			System.out.print("Insert User ID:");
			user = br.readLine();
			MainInfluenceOntology.CreateInfluenceOntologyCompact(Long.parseLong(user));
			break;
		case "221":
			reader = new IdsReader("data/checkedUsers.txt");
			while (reader.hasNext())
				MainInfluenceOntology.CreateInfluenceOntologyExtended(reader.nextIsAnother());
			break;
		case "222":
			reader = new IdsReader("data/checkedUsers.txt");
			while (reader.hasNext())
				MainInfluenceOntology.CreateInfluenceOntologyCompact(reader.nextIsAnother());
			break;
		}

		long stopTime = System.currentTimeMillis();
		System.out.println("Job Finished in " + (stopTime - startTime) / 1000.0 + " seconds");
		
		System.exit(0);

	}

	public static String choiceMod() throws IOException {
		String mod = "";
		System.out.print("Choose your ontology\n\t 1: Twitter Ontology\n\t 2: Influence Ontology\n (1 or 2)?:");
		mod += readMod();
		System.out.print("Choose your dataset\n\t 1: Single User\n\t 2: File checkedUsers\n (1 or 2)?:");
		mod += readMod();
		System.out.print("Choose your visualization graph\n\t 1: Extended view\n\t 2: Compact view\n (1 or 2)?:");
		mod += readMod();
		return mod;
	}

	public static String readMod() throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		String choice = br.readLine();
		if (!choice.equals("1") && !choice.equals("2")) {
			System.out.print("Insert a valid input");
			readMod();
		}
		return choice;
	}

}
