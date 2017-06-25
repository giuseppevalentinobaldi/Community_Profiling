package influenceOntology.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import influenceOntology.twitter.TwitterUserAccount;
import influenceOntology.twitter.URL;
import influenceOntology.twitter.Hashtag;
import influenceOntology.twitter.Image;

import java.util.Set;
import java.util.HashSet;

public class Neo4jUtil {

	private final Driver driver;
	private final Session session;
	
	private Set<Long> setUser;

	public Neo4jUtil(String uri, String user, String password) {

		this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		this.session = this.driver.session();
		this.setUser = new HashSet<Long>();

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
		int count = 0;
		
		
		// CREAZIONE NODO UTENTE
		
		// creazione del nodo utente (TwitterUser)
		session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
				Values.parameters("name", twitterUser.getAccountName(), "value", twitterUser.getId(), "description", "Twitter User Account"));
		
		
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
		name = "General Information";
		sID = twitterUser.getId()+name.replace(" ", "");
		nodeID = twitterUser.getId()+name.replace(" ", "");
		session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id}})",
				Values.parameters("name", name, "description", name+" user", "id", sID));
		
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
		
		
		
		
		// CREAZIONE NODI QUALITY METRIC
		
		// creazione nodo quality metric
		name = "Quality Metric";
		sID = twitterUser.getId()+name.replace(" ", "");
		nodeID = twitterUser.getId()+name.replace(" ", "");
		session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id}})",
				Values.parameters("name", name, "description", name+" user", "id", sID));
		
		property = "has_quality_metric";
		session.run("MATCH (a:TwitterUser),(b:TwitterUserInformation) WHERE a.value = "+twitterUser.getId()+
				" AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo hIndexRetweet (literal)
		sID = twitterUser.getId()+twitterUser.gethIndexRetweetLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.gethIndexRetweetLabel(), "value", twitterUser.getQm().gethIndexRetweet(),
				"description", twitterUser.gethIndexRetweetDescription(), "id", sID));
		
		property = twitterUser.gethIndexRetweetLabel().replace(" ", "_").replace("-", "");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo hIndexRetweetDaily (literal)
		sID = twitterUser.getId()+twitterUser.gethIndexRetweetDailyLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.gethIndexRetweetDailyLabel(), "value", twitterUser.getQm().gethIndexRetweetDaily(),
				"description", twitterUser.gethIndexRetweetDailyDescription(), "id", sID));
		
		property = twitterUser.gethIndexRetweetDailyLabel().replace(" ", "_").replace("-", "");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo hIndexFavorite (literal)
		sID = twitterUser.getId()+twitterUser.gethIndexFavoriteLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.gethIndexFavoriteLabel(), "value", twitterUser.getQm().gethIndexFavorite(),
				"description", twitterUser.gethIndexFavoriteDescription(), "id", sID));
		
		property = twitterUser.gethIndexFavoriteLabel().replace(" ", "_").replace("-", "");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo hIndexFavoriteDaily (literal)
		sID = twitterUser.getId()+twitterUser.gethIndexFavoriteDailyLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.gethIndexFavoriteDailyLabel(), "value", twitterUser.getQm().gethIndexFavoriteDaily(),
				"description", twitterUser.gethIndexFavoriteDailyDescription(), "id", sID));
		
		property = twitterUser.gethIndexFavoriteDailyLabel().replace(" ", "_").replace("-", "");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo replayRatio (literal)
		sID = twitterUser.getId()+twitterUser.getReplayRatioLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getReplayRatioLabel(), "value", twitterUser.getQm().getReplayRatio(),
				"description", twitterUser.getReplayRatioDescription(), "id", sID));
		
		property = twitterUser.getReplayRatioLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		//creazione nodo influenceMetric (literal)
		sID = twitterUser.getId()+twitterUser.getInfluenceMetricLabel().replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUser.getInfluenceMetricLabel(), "value", twitterUser.getQm().getInfluenceMetric(),
				"description", twitterUser.getInfluenceMetricDescription(), "id", sID));
		
		property = twitterUser.getInfluenceMetricLabel().replace(" ", "_");
		session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
				"' AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		
		
		// CREAZIONE NODI URL
		
		count = 0;
		for(URL url : twitterUser.getUrl()){
		
			// creazione nodo url
			name = "URL";
			sID = twitterUser.getId()+name.replace(" ", "")+count;
			nodeID = twitterUser.getId()+name.replace(" ", "")+count;
			session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id}})",
					Values.parameters("name", name, "description", name, "id", sID));
			
			property = "included_url";
			session.run("MATCH (a:TwitterUser),(b:TwitterUserInformation) WHERE a.value = "+twitterUser.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			//creazione nodo domain (literal)
			sID = twitterUser.getId()+twitterUser.getDomainLabel().replace(" ", "")+count;
			session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", twitterUser.getDomainLabel(), "value", url.getDomain(),
					"description", twitterUser.getDomainDescription(), "id", sID));
			
			property = twitterUser.getDomainLabel().replace(" ", "_");
			session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
					"' AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			//creazione nodo url (literal)
			sID = twitterUser.getId()+twitterUser.getUrlLabel().replace(" ", "")+count;
			session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", twitterUser.getUrlLabel(), "value", url.getUrl(),
					"description", twitterUser.getUrlDescription(), "id", sID));
			
			property = twitterUser.getUrlLabel().replace(" ", "_");
			session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
					"' AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			//creazione nodo fullurl (literal)
			sID = twitterUser.getId()+twitterUser.getFullurlLabel().replace(" ", "")+count;
			session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", twitterUser.getFullurlLabel(), "value", url.getFullurl(),
					"description", twitterUser.getFullurlDescription(), "id", sID));
			
			property = twitterUser.getFullurlLabel().replace(" ", "_");
			session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
					"' AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			
			count++;
			
		}
		
		
		
		
		// CREAZIONE NODI HASHTAG
		
		count = 0;
		for(Hashtag hashtag : twitterUser.getHashtag()){
			
			// creazione nodo hashtag
			name = "Hashtag";
			sID = twitterUser.getId()+name.replace(" ", "")+count;
			nodeID = twitterUser.getId()+name.replace(" ", "")+count;
			session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id}})",
					Values.parameters("name", name, "description", name, "id", sID));
			
			property = "included_hashtag";
			session.run("MATCH (a:TwitterUser),(b:TwitterUserInformation) WHERE a.value = "+twitterUser.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			//creazione nodo hashtag (literal)
			sID = twitterUser.getId()+twitterUser.getHashtagLabel().replace(" ", "")+count;
			session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", twitterUser.getHashtagLabel(), "value", hashtag.toString(),
					"description", twitterUser.getHashtagDescription(), "id", sID));
			
			property = twitterUser.getHashtagLabel().replace(" ", "_");
			session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
					"' AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			count++;
			
		}
		
		
		
		
		// CREAZIONE NODO IMAGE
		
		count = 0;
		for(Image image : twitterUser.getImage()){
			
			// creazione nodo image
			name = "Image";
			sID = twitterUser.getId()+name.replace(" ", "")+count;
			nodeID = twitterUser.getId()+name.replace(" ", "")+count;
			session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id}})",
					Values.parameters("name", name, "description", name, "id", sID));
			
			property = "included_image";
			session.run("MATCH (a:TwitterUser),(b:TwitterUserInformation) WHERE a.value = "+twitterUser.getId()+
					" AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			//creazione nodo image (literal)
			sID = twitterUser.getId()+twitterUser.getImageurlLabel().replace(" ", "")+count;
			session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
					Values.parameters("name", twitterUser.getImageurlLabel(), "value", image.getImageurl(),
					"description", twitterUser.getImageurlDescription(), "id", sID));
			
			property = twitterUser.getImageurlLabel().replace(" ", "_");
			session.run("MATCH (a:TwitterUserInformation),(b:Literal) WHERE a.id = '"+nodeID+
					"' AND b.id = '"+sID+
					"' CREATE (a)-[r:"+property+"]->(b)");
			
			count++;
			
		}
		
		
		
		
		// CREAZIONE USER MENTIONS

		for(TwitterUserAccount user : twitterUser.getMentions()){
			
			if(twitterUser.getId() != user.getId()){
			
				if(!setUser.contains(user.getId())){
					
					// creazione del nodo utente (TwitterUser)
					session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
							Values.parameters("name", user.getAccountName(), "value", user.getId(), "description", "Twitter User Account"));
					
					property = "has_mentioned";
					session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
							" AND b.value = "+user.getId()+
							" CREATE (a)-[r:"+property+"]->(b)");
					
					
					//creazione nodo account name (literal)
					sID = user.getId()+user.getAccountNameLabel().replace(" ", "");
					session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
							Values.parameters("name", user.getAccountNameLabel(), "value", user.getAccountName(),
							"description", user.getAccountNameDescription(), "id", sID));
					
					property = user.getAccountNameLabel().replace(" ", "_");
					session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+user.getId()+
							" AND b.id = '"+sID+
							"' CREATE (a)-[r:"+property+"]->(b)");
					
					// inserimeto user in setUser
					setUser.add(user.getId());
				
				}
				else{
					
					property = "has_mentioned";
					session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
							" AND b.value = "+user.getId()+
							" CREATE (a)-[r:"+property+"]->(b)");
					
				}
			
			}
			
		}
		
		
		
		
		// CREAZIONE USER REPLY TO

		for(TwitterUserAccount user : twitterUser.getReplyTo()){
			
			if(twitterUser.getId() != user.getId()){
				
				if(!setUser.contains(user.getId())){
			
					// creazione del nodo utente (TwitterUser)
					session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
							Values.parameters("name", user.getAccountName(), "value", user.getId(), "description", "Twitter User Account"));
					
					property = "has_replied_to";
					session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
							" AND b.value = "+user.getId()+
							" CREATE (a)-[r:"+property+"]->(b)");
					
					
					//creazione nodo account name (literal)
					sID = user.getId()+user.getAccountNameLabel().replace(" ", "");
					session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
							Values.parameters("name", user.getAccountNameLabel(), "value", user.getAccountName(),
							"description", user.getAccountNameDescription(), "id", sID));
					
					property = user.getAccountNameLabel().replace(" ", "_");
					session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+user.getId()+
							" AND b.id = '"+sID+
							"' CREATE (a)-[r:"+property+"]->(b)");
					
					// inserimeto user in setUser
					setUser.add(user.getId());
					
				}
				else{
					
					property = "has_replied_to";
					session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
							" AND b.value = "+user.getId()+
							" CREATE (a)-[r:"+property+"]->(b)");
					
				}
			
			}
			
		}
		
		
		
		
		// CREAZIONE USER HAS FOLLOWER

		for(TwitterUserAccount user : twitterUser.getHasFollower()){
			
			if(!setUser.contains(user.getId())){
			
				// creazione del nodo utente (TwitterUser)
				session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
						Values.parameters("name", user.getAccountName(), "value", user.getId(), "description", "Twitter User Account"));
				
				property = "has_follower";
				session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
						" AND b.value = "+user.getId()+
						" CREATE (a)-[r:"+property+"]->(b)");
				
				
				//creazione nodo account name (literal)
				sID = user.getId()+user.getAccountNameLabel().replace(" ", "");
				session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", user.getAccountNameLabel(), "value", user.getAccountName(),
						"description", user.getAccountNameDescription(), "id", sID));
				
				property = user.getAccountNameLabel().replace(" ", "_");
				session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+user.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
				// inserimeto user in setUser
				setUser.add(user.getId());
				
			}
			else{
				
				property = "has_follower";
				session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
						" AND b.value = "+user.getId()+
						" CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
		}
		
		
		
		
		// CREAZIONE USER IS FOLLOWING

		for(TwitterUserAccount user : twitterUser.getIsFollowing()){
			
			if(!setUser.contains(user.getId())){
			
				// creazione del nodo utente (TwitterUser)
				session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
						Values.parameters("name", user.getAccountName(), "value", user.getId(), "description", "Twitter User Account"));
				
				property = "is_following";
				session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
						" AND b.value = "+user.getId()+
						" CREATE (a)-[r:"+property+"]->(b)");
				
				
				//creazione nodo account name (literal)
				sID = user.getId()+user.getAccountNameLabel().replace(" ", "");
				session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
						Values.parameters("name", user.getAccountNameLabel(), "value", user.getAccountName(),
						"description", user.getAccountNameDescription(), "id", sID));
				
				property = user.getAccountNameLabel().replace(" ", "_");
				session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+user.getId()+
						" AND b.id = '"+sID+
						"' CREATE (a)-[r:"+property+"]->(b)");
				
				// inserimeto user in setUser
				setUser.add(user.getId());
				
			}
			else{
				
				property = "is_following";
				session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
						" AND b.value = "+user.getId()+
						" CREATE (a)-[r:"+property+"]->(b)");
				
			}
			
		}
		
		
		
		// CREAZIONE USER HAS SIMILAR

		for(TwitterUserAccount user : twitterUser.getHasSimilar()){
			
			if(twitterUser.getId() != user.getId()){
				
				if(!setUser.contains(user.getId())){
			
					// creazione del nodo utente (TwitterUser)
					session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
							Values.parameters("name", user.getAccountName(), "value", user.getId(), "description", "Twitter User Account"));
					
					property = "has_similar";
					session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
							" AND b.value = "+user.getId()+
							" CREATE (a)-[r:"+property+"]->(b)");
					
					
					//creazione nodo account name (literal)
					sID = user.getId()+user.getAccountNameLabel().replace(" ", "");
					session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
							Values.parameters("name", user.getAccountNameLabel(), "value", user.getAccountName(),
							"description", user.getAccountNameDescription(), "id", sID));
					
					property = user.getAccountNameLabel().replace(" ", "_");
					session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+user.getId()+
							" AND b.id = '"+sID+
							"' CREATE (a)-[r:"+property+"]->(b)");
					
					// inserimeto user in setUser
					setUser.add(user.getId());
					
				}
				else{
					
					property = "has_similar";
					session.run("MATCH (a:TwitterUser),(b:TwitterUser) WHERE a.value = "+twitterUser.getId()+
							" AND b.value = "+user.getId()+
							" CREATE (a)-[r:"+property+"]->(b)");
					
				}
			
			}
			
		}
		
	}
	
}
