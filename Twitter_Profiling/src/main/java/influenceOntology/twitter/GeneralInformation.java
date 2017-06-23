package influenceOntology.twitter;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

import twitter4j.Status;

@NodeEntity
public class GeneralInformation {
	@GraphId
	private Long graphId;
	@Property
	private String displayName;
	@Property
	private String description;
	@Property
	private int numberTweet;
	@Property
	private float tweetForDay;
	@Property
	private int follower;
	@Property
	private int following;
	@Property
	private float retweetPercentage;
	@Property
	private boolean activeAccount; // ?
	@Property
	private boolean profileLocked;
	@Property
	private String retrivedOn; // ?
	
	public GeneralInformation(){
		
		this.displayName = "";
		this.description = "";
		this.numberTweet = 0;
		this.tweetForDay = 0;
		this.follower = 0;
		this.following = 0;
		this.retweetPercentage = 0;
		this.activeAccount = false;
		this.profileLocked = false;
		this.retrivedOn = "";
		
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDescription() {
		return description;
	}

	public int getNumberTweet() {
		return numberTweet;
	}

	public float getTweetForDay() {
		return tweetForDay;
	}

	public int getFollower() {
		return follower;
	}

	public int getFollowing() {
		return following;
	}

	public float getRetweetPercentage() {
		return retweetPercentage;
	}

	public boolean isActiveAccount() {
		return activeAccount;
	}

	public boolean isProfileLocked() {
		return profileLocked;
	}

	public String getRetrivedOn() {
		return retrivedOn;
	}
	
	private static float fromMillisecondsToDay(long time){
		return time/(1000*3600*24);
	}
	
	public void userGeneralInformation(List<Status> statuses){
		
		if(statuses == null || statuses.isEmpty()){
			return;
		}
		
		Status status = statuses.get(0);
		
		long dateOldestTweet = Long.MAX_VALUE;
		long dateLastTweet = Long.MIN_VALUE;
		Date lastUpdate = null;
		int retweetCount = 0;
		long data;
		for(Status s : statuses){
			
			// retweet
			if(s.getRetweetCount() > 0){
				retweetCount++;
			}
			
			data = s.getCreatedAt().getTime();
			
			// date oldest tweet
			if(dateOldestTweet > data){
				dateOldestTweet = data;
			}
			
			// date last tweet
			if(dateLastTweet < data){
				dateLastTweet = data;
				lastUpdate = s.getCreatedAt();
			}
			
		}
		
		// display name
		this.displayName = status.getUser().getScreenName();
		
		// description
		this.description = status.getUser().getDescription();
		
		// number tweet (max last 20 tweet)
		this.numberTweet = status.getUser().getStatusesCount();
		
		// tweet for day
		float day = fromMillisecondsToDay(System.currentTimeMillis() - dateOldestTweet);
		
		if(day != 0){
			this.tweetForDay = this.numberTweet/day;
		}
		
		// follower
		this.follower = status.getUser().getFollowersCount();
		
		// following
		this.following = status.getUser().getFriendsCount();
		
		// retweet percentage last tweets
		if(this.numberTweet != 0){
			this.retweetPercentage = (retweetCount/this.numberTweet)*100;
		}
		
		// active Account
		float lastActivity = fromMillisecondsToDay(System.currentTimeMillis() - dateLastTweet);
		if(lastActivity <= 365){
			this.activeAccount = true;
		}
		
		// profile locked
		this.profileLocked = status.getUser().isProtected();
		
		// retrived on
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.retrivedOn = sdf.format(lastUpdate);
		
	}

}
