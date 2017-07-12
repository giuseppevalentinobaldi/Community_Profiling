package main;

import twitterOntology.main.*;
import influenceOntology.main.*;
import datasetReader.*;
import tsvWriter.*;

public class TimestampMain {

	public static void main(String[] args) throws Exception {
		
		long user;
		//IdsReader reader = new IdsReader("data/checkedUsers.txt");
		IdsReader reader = new IdsReader("data/dataset.txt");
		TsvPrinter tsv = new TsvPrinter();
		tsv.init("timestamp.tsv");
		
		String[] arrayTime = new String[4];
		
		long startTime;
		long timestampOntology1, timestampOntology2, timestampTot; 
		
		while(reader.hasNext()){
			
			user = reader.nextIsAnother();
			arrayTime[0] = ""+user;
			
			startTime = System.currentTimeMillis();
			MainTwitterOntology.CreateTwitterOntologyCompact(user);
			timestampOntology1 = (System.currentTimeMillis() - startTime) / 1000;
			arrayTime[1] = ""+timestampOntology1;
			
			startTime = System.currentTimeMillis();
			MainInfluenceOntology.CreateInfluenceOntologyCompact(user);
			timestampOntology2 = (System.currentTimeMillis() - startTime) / 1000;
			arrayTime[2] = ""+timestampOntology2;
			
			timestampTot = timestampOntology1 + timestampOntology2;
			arrayTime[3] = ""+timestampTot;
			
			tsv.writeRow(arrayTime);
			
		}

	}

}
