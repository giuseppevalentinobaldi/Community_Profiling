package influenceOntology.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import influenceOntology.twitter.TwitterUserAccount;
import influenceOntology.twitter.TwitterUtil;
import influenceOntology.neo4j.Neo4jOGM;
import influenceOntology.neo4j.Neo4jUtil;

public class MainInfluenceOntology {

	public static void CreateInfluenceOntology(long userID) throws Exception {
		TwitterUtil twitter;
		TwitterUserAccount twitterUserAccount;
		
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
				twitterUserAccount = twitter.getTwitterUserAccount(userID);
				neo4j.printTwitterUserAccount(twitterUserAccount);
				neo4j.close();
				
			} else if (read.equals("2")) {
				
				bool = false;
				startTime=System.currentTimeMillis();
				Neo4jOGM nogm = new Neo4jOGM("localhost:7687", "neo4j", "neo4j");
				twitter = new TwitterUtil();
				twitterUserAccount = twitter.getTwitterUserAccount(userID);
				nogm.printCompactUserData(twitterUserAccount);
				
			}
			
		}
		
		long stopTime = System.currentTimeMillis();
		System.out.println("Job Finished in " + (stopTime - startTime) / 1000.0 + " seconds");

	}
}