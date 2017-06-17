package twitter;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

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
			tweetData.setContributorsId(status.getContributors());
			
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
				tweetData.setHashTag(status.getHashtagEntities().toString());
			}
			else{
				tweetData.setHashTag("");
			}
			
			// data set containing tweet-relevant urls
			if(status.getURLEntities() != null){
				tweetData.setUrl(status.getURLEntities().toString());
			}
			else{
				tweetData.setUrl("");
			}
			
			// data set containing mentioned user ids
			if(status.getUserMentionEntities() != null){
				tweetData.setMentionedUserId(status.getUserMentionEntities().toString());
			}
			else{
				tweetData.setMentionedUserId("");
			}
			
			// count of users who favorited
			tweetData.setCountFavoriteUser(status.getFavoriteCount());
			
			// user screen name responded to
			tweetData.setScreenNameRespondedUser(status.getInReplyToScreenName());
			
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
				tweetData.setCoordinates(status.getGeoLocation().toString());
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
