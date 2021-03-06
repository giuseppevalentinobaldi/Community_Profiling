package twitterOntology.ontology;

public abstract class TwitterOntologyData {

	private final String idLabel = "tweet id";
	private final String idDescription = "A centrally registered identifier that denotes a tweet data item.";

	private final String messageLabel = "tweet message";
	private final String messageDescription = "The textual message generated by the person or computer system that was shared via a tweet data item.";

	private final String contributorsIdLabel = "data set containing contributors ids";
	private final String contributorsIdDescription = "A data set containing a list of identifiers that uniquely identify Twitter user accounts that contributed in some way to a tweet data item.";

	private final String longitudeLabel = "longitude of the location tweet was generated";
	private final String longitudeDescription = "A measurement of the longitude by some geopositioning device of the location where the tweet data item was generated.";

	private final String latitudeLabel = "latitude of the location tweet was generated";
	private final String latitudeDescription = "A measurement of the latitude by some geopositioning device of the location where the tweet data item was generated.";

	private final String timeStampLabel = "created timestamp";
	private final String timeStampDescription = "A timestamp which denotes the precise time during which a tweet data item was created.";

	private final String hashTagLabel = "data set containing hashtags";
	private final String hashTagDescription = "A data set containing a list of strings which a users uses to denote the topic(s) of a tweet message.";

	private final String urlLabel = "data set containing tweet-relevant urls";
	private final String urlDescription = "A data set containing a list of URLs that are relevant to the tweet data item by some metric.";

	private final String mentionedUserIdLabel = "data set containing mentioned user ids";
	private final String mentionedUserIdDescription = "A data set containing a list of identifiers which denote a user account which has been mentioned directly in the tweet message.";

	private final String countFavoriteUserLabel = "count of users who favorited";
	private final String countFavoriteUserDescription = "";

	private final String screenNameRespondedUserLabel = "user screen name responded to";
	private final String screenNameRespondedUserDescription = "An identifier that, at some point in time, uniquely identifies a Twitter user account.";

	private final String tweetIdRespondedLabel = "tweet id responded to";
	private final String tweetIdRespondedDescription = "A identifier that denotes a tweet data item that the associated tweet data item was created in response to.";

	private final String userIdRespondedLabel = "user id responded to";
	private final String userIdRespondedDescription = "An identifier that denotes a user which is the intended recepient of the associated tweet message.";

	private final String languageLabel = "tweet language";
	private final String languageDescription = "A data item that is about the language that a tweet message is written in.";

	private final String countRetweetLabel = "count of retweets";
	private final String countRetweetDescription = "A count of the number of times the associated tweet message has been retweeted.";

	private final String sourceLabel = "data item about the application used to generate tweet";
	private final String sourceDescription = "A possibly non-unique identifier that denotes the software application used to generate a tweet data item.";

	private final String coordinatesLabel = "coordinates";
	private final String coordinatesDescription = "Represents the geographic location of this Tweet as reported by the user or client application. Has two primary parts: a lattitude and a longitude";
	
	//data ontology 

	public String getIdLabel() {
		return idLabel;
	}

	public String getIdDescription() {
		return idDescription;
	}

	public String getMessageLabel() {
		return messageLabel;
	}

	public String getMessageDescription() {
		return messageDescription;
	}

	public String getContributorsIdLabel() {
		return contributorsIdLabel;
	}

	public String getContributorsIdDescription() {
		return contributorsIdDescription;
	}

	public String getLongitudeLabel() {
		return longitudeLabel;
	}

	public String getLongitudeDescription() {
		return longitudeDescription;
	}

	public String getLatitudeLabel() {
		return latitudeLabel;
	}

	public String getLatitudeDescription() {
		return latitudeDescription;
	}

	public String getTimeStampLabel() {
		return timeStampLabel;
	}

	public String getTimeStampDescription() {
		return timeStampDescription;
	}

	public String getHashTagLabel() {
		return hashTagLabel;
	}

	public String getHashTagDescription() {
		return hashTagDescription;
	}

	public String getUrlLabel() {
		return urlLabel;
	}

	public String getUrlDescription() {
		return urlDescription;
	}

	public String getMentionedUserIdLabel() {
		return mentionedUserIdLabel;
	}

	public String getMentionedUserIdDescription() {
		return mentionedUserIdDescription;
	}

	public String getCountFavoriteUserLabel() {
		return countFavoriteUserLabel;
	}

	public String getCountFavoriteUserDescription() {
		return countFavoriteUserDescription;
	}

	public String getScreenNameRespondedUserLabel() {
		return screenNameRespondedUserLabel;
	}

	public String getScreenNameRespondedUserDescription() {
		return screenNameRespondedUserDescription;
	}

	public String getTweetIdRespondedLabel() {
		return tweetIdRespondedLabel;
	}

	public String getTweetIdRespondedDescription() {
		return tweetIdRespondedDescription;
	}

	public String getUserIdRespondedLabel() {
		return userIdRespondedLabel;
	}

	public String getUserIdRespondedDescription() {
		return userIdRespondedDescription;
	}

	public String getLanguageLabel() {
		return languageLabel;
	}

	public String getLanguageDescription() {
		return languageDescription;
	}

	public String getCountRetweetLabel() {
		return countRetweetLabel;
	}

	public String getCountRetweetDescription() {
		return countRetweetDescription;
	}

	public String getSourceLabel() {
		return sourceLabel;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	public String getCoordinatesLabel() {
		return coordinatesLabel;
	}

	public String getCoordinatesDescription() {
		return coordinatesDescription;
	}

}
