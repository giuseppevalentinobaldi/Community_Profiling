package twitterOntology.twitter;

import java.util.Date;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import twitterOntology.ontology.TwitterOntologyData;
@NodeEntity
public class TweetData extends TwitterOntologyData {
	@GraphId private Long graphId;
	@Property private long id;
	@Property private String message;
	@Property private String contributorsId;
	@Property private String longitude;
	@Property private String latitude;
	@Property private Date timeStamp;
	@Property private String hashTag;
	@Property private String url;
	@Property private String mentionedUserId;
	@Property private int countFavoriteUser;
	@Property private String screenNameRespondedUser;
	@Property private long tweetIdResponded;
	@Property private long userIdResponded;
	@Property private String language;
	@Property private int countRetweet;
	@Property private String source;
	@Property private String coordinates;
	
	
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


	public String getContributorsId() {
		return contributorsId;
	}


	public void setContributorsId(String contributorsId) {
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
