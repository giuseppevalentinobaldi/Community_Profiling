package main;

import java.util.List;

import neo4j.Neo4jUtil;
import twitter.TwitterUtil;

public class Main{

	public static void main(String args[]) throws Exception {
		TwitterUtil twitter = new TwitterUtil();
		List<List<String>> lls=twitter.getTweetDataItems(769181646176284672L);
		@SuppressWarnings("resource")
		Neo4jUtil neo4j= new Neo4jUtil("bolt://localhost:7687", "neo4j", "neo4j");
		lls.forEach( l1 -> neo4j.printTweets(l1));
		System.exit(0);
	}
}