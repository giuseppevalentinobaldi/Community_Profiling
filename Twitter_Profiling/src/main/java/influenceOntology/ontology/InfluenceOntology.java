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

}
