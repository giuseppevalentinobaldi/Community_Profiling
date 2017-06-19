package twitterOntology.neo4j;

import java.text.SimpleDateFormat;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import twitterOntology.twitter.TwitterUserData;
import twitterOntology.twitter.TweetData;

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
			if(!tweet.getContributorsId().equals("")){
				
				sID = tweet.getId()+tweet.getContributorsIdLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getContributorsIdLabel(),"value", tweet.getContributorsId().toString(), "description",
						tweet.getContributorsIdDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// longitude
			if(!tweet.getLongitude().equals("")){
				
				sID = tweet.getId()+tweet.getLongitudeLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getLongitudeLabel(),"value", tweet.getLongitude(), "description",
						tweet.getLongitudeDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// latitude
			if(!tweet.getLatitude().equals("")){
				
				sID = tweet.getId()+tweet.getLatitudeLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getLatitudeLabel(),"value", tweet.getLatitude(), "description",
						tweet.getLatitudeDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// timeStamp
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String formattedDate = sdf.format(tweet.getTimeStamp());
			
			sID = tweet.getId()+tweet.getTimeStampLabel().replace(" ", "");
			session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", tweet.getTimeStampLabel(),"value", formattedDate, "description",
					tweet.getTimeStampDescription(),"id",sID));
			
			session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			// hashTag
			if(!tweet.getHashTag().equals("")){
				
				sID = tweet.getId()+tweet.getHashTagLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getHashTagLabel(),"value", tweet.getHashTag(), "description",
						tweet.getHashTagDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// url
			if(!tweet.getUrl().equals("")){
				
				sID = tweet.getId()+tweet.getUrlLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getUrlLabel(),"value", tweet.getUrl(), "description",
						tweet.getUrlDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// mentionedUserId
			if(!tweet.getMentionedUserId().equals("")){
				
				sID = tweet.getId()+tweet.getMentionedUserIdLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getMentionedUserIdLabel(),"value", tweet.getMentionedUserId(), "description",
						tweet.getMentionedUserIdDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
			
			// countFavoriteUser
			sID = tweet.getId()+tweet.getCountFavoriteUserLabel().replace(" ", "");
			session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", tweet.getCountFavoriteUserLabel(),"value", tweet.getCountFavoriteUser(), "description",
					tweet.getCountFavoriteUserDescription(),"id",sID));
			
			session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			// screenNameRespondedUser
			if(!tweet.getScreenNameRespondedUser().equals("")){
				
				sID = tweet.getId()+tweet.getScreenNameRespondedUserLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getScreenNameRespondedUserLabel(),"value", tweet.getScreenNameRespondedUser(), "description",
						tweet.getScreenNameRespondedUserDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
			
			}
			
			
			// tweetIdResponded
			if(tweet.getTweetIdResponded() != -1){
				
				sID = tweet.getId()+tweet.getTweetIdRespondedLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getTweetIdRespondedLabel(),"value", tweet.getTweetIdResponded(), "description",
						tweet.getTweetIdRespondedDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
			
			}
			
			
			// userIdResponded
			if(tweet.getUserIdResponded() != -1){
				
				sID = tweet.getId()+tweet.getUserIdRespondedLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getUserIdRespondedLabel(),"value", tweet.getUserIdResponded(), "description",
						tweet.getUserIdRespondedDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
			
			}
			
			
			// language
			sID = tweet.getId()+tweet.getLanguageLabel().replace(" ", "");
			session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", tweet.getLanguageLabel(),"value", tweet.getLanguage(), "description",
					tweet.getLanguageDescription(),"id",sID));
			
			session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			// countRetweet
			sID = tweet.getId()+tweet.getCountRetweetLabel().replace(" ", "");
			session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", tweet.getCountRetweetLabel(),"value", tweet.getCountRetweet(), "description",
					tweet.getCountRetweetDescription(),"id",sID));
			
			session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			// source
			if(!tweet.getSource().equals("")){
			
				sID = tweet.getId()+tweet.getSourceLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getSourceLabel(),"value", tweet.getSource(), "description",
						tweet.getSourceDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
			
			}
			
			
			// coordinates
			if(!tweet.getCoordinates().equals("")){
			
				sID = tweet.getId()+tweet.getCoordinatesLabel().replace(" ", "");
				session.run("CREATE (b:TweetItemPart {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", tweet.getCoordinatesLabel(),"value", tweet.getCoordinates(), "description",
						tweet.getCoordinatesDescription(),"id",sID));
				
				session.run("MATCH (a:TweetItem),(b:TweetItemPart) WHERE a.value = "+tweet.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
			
			}
			
		}
		
	}

}
