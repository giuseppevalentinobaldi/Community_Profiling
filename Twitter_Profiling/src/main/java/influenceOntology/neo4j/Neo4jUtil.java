package influenceOntology.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import influenceOntology.twitter.TwitterUserAccount;

public class Neo4jUtil {

	private final Driver driver;
	private final Session session;

	public Neo4jUtil(String uri, String user, String password) {

		this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		this.session = this.driver.session();

	}

	public void close() {

		this.session.close();
		this.driver.close();

	}
	
	public void printTwitterUserAccount(TwitterUserAccount twitterUser){
		
		String sID = "";
		String nodeID = "";
		String name = "";
		String property = "";
		
		
		// CREAZIONE NODO UTENTE
		
		// creazione del nodo utente (TwitterUser)
		session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
				Values.parameters("name", twitterUser.getAccountName(), "value", twitterUser.getId(), "description", ""));
		
		
		//creazione nodo account name (literal)
		sID = twitterUser.getId()+twitterUser.getAccountNameLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getAccountNameLabel(), "value", twitterUser.getAccountName(),
				"description", twitterUser.getAccountNameDescription(), "id", sID));
		
		property = twitterUser.getAccountNameLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+twitterUser.getId()+
				" AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		
		
		// CREAZIONE NODI GENERAL INFORMATION
		
		// creazione nodo general information
		name = "general information";
		sID = twitterUser.getId()+name.replace(" ", "");
		nodeID = twitterUser.getId()+name.replace(" ", "");
		session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id}})",
				Values.parameters("name", name, "description", "Twitter User Account", "id", sID));
		
		property = "has_general_information";
		session.run("MATCH (a:TwitterUser),(b:TwitterUserInformation) WHERE a.value = "+twitterUser.getId()+
				" AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo display name (literal)
		sID = twitterUser.getId()+twitterUser.getDisplayNameLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getDisplayNameLabel(), "value", twitterUser.getGi().getDisplayName(),
				"description", twitterUser.getDisplayNameDescription(), "id", sID));
		
		property = twitterUser.getDisplayNameLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo description (literal)
		sID = twitterUser.getId()+twitterUser.getDescriptionLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getDescriptionLabel(), "value", twitterUser.getGi().getDescription(),
				"description", twitterUser.getDescriptionDescription(), "id", sID));
		
		property = twitterUser.getDescriptionLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo numberTweet (literal)
		sID = twitterUser.getId()+twitterUser.getNumberTweetLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getNumberTweetLabel(), "value", twitterUser.getGi().getNumberTweet(),
				"description", twitterUser.getNumberTweetDescription(), "id", sID));
		
		property = twitterUser.getNumberTweetLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo tweetForDay (literal)
		sID = twitterUser.getId()+twitterUser.getTweetForDayLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getTweetForDayLabel(), "value", twitterUser.getGi().getTweetForDay(),
				"description", twitterUser.getTweetForDayDescription(), "id", sID));
		
		property = twitterUser.getTweetForDayLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo follower (literal)
		sID = twitterUser.getId()+twitterUser.getFollowerLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getFollowerLabel(), "value", twitterUser.getGi().getFollower(),
				"description", twitterUser.getFollowerDescription(), "id", sID));
		
		property = twitterUser.getFollowerLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo following (literal)
		sID = twitterUser.getId()+twitterUser.getFollowingLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getFollowingLabel(), "value", twitterUser.getGi().getFollowing(),
				"description", twitterUser.getFollowingDescription(), "id", sID));
		
		property = twitterUser.getFollowingLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo retweetPercentage (literal)
		sID = twitterUser.getId()+twitterUser.getRetweetPercentageLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getRetweetPercentageLabel(), "value", twitterUser.getGi().getRetweetPercentage(),
				"description", twitterUser.getRetweetPercentageDescription(), "id", sID));
		
		property = twitterUser.getRetweetPercentageLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo activeAccount (literal)
		sID = twitterUser.getId()+twitterUser.getActiveAccountLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getActiveAccountLabel(), "value", twitterUser.getGi().isActiveAccount(),
				"description", twitterUser.getActiveAccountDescription(), "id", sID));
		
		property = twitterUser.getActiveAccountLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo profileLocked (literal)
		sID = twitterUser.getId()+twitterUser.getProfileLockedLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getProfileLockedLabel(), "value", twitterUser.getGi().isProfileLocked(),
				"description", twitterUser.getProfileLockedDescription(), "id", sID));
		
		property = twitterUser.getProfileLockedLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo retrivedOn (literal
		sID = twitterUser.getId()+twitterUser.getRetrivedOnLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getRetrivedOnLabel(), "value", twitterUser.getGi().getRetrivedOn(),
				"description", twitterUser.getRetrivedOnDescription(), "id", sID));
		
		property = twitterUser.getRetrivedOnLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
	}
	
}







































