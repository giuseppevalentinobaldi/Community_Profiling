package twitter;

import java.util.Date;

import ontology.TwitterOntologyData;

public class TweetData extends TwitterOntologyData {

	private long id;
	private String message;
	private long[] contributorsId;
	private String longitude;
	private String latitude;
	private Date timeStamp;
	private String hashTag;
	private String url;
	private String mentionedUserId;
	private int countFavoriteUser;
	private String screenNameRespondedUser;
	private long tweetIdResponded;
	private long userIdResponded;
	private String language;
	private int countRetweet;
	private String source;
	private String coordinates;
	
	
	public TweetData(){
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public long[] getContributorsId() {
		return contributorsId;
	}


	public void setContributorsId(long[] contributorsId) {
		this.contributorsId = contributorsId;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getHashTag() {
		return hashTag;
	}


	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getMentionedUserId() {
		return mentionedUserId;
	}


	public void setMentionedUserId(String mentionedUserId) {
		this.mentionedUserId = mentionedUserId;
	}


	public int getCountFavoriteUser() {
		return countFavoriteUser;
	}


	public void setCountFavoriteUser(int countFavoriteUser) {
		this.countFavoriteUser = countFavoriteUser;
	}


	public String getScreenNameRespondedUser() {
		return screenNameRespondedUser;
	}


	public void setScreenNameRespondedUser(String screenNameRespondedUser) {
		this.screenNameRespondedUser = screenNameRespondedUser;
	}


	public long getTweetIdResponded() {
		return tweetIdResponded;
	}


	public void setTweetIdResponded(long tweetIdResponded) {
		this.tweetIdResponded = tweetIdResponded;
	}


	public long getUserIdResponded() {
		return userIdResponded;
	}


	public void setUserIdResponded(long userIdResponded) {
		this.userIdResponded = userIdResponded;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public int getCountRetweet() {
		return countRetweet;
	}


	public void setCountRetweet(int countRetweet) {
		this.countRetweet = countRetweet;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getCoordinates() {
		return coordinates;
	}


	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
}
