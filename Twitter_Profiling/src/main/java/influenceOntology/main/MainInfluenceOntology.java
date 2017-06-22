package influenceOntology.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import influenceOntology.twitter.TwitterUserAccount;
import influenceOntology.twitter.TwitterUtil;
import influenceOntology.neo4j.Neo4jOGM;
//import twitterOntology.neo4j.Neo4jUtil;

public class MainInfluenceOntology {

	public static void main(String args[]) throws Exception {
		TwitterUtil twitter;
		TwitterUserAccount twitterUserAccount;
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		System.out.print("Choose your visualization graph\n\t 1: Extended view\n\t 2: Compact view\n (1 or 2)?:");
		String read = br.readLine();
		long startTime=System.currentTimeMillis();;
		if (read.equals("1")) {
			/*Neo4jUtil neo4j = new Neo4jUtil("bolt://localhost:7687", "neo4j", "neo4j");
			twitter = new TwitterUtil();
			twitterUserData = twitter.getUserData(769181646176284672L);
			neo4j.printUserData(twitterUserData);
			neo4j.close();*/
		} else if (read.equals("2")) {
			Neo4jOGM nogm = new Neo4jOGM("localhost:7687", "neo4j", "neo4j");
			twitter = new TwitterUtil();
			twitterUserAccount = twitter.getTwitterUserAccount(769181646176284672L);
			nogm.printCompactUserData(twitterUserAccount);
		}
		else{
			main(args);
		}
		long stopTime = System.currentTimeMillis();
		System.out.println("Job Finished in " + (stopTime - startTime) / 1000.0 + " seconds");
		System.exit(0);
	}
}