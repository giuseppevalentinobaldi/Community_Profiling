package twitterOntology.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import twitterOntology.neo4j.Neo4jOGM;
import twitterOntology.neo4j.Neo4jUtil;
import twitterOntology.twitter.TwitterUserData;
import twitterOntology.twitter.TwitterUtil;

public class MainTwitterOntology {

	public static void CreateTwitterOntology(long userID) throws Exception {
		TwitterUtil twitter;
		TwitterUserData twitterUserData;
		
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		
		long startTime=0;
		boolean bool = true;
		
		while(bool){
		
			System.out.print("Choose your visualization graph\n\t 1: Extended view\n\t 2: Compact view\n (1 or 2)?:");
			String read = br.readLine();
			
			if (read.equals("1")) {
				
				bool = false;
				startTime=System.currentTimeMillis();
				Neo4jUtil neo4j = new Neo4jUtil("bolt://localhost:7687", "neo4j", "neo4j");
				twitter = new TwitterUtil();
				twitterUserData = twitter.getUserData(userID);
				neo4j.printUserData(twitterUserData);
				neo4j.close();
				
			} else if (read.equals("2")) {
				
				bool = false;
				startTime=System.currentTimeMillis();
				Neo4jOGM nogm = new Neo4jOGM("localhost:7687", "neo4j", "neo4j");
				twitter = new TwitterUtil();
				twitterUserData = twitter.getUserData(userID);
				nogm.printCompactUserData(twitterUserData);
				
			}
			
		}
			
		long stopTime = System.currentTimeMillis();
		System.out.println("Job Finished in " + (stopTime - startTime) / 1000.0 + " seconds");
		
	}
}