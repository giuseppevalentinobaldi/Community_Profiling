package neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import twitter.TwitterUserData;
import twitter.TweetData;

public class Neo4jUtil {

	private final Driver driver;
	private final Session session;
	private final String property = "has_asserted_value";

	public Neo4jUtil(String uri, String user, String password) {

		this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		this.session = this.driver.session();

	}

	public void close() {

		this.session.close();
		this.driver.close();

	}

	public void printUserData(TwitterUserData twitterUserData) {
		
		String sID = "";
		
		// CREAZIONE NODO UTENTE
		
		// creazione del nodo utente (TwitterUser)
		session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
				Values.parameters("name", twitterUserData.getIdLabel(),"value", twitterUserData.getId(), "description",
				twitterUserData.getIdDescription()));
		
		
		// CREAZIONE NODI LITERAL UTENTE
		
		// creazione dei nodi literal (TwitterUserPart) dell'utente (TwitterUser) ed archi (property)
		
		// name
		sID = twitterUserData.getId()+twitterUserData.getNameLabel().replace(" ", "");
		session.run("CREATE (b:TwitterUserPart {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUserData.getNameLabel(),"value", twitterUserData.getName(), "description",
				twitterUserData.getNameDescription(),"id",sID));
		
		session.run("MATCH (a:TwitterUser),(b:TwitterUserPart) WHERE a.value = "+twitterUserData.getId()+
				" AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		// screen name
		sID = twitterUserData.getId()+twitterUserData.getScreenNameLabel().replace(" ", "");
		session.run("CREATE (b:TwitterUserPart {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUserData.getScreenNameLabel(),"value", twitterUserData.getScreenName(), "description",
				twitterUserData.getScreenNameDescription(),"id", sID));
		
		session.run("MATCH (a:TwitterUser),(b:TwitterUserPart) WHERE a.value = "+twitterUserData.getId()+
				" AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");

		
		// CREAZIONE NODI TWEET
		
		for(TweetData tweet : twitterUserData.getTweetDataList()){
			
			// creazione del nodo tweet (TweetItem)
			session.run("CREATE (a:TweetItem {name : {name}, value: {value}, description: {description}})",
					Values.parameters("name", tweet.getIdLabel(),"value", tweet.getId(), "description",
					tweet.getIdDescription()));
			
			session.run("MATCH (a:TwitterUser),(b:TweetItem) WHERE a.value = "+twitterUserData.getId()+
					" AND b.value = "+tweet.getId()+
					" CREATE (a)-[r:"+property+"]->(b)");
			
			
			// creazione nodi literal (TweetItemPart) del tweet (TweetItem)
			
			// message
			sID = tweet.getId()+tweet.getMessageLabel().replace(" ", "");
			session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", tweet.getMessageLabel(),"value", tweet.getMessage(), "description",
					tweet.getMessageDescription(),"id",sID));
			
			session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			// contributorsId
			if(tweet.getContributorsId().length > 0){
				
				sID = tweet.getId()+tweet.getContributorsIdLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getContributorsIdLabel(),"value", tweet.getContributorsId().toString(), "description",
						tweet.getContributorsIdDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// longitude
			if(tweet.getLongitude().equals("")){
				
				sID = tweet.getId()+tweet.getLongitudeLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getLongitudeLabel(),"value", tweet.getLongitude(), "description",
						tweet.getLongitudeDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// latitude
			if(tweet.getLatitude().equals("")){
				
				sID = tweet.getId()+tweet.getLatitudeLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getLatitudeLabel(),"value", tweet.getLatitude(), "description",
						tweet.getLatitudeDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
		}
		
	}

}





















