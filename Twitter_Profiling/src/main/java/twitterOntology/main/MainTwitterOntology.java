package twitterOntology.main;

import twitterOntology.neo4j.Neo4jOGM;
import twitterOntology.neo4j.Neo4jUtil;
import twitterOntology.twitter.TwitterUserData;
import twitterOntology.twitter.TwitterUtil;

public class MainTwitterOntology {
	
	public static void CreateTwitterOntologyExtended(long userID) throws Exception {
		Neo4jUtil neo4j = new Neo4jUtil("bolt://localhost:7687", "neo4j", "neo4j");
		TwitterUtil twitter = new TwitterUtil();
		TwitterUserData  twitterUserData = twitter.getUserData(userID);
		neo4j.printUserData(twitterUserData);
		neo4j.close();
	}
	
	public static void CreateTwitterOntologyCompact(long userID) throws Exception {
		Neo4jOGM nogm = new Neo4jOGM("localhost:7687", "neo4j", "neo4j");
		TwitterUtil twitter = new TwitterUtil();
		TwitterUserData twitterUserData = twitter.getUserData(userID);
		nogm.printCompactUserData(twitterUserData);			
	}
	
}