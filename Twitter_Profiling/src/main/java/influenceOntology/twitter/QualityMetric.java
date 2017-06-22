package influenceOntology.twitter;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import twitter4j.Status;

import java.lang.Math;
import java.util.List;

@NodeEntity
public class QualityMetric {
	
	@GraphId
	private Long graphId;
	@Property
	private int hIndexRetweet;
	@Property
	private float hIndexRetweetDaily;
	@Property
	private int hIndexFavorite;
	@Property
	private float hIndexFavoriteDaily;
	@Property
	private float replayRatio;
	@Property
	private float influenceMetric;
	
	public QualityMetric(){
		
		this.hIndexRetweet = 0;
		this.hIndexRetweetDaily = 0;
		this.hIndexFavorite = 0;
		this.hIndexFavoriteDaily = 0;
		this.replayRatio = 0;
		this.influenceMetric = 0;
		
	}
	
	public long getGraphId(){
		return graphId;
	}
	
	public int gethIndexRetweet() {
		return hIndexRetweet;
	}

	public float gethIndexRetweetDaily() {
		return hIndexRetweetDaily;
	}

	public int gethIndexFavorite() {
		return hIndexFavorite;
	}

	public float gethIndexFavoriteDaily() {
		return hIndexFavoriteDaily;
	}

	public float getReplayRatio() {
		return replayRatio;
	}

	public float getInfluenceMetric() {
		return influenceMetric;
	}
	
	public void calculationUserQualityMetric(List<Status> statuses){
		
		if(statuses.size() == 0){
			return;
		}
		
		long dateLastTweet = Long.MIN_VALUE;
		long dateOldestTweet = Long.MAX_VALUE;
		
		// conteggio retweet, favorite e reply
		int replyCount = 0;
		long date;
		int totalRetweet = 0;
		int totalFavorite = 0;
		for(Status status : statuses)
		{
			
			// retweet
			if(status.getRetweetCount() > 0){
				this.hIndexRetweet++;
				totalRetweet += status.getRetweetCount();
			}
			
			// favorite
			if(status.getFavoriteCount() > 0){
				this.hIndexFavorite++;
				totalFavorite = status.getFavoriteCount();
			}
			
			// reply
			if(status.getInReplyToStatusId() != -1){
				replyCount++;
			}
			
			date = dateLastTweet = status.getCreatedAt().getTime();
			
			// date of last tweet
			if(dateLastTweet < date){
				dateLastTweet = date;
			}
			
			// date of oldest tweet
			if(dateOldestTweet > date){
				dateOldestTweet = date;
			}
			
		}
		
		// calcolo giorni dall'ultimo tweet effettuato e dal primo
		float daysLastTweet = fromMillisecondsToDay(System.currentTimeMillis() - dateLastTweet);
		float daysFirstTweet = fromMillisecondsToDay(System.currentTimeMillis() - dateOldestTweet);

		// calcolo retweet e favorite giornaliero
		this.hIndexRetweetDaily = totalRetweet/daysFirstTweet;
		this.hIndexFavoriteDaily = totalFavorite/daysFirstTweet;
		
		int tweetCount = statuses.size();
		int follower = statuses.get(0).getUser().getFollowersCount();
		int following = statuses.get(0).getUser().getFriendsCount();
		
		// calcolo reply ratio
		if(tweetCount != 0){
			this.replayRatio = replyCount/tweetCount;
		}
		
		// calcolo dell'influence metric
		this.influenceMetric = InfluenceMetric(tweetCount, daysLastTweet, follower, following);
		
	}
	
	private static float fromMillisecondsToDay(long time){
		return time/(1000*3600*24);
	}
	
	private static int OOM(int follower){
		
		if(follower < 0){
			return -1;
		}
		if(follower == 0){
			return 0;
		}
		
		double num = follower;
		int oom = 0;
		boolean test = true;
		
		while(test){
			
			if(num > 0.5 && num <= 5){
				test = false;
			}
			else if(num <= 0.5){
				num *= 10;
				oom--;
			}
			else{
				num /= 10;
				oom++;
			}
			
		}
		
		return oom;
		
	}
	
	private static float InfluenceMetric(int tweet, float day, int follower, int following){
		
		if(day == 0){
			day = 1;
		}
		if(following == 0){
			following = 1;
		}
		
		float im = (float)((tweet/day)*(OOM(follower))*(Math.log10((double)((follower/following)+1))));
		return im;
	
	}
	
}
