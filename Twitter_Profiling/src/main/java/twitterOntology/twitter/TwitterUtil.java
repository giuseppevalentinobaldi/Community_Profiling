package twitterOntology.twitter;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.HashtagEntity;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;

public class TwitterUtil{
	
	final private String token = "769181646176284672-0IC2vOHqXZ22Rxe6inpBCYAecQsZouN";
	final private String tokenSecret = "TWieXfhALOSL2meTuxzdKo9gYtnY6viEGeeASKwwV1aUc";
	final private String consumerKey = "N2LZiDdNAqY1qtgJ8EPRoAdx9";
	final private String consumerSecret = "ayLGG7YtnVykMbkfNZ3XyYZRo1FDCC4sIO8VBSJELBOoM6lYHU";

	private Twitter twitter;

	public TwitterUtil() {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(this.getToken(), this.getTokenSecret());
		this.twitter = factory.getInstance();
		this.twitter.setOAuthConsumer(this.getConsumerKey(), this.getConsumerSecret());
		this.twitter.setOAuthAccessToken(accessToken);
	}
	
	public TwitterUserData getUserData(long userId) throws Exception {
		
		// prelievo degli ultimi 20 tweet dell'utente
		List<Status> statuses = this.twitter.getUserTimeline(userId);
		
		if(statuses.isEmpty()){
			return new TwitterUserData(userId);
		}
		
		// prelievo  del name e dello screen name dell'utente
		String userName = statuses.get(0).getUser().getName();
		String userScreenName = statuses.get(0).getUser().getScreenName();
		
		// creazione dell'oggetto TwitterUserData
		TwitterUserData userData = new TwitterUserData(userId, userName, userScreenName);
		
		// estrazione dei dati da ogni tweet ed inserimento in TwitterUserData
		TweetData tweetData;
		
		for (Status status : statuses) {
			
			tweetData = new TweetData();
			
			// tweet id
			tweetData.setId(status.getId());
			
			// tweet message
			tweetData.setMessage(status.getText());
			
			// data set containing contributors ids
			if(status.getContributors().length > 0){
				
				long[] array = status.getContributors();
				String s = ""+array[0];
				
				int count = 1;
				while(count < array.length){
					s += ", "+array[count];
					count++;
				}
				
				tweetData.setContributorsId(s);
				
			}
			else{
				tweetData.setContributorsId("");
			}
			
			// longitude of the location tweet was generated
			if(status.getGeoLocation() != null){
				tweetData.setLongitude(""+status.getGeoLocation().getLongitude());
			}
			else{
				tweetData.setLongitude("");
			}
			
			// latitude of the location tweet was generated
			if(status.getGeoLocation() != null){
				tweetData.setLatitude(""+status.getGeoLocation().getLatitude());
			}
			else{
				tweetData.setLatitude("");
			}
			
			// created timestamp
			tweetData.setTimeStamp(status.getCreatedAt());
			
			// data set containing hashtags
			if(status.getHashtagEntities() != null){
				
				HashtagEntity[] array = status.getHashtagEntities();
				
				if(array.length > 0){
					
					String s = array[0].getText();
					
					int count = 1;
					while(count < array.length){
						s += ", "+array[count].getText();
						count++;
					}
					
					tweetData.setHashTag(s);
					
				}
				else{
					tweetData.setHashTag("");
				}
				
			}
			else{
				tweetData.setHashTag("");
			}
			
			// data set containing tweet-relevant urls
			if(status.getURLEntities() != null){
				
				URLEntity[] array = status.getURLEntities();
				
				if(array.length > 0){
					
					String s = array[0].getText();
					
					int count = 1;
					while(count < array.length){
						s += ", "+array[count].getText();
						count++;
					}
					
					tweetData.setUrl(s);
					
				}
				else{
					tweetData.setUrl("");
				}
				
			}
			else{
				tweetData.setUrl("");
			}
			
			// data set containing mentioned user ids
			if(status.getUserMentionEntities() != null){
				
				UserMentionEntity[] array = status.getUserMentionEntities();
				
				if(array.length > 0){
					
					String s = array[0].getText();
					
					int count = 1;
					while(count < array.length){
						s += ", "+array[count].getText();
						count++;
					}
					
					tweetData.setMentionedUserId(s);
					
				}
				else{
					tweetData.setMentionedUserId("");
				}
				
			}
			else{
				tweetData.setMentionedUserId("");
			}
			
			// count of users who favorited
			tweetData.setCountFavoriteUser(status.getFavoriteCount());
			
			// user screen name responded to
			if(status.getInReplyToScreenName() != null){
				tweetData.setScreenNameRespondedUser(status.getInReplyToScreenName());
			}
			else{
				tweetData.setScreenNameRespondedUser("");
			}
			
			// tweet id responded to
			tweetData.setTweetIdResponded(status.getInReplyToStatusId());
			
			// user id responded to
			tweetData.setUserIdResponded(status.getInReplyToUserId());
			
			// tweet language
			tweetData.setLanguage(status.getLang());
			
			// count of retweets
			tweetData.setCountRetweet(status.getRetweetCount());
			
			// data item about the application used to generate tweet
			tweetData.setSource(status.getSource());
			
			// coordinates
			if(status.getGeoLocation() != null){
				String c = status.getGeoLocation().toString();
				tweetData.setCoordinates(c.substring(12, c.length()-1));
			}
			else{
				tweetData.setCoordinates("");
			}
			
			// inserimento del TweetData in TwitterUserData
			userData.addTweetData(tweetData);
			
		}
		
		return userData;
		
	}

//	public void Tweets() throws TwitterException {
//		List<Status> statuses = this.twitter.getUserTimeline(769181646176284672L);
//		System.out.println("Showing home timeline.");
//		for (Status status : statuses) {
//			System.out.println(status.getUser().getName() + ":" + status.getText());
//		}
//	}
//
//	public List<List<String>> getTweetDataItems(long userId) throws TwitterException {
//		List<Status> statuses = this.twitter.getUserTimeline(userId);
//		List<List<String>> listOfDataItem = new LinkedList<List<String>>();
//		for (Status status : statuses) {
//			listOfDataItem.add(newItem(status));
//		}
//		return listOfDataItem;
//	}
//
//	public List<String> newItem(Status status){
//		String temp;
//		boolean isFirst;
//		List<String>  item= new LinkedList<String>();
//		item.add(status.getId()+"");
//		item.add(status.getText());
//		long[] contributors =status.getContributors();
//		temp="";
//		isFirst=true;
//		for (long idContributor : contributors) {
//			if(isFirst){
//				temp+=idContributor;
//				isFirst=false;
//			}
//			else
//				temp+="\t"+idContributor;
//		}
//		item.add(temp);
//		GeoLocation geoLocation=status.getGeoLocation();
//		if(geoLocation!=null){	
//			item.add(geoLocation.getLongitude()+"");
//			item.add(geoLocation.getLatitude()+"");
//			item.add(geoLocation.toString());
//		}
//		else{
//			item.add("");
//			item.add("");
//			item.add("");
//		}
//		item.add(status.getCreatedAt()+"");
//		HashtagEntity[] hashtagEntities = status.getHashtagEntities();
//		temp="";
//		isFirst=true;
//		for (HashtagEntity hashtagEntity : hashtagEntities) {
//			if(isFirst){
//				temp+=hashtagEntity.getText();
//				isFirst=false;
//			}
//			else
//				temp+="\t"+hashtagEntity.getText();
//		}
//		item.add(temp);
//		URLEntity[] URLEntities = status.getURLEntities();
//		temp="";
//		isFirst=true;
//		for (URLEntity URLEntity : URLEntities) {
//			if(isFirst){
//				temp+=URLEntity.getText();
//				isFirst=false;
//			}
//			else
//				temp+="\t"+URLEntity.getText();
//		}
//		item.add(temp);
//		UserMentionEntity[] userMentionEntities = status.getUserMentionEntities();
//		temp="";
//		isFirst=true;
//		for (UserMentionEntity userMentionEntity : userMentionEntities) {
//			if(isFirst){
//				temp+=userMentionEntity.getText();
//				isFirst=false;
//			}
//			else
//				temp+="\t"+userMentionEntity.getText();
//		}
//		item.add(temp);
//		item.add(status.getFavoriteCount()+"");
//		item.add(status.getInReplyToScreenName());
//		item.add(status.getInReplyToStatusId()+"");
//		item.add(status.getInReplyToUserId()+"");
//		item.add(status.getLang());
//		item.add(status.getRetweetCount()+"");
//		item.add(status.getSource());
//		item.add(status.getUser().getName());
//		item.add(status.getUser().getScreenName());
//		item.add(status.getUser().getId()+"");
//		return item;
//	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public String getToken() {
		return token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

}
