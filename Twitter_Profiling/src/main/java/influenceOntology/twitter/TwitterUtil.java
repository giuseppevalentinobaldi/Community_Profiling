package influenceOntology.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.HashtagEntity;
import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.Twitter;
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
	private Map<Long, Structure> cache;

	public TwitterUtil() {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(this.getToken(), this.getTokenSecret());
		this.twitter = factory.getInstance();
		this.twitter.setOAuthConsumer(this.getConsumerKey(), this.getConsumerSecret());
		this.twitter.setOAuthAccessToken(accessToken);
		this.cache = new HashMap<Long, Structure>();
	}

	public TwitterUserAccount getTwitterUserAccount(long userId) throws Exception {
		TwitterUserAccount newUser;

		// check user in cache
		if (this.cache.containsKey(new Long(userId)))
			if(this.cache.get(new Long(userId)).isComplete())
				newUser = this.cache.get(new Long(userId)).getTua();
			else{
				newUser = this.cache.get(new Long(userId)).getTua();
			}
		// create new user and add in cache
		else {
			// create an user complete
			newUser = new TwitterUserAccount(userId);
			// add user in cache
			this.cache.put(new Long(userId), new Structure(newUser,true));
			// set general information
			newUser.setGi(this.setGeneralInformation(userId));
			// set quality metrics
			newUser.setQm(this.setQualityMetrics(userId));

			// initialize private statements
			newUser.setHashtag(new ArrayList<Hashtag>());
			newUser.setUrl(new ArrayList<URL>());
			newUser.setMentions(new ArrayList<TwitterUserAccount>());
			newUser.setReplyTo(new ArrayList<TwitterUserAccount>());
			newUser.setHasFollower(new ArrayList<TwitterUserAccount>());
			newUser.setIsFollowing(new ArrayList<TwitterUserAccount>());
			// newUser.setHasSimilar(new ArrayList<TwitterUserAccount>());
			
			IDs isFollowing=this.twitter.getFollowersIDs(-1);
			while(isFollowing.hasNext()){
				newUser.getIsFollowing().add(this.getUser(isFollowing.getNextCursor()));
			}
			
			IDs hasFollower=this.twitter.getFriendsIDs(-1);
			while(hasFollower.hasNext()){
				newUser.getIsFollowing().add(this.getUser(hasFollower.getNextCursor()));
			}
			
			
			// takes the last 20 tweets from the user
			List<Status> statuses = this.twitter.getUserTimeline(userId);

			for (Status status : statuses) {

				// add hashtag if present
				if (status.getHashtagEntities() != null) {
					HashtagEntity[] array = status.getHashtagEntities();

					int count = 0;
					while (count < array.length) {
						newUser.getHashtag().add(new Hashtag(array[count].getText()));
						count++;
					}
				}

				// add url if present
				if (status.getURLEntities() != null) {
					URLEntity[] array = status.getURLEntities();

					int count = 0;
					while (count < array.length) {
						newUser.getUrl().add(new URL(array[count].getText()));
						count++;
					}
				}

				// data set containing mentioned user ids
				if (status.getUserMentionEntities() != null) {
					UserMentionEntity[] array = status.getUserMentionEntities();

					int count = 0;
					while (count < array.length) {
						newUser.getMentions().add(this.getUser(array[count].getId()));
						count++;
					}
				}
				
				// data set containing mentioned user ids
				if (status.getUserMentionEntities() != null) {
					UserMentionEntity[] array = status.g;

					int count = 0;
					while (count < array.length) {
						newUser.getMentions().add(this.getUser(array[count].getId()));
						count++;
					}
				}
			}
		}
		return newUser;
	}

	public TwitterUserAccount getUser(long id){
		return null;
	}

	public GeneralInformation setGeneralInformation(long userId) {
		return null;
	}

	public QualityMetrics setQualityMetrics(long userId) {
		return null;
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
