package twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterUtil {
	final private String token = "769181646176284672-0IC2vOHqXZ22Rxe6inpBCYAecQsZouN";
	final private String tokenSecret = "TWieXfhALOSL2meTuxzdKo9gYtnY6viEGeeASKwwV1aUc";
	final private String consumerKey = "N2LZiDdNAqY1qtgJ8EPRoAdx9";
	final private String consumerSecret = "ayLGG7YtnVykMbkfNZ3XyYZRo1FDCC4sIO8VBSJELBOoM6lYHU";
	
	private Twitter twitter;
	
	public TwitterUtil(){
		TwitterFactory factory = new TwitterFactory();
		AccessToken accessToken = new AccessToken(this.getToken(), this.getTokenSecret());
		Twitter twitter = factory.getInstance();
		twitter.setOAuthConsumer(this.getConsumerKey(),this.getConsumerSecret());
		twitter.setOAuthAccessToken(accessToken);
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
