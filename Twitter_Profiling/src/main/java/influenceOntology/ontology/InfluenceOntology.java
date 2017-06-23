package influenceOntology.ontology;

public abstract class InfluenceOntology {
	// General Information
	final private String displayNameLabel = "display name";
	final private String displayNameDescription = "Provides the name displayed at the web page of the account, as set by its owner.";
	final private String descriptionLabel = "description";
	final private String descriptionDescription = "Provides the description of the account, as set by its owner.";
	final private String numberTweetLabel = "number of tweets";
	final private String numberTweetDescription = "Indicates the number of the total tweets posted by the account.";
	final private String tweetForDayLabel = "tweets per day";
	final private String tweetForDayDescription = "Indicates the number of the average tweets posted per day by the account.";
	final private String followerLabel = "followers";
	final private String followerDescription = "Provides the number of the followers of the account.";
	final private String followingLabel = "following";
	final private String followingDescription = "Provides the number of the accounts that the account follows.";
	final private String retweetPercentageLabel = "retweet percentage";
	final private String retweetPercentageDescription = "Provides the percentage of the latest user’s tweets that are retweets from other accounts.";
	final private String activeAccountLabel = "active account";
	final private String activeAccountDescription = "Indicates whether the account is active or not.";
	final private String profileLockedLabel = "profile locked";
	final private String profileLockedDescription = "Indicates whether the profile of the account is publicly visible or not.";
	final private String retrivedOnLabel = "retrieved on";
	final private String retrivedOnDescription = "Indicates the date that the information regarding the account was lastly updated.";

	// Quality Metrics
	final private String hIndexRetweetLabel = "h-index retweet";
	final private String hIndexRetweetDescription = "Provides the value of the “ReTweet h-index - Last 100 Tweets” metric of the account.";
	final private String hIndexRetweetDailyLabel = "h-index retweet daily";
	final private String hIndexRetweetDailyDescription = "Provides the estimated daily value of the “ReTweet h-index” metric during the lifespan of the account.";
	final private String hIndexFavoriteLabel = "h-index favorite";
	final private String hIndexFavoriteDescription = "Provides the value of the “Favorite h-index - Last 100 Tweets” metric of the account.";
	final private String hIndexFavoriteDailyLabel = "h-index favorite daily";
	final private String hIndexFavoriteDailyDescription = "Provides the estimated daily value of the “Favorite h-index” metric during the lifespan of the account.";
	final private String replayRatioLabel = "reply ratio";
	final private String replayRatioDescription = "Provides the ratio of the user's latest tweets which are used as replies to other users' tweets.";
	final private String influenceMetricLabel = "influence metric";
	final private String influenceMetricDescription = "Indicates the value of the Influence Metric measurement. Its aim is to describe both the importance and impact of an account in a social network.";

	public String getDisplayNameLabel() {
		return displayNameLabel;
	}

	public String getDisplayNameDescription() {
		return displayNameDescription;
	}

	public String getDescriptionLabel() {
		return descriptionLabel;
	}

	public String getDescriptionDescription() {
		return descriptionDescription;
	}

	public String getNumberTweetLabel() {
		return numberTweetLabel;
	}

	public String getNumberTweetDescription() {
		return numberTweetDescription;
	}

	public String getTweetForDayLabel() {
		return tweetForDayLabel;
	}

	public String getTweetForDayDescription() {
		return tweetForDayDescription;
	}

	public String getFollowerLabel() {
		return followerLabel;
	}

	public String getFollowerDescription() {
		return followerDescription;
	}

	public String getFollowingLabel() {
		return followingLabel;
	}

	public String getFollowingDescription() {
		return followingDescription;
	}

	public String getRetweetPercentageLabel() {
		return retweetPercentageLabel;
	}

	public String getRetweetPercentageDescription() {
		return retweetPercentageDescription;
	}

	public String getActiveAccountLabel() {
		return activeAccountLabel;
	}

	public String getActiveAccountDescription() {
		return activeAccountDescription;
	}

	public String getProfileLockedLabel() {
		return profileLockedLabel;
	}

	public String getProfileLockedDescription() {
		return profileLockedDescription;
	}

	public String getRetrivedOnLabel() {
		return retrivedOnLabel;
	}

	public String getRetrivedOnDescription() {
		return retrivedOnDescription;
	}

	public String gethIndexRetweetLabel() {
		return hIndexRetweetLabel;
	}

	public String gethIndexRetweetDescription() {
		return hIndexRetweetDescription;
	}

	public String gethIndexRetweetDailyLabel() {
		return hIndexRetweetDailyLabel;
	}

	public String gethIndexRetweetDailyDescription() {
		return hIndexRetweetDailyDescription;
	}

	public String gethIndexFavoriteLabel() {
		return hIndexFavoriteLabel;
	}

	public String gethIndexFavoriteDescription() {
		return hIndexFavoriteDescription;
	}

	public String gethIndexFavoriteDailyLabel() {
		return hIndexFavoriteDailyLabel;
	}

	public String gethIndexFavoriteDailyDescription() {
		return hIndexFavoriteDailyDescription;
	}

	public String getReplayRatioLabel() {
		return replayRatioLabel;
	}

	public String getReplayRatioDescription() {
		return replayRatioDescription;
	}

	public String getInfluenceMetricLabel() {
		return influenceMetricLabel;
	}

	public String getInfluenceMetricDescription() {
		return influenceMetricDescription;
	}

}
