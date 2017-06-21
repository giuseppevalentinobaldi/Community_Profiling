package influenceOntology.twitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterUtil {

	final private String token = "769181646176284672-0IC2vOHqXZ22Rxe6inpBCYAecQsZouN";
	final private String tokenSecret = "TWieXfhALOSL2meTuxzdKo9gYtnY6viEGeeASKwwV1aUc";
	final private String consumerKey = "N2LZiDdNAqY1qtgJ8EPRoAdx9";
	final private String consumerSecret = "ayLGG7YtnVykMbkfNZ3XyYZRo1FDCC4sIO8VBSJELBOoM6lYHU";

	private Twitter twitter;
	private Map<Long, TwitterUserAccount> cache;

	public TwitterUtil() {
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(this.getToken(), this.getTokenSecret());
		this.twitter = factory.getInstance();
		this.twitter.setOAuthConsumer(this.getConsumerKey(), this.getConsumerSecret());
		this.twitter.setOAuthAccessToken(accessToken);
		this.cache = new HashMap<Long, TwitterUserAccount>();
	}

	public TwitterUserAccount getUserData(long userId) throws Exception {

		// check user in cache
		if (this.cache.containsKey(new Long(userId)))
			return this.cache.get(new Long(userId));
		// create new use and add in cache
		else {
			// create an user
			TwitterUserAccount newUser = new TwitterUserAccount(userId);
			// add user in cache
			this.cache.put(new Long(userId), newUser);

			// prelievo degli ultimi 20 tweet dell'utente
			List<Status> statuses = this.twitter.getUserTimeline(userId);

			if (statuses.isEmpty()) {
				return new TwitterUserAccount(userId);
			}

			return null;
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

	public Map<Long, TwitterUserAccount> getCache() {
		return cache;
	}

	public void setCache(Map<Long, TwitterUserAccount> cache) {
		this.cache = cache;
	}
}
