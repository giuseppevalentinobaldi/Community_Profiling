package main;

import neo4j.Neo4jOGM;
import neo4j.Neo4jUtil;
import twitter.TwitterUserData;
import twitter.TwitterUtil;

public class Main {

	public static void main(String args[]) throws Exception {
		TwitterUtil twitter = new TwitterUtil();
		TwitterUserData twitterUserData = twitter.getUserData(769181646176284672L);
		//Neo4jUtil neo4j = new Neo4jUtil("bolt://localhost:7687", "neo4j", "neo4j");
		//neo4j.printCompactUserData(twitterUserData);
		//neo4j.close();
		Neo4jOGM nogm = new Neo4jOGM();
		nogm.printCompactUserData(twitterUserData);
		System.exit(0);
	}
}