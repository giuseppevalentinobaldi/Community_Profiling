package twitterOntology.twitter;

import java.util.List;
import java.util.Map;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.HashtagEntity;
import twitter4j.RateLimitStatus;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;

public class TwitterUtil {

	final private String token = "769181646176284672-EYL3wIrIl5bx2lSBPtFweSocignMguH";
	final private String tokenSecret = "bbRELLK6X4EvKvfIharcz8I1zXGykLAiJz1X1TGwenuho";
	final private String consumerKey = "N2LZiDdNAqY1qtgJ8EPRoAdx9";
	final private String consumerSecret = "ayLGG7YtnVykMbkfNZ3XyYZRo1FDCC4sIO8VBSJELBOoM6lYHU";

	private Twitter twitter;

	private final int OVERTIME = 30;

	public TwitterUtil() {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(this.getToken(), this.getTokenSecret());
		this.twitter = factory.getInstance();
		this.twitter.setOAuthConsumer(this.getConsumerKey(), this.getConsumerSecret());
		this.twitter.setOAuthAccessToken(accessToken);
	}

	public TwitterUserData getUserData(long userId) throws Exception {
		// controllo # chiamate
		checkRateLimitStatus();
		List<Status> statuses = null;
		// prelievo degli ultimi 20 tweet dell'utente
		try {
			statuses = this.twitter.getUserTimeline(userId);
		} catch (TwitterException e) {
			System.out.println(e);
			System.out.println("This user is private, for add him you must add between your followings");
			return new TwitterUserData(userId);
		}

		if (statuses.isEmpty()) {
			return new TwitterUserData(userId);
		}

		// prelievo del name e dello screen name dell'utente
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
			if (status.getContributors().length > 0) {

				long[] array = status.getContributors();
				String s = "" + array[0];

				int count = 1;
				while (count < array.length) {
					s += ", " + array[count];
					count++;
				}

				tweetData.setContributorsId(s);

			} else {
				tweetData.setContributorsId("");
			}

			// longitude of the location tweet was generated
			if (status.getGeoLocation() != null) {
				tweetData.setLongitude("" + status.getGeoLocation().getLongitude());
			} else {
				tweetData.setLongitude("");
			}

			// latitude of the location tweet was generated
			if (status.getGeoLocation() != null) {
				tweetData.setLatitude("" + status.getGeoLocation().getLatitude());
			} else {
				tweetData.setLatitude("");
			}

			// created timestamp
			tweetData.setTimeStamp(status.getCreatedAt());

			// data set containing hashtags
			if (status.getHashtagEntities() != null) {

				HashtagEntity[] array = status.getHashtagEntities();

				if (array.length > 0) {

					String s = array[0].getText();

					int count = 1;
					while (count < array.length) {
						s += ", " + array[count].getText();
						count++;
					}

					tweetData.setHashTag(s);

				} else {
					tweetData.setHashTag("");
				}

			} else {
				tweetData.setHashTag("");
			}

			// data set containing tweet-relevant urls
			if (status.getURLEntities() != null) {

				URLEntity[] array = status.getURLEntities();

				if (array.length > 0) {

					String s = array[0].getText();

					int count = 1;
					while (count < array.length) {
						s += ", " + array[count].getText();
						count++;
					}

					tweetData.setUrl(s);

				} else {
					tweetData.setUrl("");
				}

			} else {
				tweetData.setUrl("");
			}

			// data set containing mentioned user ids
			if (status.getUserMentionEntities() != null) {

				UserMentionEntity[] array = status.getUserMentionEntities();

				if (array.length > 0) {

					String s = "" + array[0].getId();

					int count = 1;
					while (count < array.length) {
						s += ", " + array[count].getId();
						count++;
					}

					tweetData.setMentionedUserId(s);

				} else {
					tweetData.setMentionedUserId("");
				}

			} else {
				tweetData.setMentionedUserId("");
			}

			// count of users who favorited
			tweetData.setCountFavoriteUser(status.getFavoriteCount());

			// user screen name responded to
			if (status.getInReplyToScreenName() != null) {
				tweetData.setScreenNameRespondedUser(status.getInReplyToScreenName());
			} else {
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
			if (status.getGeoLocation() != null) {
				String c = status.getGeoLocation().toString();
				tweetData.setCoordinates(c.substring(12, c.length() - 1));
			} else {
				tweetData.setCoordinates("");
			}

			// inserimento del TweetData in TwitterUserData
			userData.addTweetData(tweetData);

		}

		return userData;

	}

	private void checkRateLimitStatus() {
		try {
			Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
			for (String endpoint : rateLimitStatus.keySet()) {
				RateLimitStatus status = rateLimitStatus.get(endpoint);
				// System.out.println(" Remaining: " +
				// status.getRemaining()+"\n");
				if (status.getRemaining() <= 0) {
					int remainingTime = status.getSecondsUntilReset() + OVERTIME;
					System.out.println("Twitter request rate limit reached. Waiting " + remainingTime / 60
							+ " minutes to request again.");

					try {
						Thread.sleep(remainingTime * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (TwitterException te) {
			System.err.println(te.getMessage());
			if (te.getStatusCode() == 503) {
				try {
					Thread.sleep(120 * 1000);// wait 2 minutes
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());

		}
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
