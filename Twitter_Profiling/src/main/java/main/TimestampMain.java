package main;

import twitterOntology.main.*;
import influenceOntology.main.*;
import datasetReader.*;
import tsvWriter.*;

public class TimestampMain {
	
	private static final int LIMITUSER = 3;

	public static void main(String[] args) throws Exception {
		
		long user;
		IdsReader reader = new IdsReader("data/checkedUsers.txt");
		TsvPrinter tsv = new TsvPrinter();
		tsv.init("timestamp.tsv");
		
		String[] arrayTime = new String[3];
		
		long startTime;
		long timestampOntology1, timestampOntology2, timestampTot; 
		
		int count = 0;
		while(reader.hasNext() && count < LIMITUSER){
			
			user = reader.nextIsAnother();
			
			startTime = System.currentTimeMillis();
			MainTwitterOntology.CreateTwitterOntologyCompact(user);
			timestampOntology1 = (System.currentTimeMillis() - startTime) / 1000;
			arrayTime[0] = ""+timestampOntology1;
			
			startTime = System.currentTimeMillis();
			MainInfluenceOntology.CreateInfluenceOntologyCompact(user);
			timestampOntology2 = (System.currentTimeMillis() - startTime) / 1000;
			arrayTime[1] = ""+timestampOntology2;
			
			timestampTot = timestampOntology1 + timestampOntology2;
			arrayTime[2] = ""+timestampTot;
			
			tsv.writeRow(arrayTime);
			
			count++;
			
		}

	}

}
