package influenceOntology.main;

import influenceOntology.twitter.TwitterUserAccount;
import influenceOntology.twitter.TwitterUtil;
import influenceOntology.neo4j.Neo4jOGM;
import influenceOntology.neo4j.Neo4jUtil;

public class MainInfluenceOntology {

	public static void CreateInfluenceOntologyExtended(long userID) throws Exception { 	
		Neo4jUtil neo4j = new Neo4jUtil("bolt://localhost:7687", "neo4j", "neo4j");
		TwitterUtil twitter = new TwitterUtil();
		TwitterUserAccount twitterUserAccount = twitter.getTwitterUserAccount(userID);
		neo4j.printTwitterUserAccount(twitterUserAccount);
		neo4j.close();
	}
	
	public static void CreateInfluenceOntologyCompact(long userID) throws Exception {
		Neo4jOGM nogm = new Neo4jOGM("localhost:7687", "neo4j", "neo4j");
		TwitterUtil twitter = new TwitterUtil();
		TwitterUserAccount twitterUserAccount = twitter.getTwitterUserAccount(userID);
		nogm.printCompactUserData(twitterUserAccount);
	}
}