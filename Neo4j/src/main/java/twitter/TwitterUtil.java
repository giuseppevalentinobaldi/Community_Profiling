package twitter;

import java.util.LinkedList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;

public class TwitterUtil {
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

	public void Tweets() throws TwitterException {
		List<Status> statuses = this.twitter.getUserTimeline(769181646176284672L);
		System.out.println("Showing home timeline.");
		for (Status status : statuses) {
			System.out.println(status.getUser().getName() + ":" + status.getText());
		}
	}

	public List<List<String>> getTweetDataItems(long userId) throws TwitterException {
		List<Status> statuses = this.twitter.getUserTimeline(userId);
		List<List<String>> listOfDataItem = new LinkedList<List<String>>();
		for (Status status : statuses) {
			listOfDataItem.add(newItem(status));
		}
		return listOfDataItem;
	}

	public List<String> newItem(Status status){
		String temp;
		boolean isFirst;
		List<String>  item= new LinkedList<String>();
		item.add(status.getText());
		long[] contributors =status.getContributors();
		temp="";
		isFirst=true;
		for (long idContributor : contributors) {
			if(isFirst){
				temp+=idContributor;
				isFirst=false;
			}
			else
				temp+="\t"+idContributor;
		}
		item.add(temp);
		GeoLocation geoLocation=status.getGeoLocation();
		if(geoLocation!=null){	
			item.add(geoLocation.getLongitude()+"");
			item.add(geoLocation.getLatitude()+"");
			item.add(geoLocation.toString());
		}
		else{
			item.add("");
			item.add("");
			item.add("");
		}
		item.add(status.getCreatedAt()+"");
		item.add(status.getCreatedAt()+"");
		HashtagEntity[] hashtagEntities = status.getHashtagEntities();
		temp="";
		isFirst=true;
		for (HashtagEntity hashtagEntity : hashtagEntities) {
			if(isFirst){
				temp+=hashtagEntity.getText();
				isFirst=false;
			}
			else
				temp+="\t"+hashtagEntity.getText();
		}
		item.add(temp);
		URLEntity[] URLEntities = status.getURLEntities();
		temp="";
		isFirst=true;
		for (URLEntity URLEntity : URLEntities) {
			if(isFirst){
				temp+=URLEntity.getText();
				isFirst=false;
			}
			else
				temp+="\t"+URLEntity.getText();
		}
		item.add(temp);
		UserMentionEntity[] userMentionEntities = status.getUserMentionEntities();
		temp="";
		isFirst=true;
		for (UserMentionEntity userMentionEntity : userMentionEntities) {
			if(isFirst){
				temp+=userMentionEntity.getText();
				isFirst=false;
			}
			else
				temp+="\t"+userMentionEntity.getText();
		}
		item.add(temp);
		item.add(status.getFavoriteCount()+"");
		item.add(status.getId()+"");
		item.add(status.getInReplyToScreenName());
		item.add(status.getInReplyToStatusId()+"");
		item.add(status.getInReplyToUserId()+"");
		item.add(status.getLang());
		item.add(status.getRetweetCount()+"");
		item.add(status.getSource());
		item.add(status.getUser().getName());
		item.add(status.getUser().getScreenName());
		item.add(status.getUser().getId()+"");
		return item;
	}

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
