package influenceOntology.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.HashtagEntity;
import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import twitterOntology.twitter.TwitterUserData;

public class TwitterUtil {

	final private String token = "769181646176284672-0IC2vOHqXZ22Rxe6inpBCYAecQsZouN";
	final private String tokenSecret = "TWieXfhALOSL2meTuxzdKo9gYtnY6viEGeeASKwwV1aUc";
	final private String consumerKey = "N2LZiDdNAqY1qtgJ8EPRoAdx9";
	final private String consumerSecret = "ayLGG7YtnVykMbkfNZ3XyYZRo1FDCC4sIO8VBSJELBOoM6lYHU";

	private Twitter twitter;

	private Map<Long, Structure> cache_1;
	private Map<String, Hashtag> cache_2;
	private Map<String, URL> cache_3;

	public TwitterUtil() {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(this.getToken(), this.getTokenSecret());
		this.twitter = factory.getInstance();
		this.twitter.setOAuthConsumer(this.getConsumerKey(), this.getConsumerSecret());
		this.twitter.setOAuthAccessToken(accessToken);
		this.cache_1 = new HashMap<Long, Structure>();
		this.cache_2 = new HashMap<String, Hashtag>();
		this.cache_3 = new HashMap<String, URL>();
	}

	public TwitterUserAccount getTwitterUserAccount(long userId) throws Exception {
		TwitterUserAccount newUser;

		// check user in cache
		if (this.cache_1.containsKey(new Long(userId)))
			if (this.cache_1.get(new Long(userId)).isComplete())
				newUser = this.cache_1.get(new Long(userId)).getTua();
			else {
				newUser = this.cache_1.get(new Long(userId)).getTua();
				this.cache_1.get(new Long(userId)).setComplete(true);
				// initialize private statements
				newUser.setMentions(new ArrayList<TwitterUserAccount>());
				newUser.setReplyTo(new ArrayList<TwitterUserAccount>());
				newUser.setHasFollower(new ArrayList<TwitterUserAccount>());
				newUser.setIsFollowing(new ArrayList<TwitterUserAccount>());

				IDs isFollowing = this.twitter.getFriendsIDs(userId, -1);
				long[] idsIsFollowing = isFollowing.getIDs();
				for (long id : idsIsFollowing) {
					newUser.getIsFollowing().add(this.getUser(id));
				}

				IDs hasFollower = this.twitter.getFollowersIDs(userId, -1);
				long[] idsHasFollower = hasFollower.getIDs();
				for (long id : idsHasFollower) {
					newUser.getHasFollower().add(this.getUser(id));
				}

				// takes the last 20 tweets from the user
				List<Status> statuses = this.twitter.getUserTimeline(userId);

				for (Status status : statuses) {

					// data set containing mentioned user ids
					if (status.getUserMentionEntities() != null) {
						UserMentionEntity[] array = status.getUserMentionEntities();

						int count = 0;
						while (count < array.length) {
							newUser.getMentions().add(this.getUser(array[count].getId()));
							count++;
						}
					}

					long replyToUserId = status.getInReplyToUserId();
					if (replyToUserId != -1)
						newUser.getReplyTo().add(this.getUser(replyToUserId));
				}
			}
		// create new user and add in cache
		else {
			// create an user complete
			newUser = new TwitterUserAccount(userId);
			// add user in cache
			this.cache_1.put(new Long(userId), new Structure(newUser, true));

			// initialize private statements
			newUser.setHashtag(new ArrayList<Hashtag>());
			newUser.setUrl(new ArrayList<URL>());
			newUser.setMentions(new ArrayList<TwitterUserAccount>());
			newUser.setReplyTo(new ArrayList<TwitterUserAccount>());
			newUser.setHasFollower(new ArrayList<TwitterUserAccount>());
			newUser.setIsFollowing(new ArrayList<TwitterUserAccount>());
			// newUser.setHasSimilar(new ArrayList<TwitterUserAccount>());

			IDs isFollowing = this.twitter.getFriendsIDs(userId, -1);
			long[] idsIsFollowing = isFollowing.getIDs();
			for (long id : idsIsFollowing) {
				newUser.getIsFollowing().add(this.getUser(id));
			}

			IDs hasFollower = this.twitter.getFollowersIDs(userId, -1);
			long[] idsHasFollower = hasFollower.getIDs();
			for (long id : idsHasFollower) {
				newUser.getHasFollower().add(this.getUser(id));
			}

			// takes the last 20 tweets from the user
			List<Status> statuses = this.twitter.getUserTimeline(userId);
			if (statuses.isEmpty()) {
				return newUser;
			}
			// set general information
			newUser.setGi(this.setGeneralInformation(statuses.get(0)));

			// set quality metrics
			newUser.setQm(this.setQualityMetrics(statuses));

			for (Status status : statuses) {

				// add hashtag if present
				if (status.getHashtagEntities() != null) {
					HashtagEntity[] array = status.getHashtagEntities();

					int count = 0;
					while (count < array.length) {
						if (this.cache_2.containsKey(array[count].getText()))
							newUser.getHashtag().add(this.cache_2.get(array[count].getText()));
						else {
							this.cache_2.put(array[count].getText(), new Hashtag(array[count].getText()));
							newUser.getHashtag().add(new Hashtag(array[count].getText()));
						}
						count++;
					}
				}

				// add url if present
				if (status.getURLEntities() != null) {
					URLEntity[] array = status.getURLEntities();

					int count = 0;
					while (count < array.length) {
						if (this.cache_3.containsKey(array[count].getText()))
							newUser.getUrl().add(this.cache_3.get(array[count].getText()));
						else {
							this.cache_3.put(array[count].getText(),
									new URL(array[count].getURL(), array[count].getExpandedURL()));
							newUser.getUrl().add(new URL(array[count].getURL(), array[count].getExpandedURL()));
						}
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

				long replyToUserId = status.getInReplyToUserId();
				if (replyToUserId != -1)
					newUser.getReplyTo().add(this.getUser(replyToUserId));
			}
		}
		return newUser;
	}

	public TwitterUserAccount getUser(long userId) throws TwitterException {
		TwitterUserAccount newUser;

		// check user in cache
		if (this.cache_1.containsKey(new Long(userId)))
			newUser = this.cache_1.get(new Long(userId)).getTua();
		else {
			newUser = new TwitterUserAccount(userId);
			// add user in cache
			this.cache_1.put(new Long(userId), new Structure(newUser, false));

			// initialize private statements
			newUser.setHashtag(new ArrayList<Hashtag>());
			newUser.setUrl(new ArrayList<URL>());

			// takes the last 20 tweets from the user
			List<Status> statuses = this.twitter.getUserTimeline(userId);
			if (statuses.isEmpty()) {
				return newUser;
			}
			// set general information
			newUser.setGi(this.setGeneralInformation(statuses.get(0)));

			// set quality metrics
			newUser.setQm(this.setQualityMetrics(statuses));

			for (Status status : statuses) {

				// add hashtag if present
				if (status.getHashtagEntities() != null) {
					HashtagEntity[] array = status.getHashtagEntities();

					int count = 0;
					while (count < array.length) {
						if (this.cache_2.containsKey(array[count].getText()))
							newUser.getHashtag().add(this.cache_2.get(array[count].getText()));
						else {
							this.cache_2.put(array[count].getText(), new Hashtag(array[count].getText()));
							newUser.getHashtag().add(new Hashtag(array[count].getText()));
						}
						count++;
					}
				}

				// add url if present
				if (status.getURLEntities() != null) {
					URLEntity[] array = status.getURLEntities();

					int count = 0;
					while (count < array.length) {
						if (this.cache_3.containsKey(array[count].getText()))
							newUser.getUrl().add(this.cache_3.get(array[count].getText()));
						else {
							this.cache_3.put(array[count].getText(),
									new URL(array[count].getURL(), array[count].getExpandedURL()));
							newUser.getUrl().add(new URL(array[count].getURL(), array[count].getExpandedURL()));
						}
						count++;
					}
				}
			}
		}

		return newUser;
	}

	public GeneralInformation setGeneralInformation(Status status) {
		twitter4j.User user = status.getUser();
		int following = user.getFollowersCount();
		int followers = user.getFriendsCount();
		String description = user.getDescription();
		String displayName = user.getScreenName();
		float daysUser = (System.currentTimeMillis() - user.getCreatedAt().getTime()) / (1000 * 3600 * 24);
		if (daysUser == 0) {
			daysUser = 1;
		}
		float tweetsPerDay = user.getStatusesCount() / daysUser; // tweet totali
																	// su giorni
																	// totali
		boolean activeAccount;
		float daysStatus = (System.currentTimeMillis() - status.getCreatedAt().getTime()) / (1000 * 3600 * 24);
		if (daysStatus > 365)
			activeAccount = false;
		else
			activeAccount = true;

		return new GeneralInformation(description, followers, displayName, following, tweetsPerDay, activeAccount);
	}

	public QualityMetric setQualityMetrics(List<Status> statuses) {
		QualityMetric qm = new QualityMetric();
		qm.calculationUserQualityMetric(statuses);
		return qm;
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
