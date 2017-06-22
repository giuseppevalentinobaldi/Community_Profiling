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
		
		long dayLastTweet = Long.MIN_VALUE;
		
		// calcolo giorni dalla registrazione utente
		long dateUserRegister = statuses.get(0).getUser().getCreatedAt().getTime();
		float daysUser = fromMillisecondsToDay(System.currentTimeMillis() - dateUserRegister);
		if(daysUser == 0){
			daysUser = 1;
		}
		int replyCount = 0;
		
		// conteggio retweet, favorite e reply
		for(Status status : statuses)
		{
			
			this.hIndexRetweet += status.getRetweetCount();
			this.hIndexFavorite += status.getFavoriteCount();
			
			if(status.getInReplyToUserId() != -1){
				replyCount++;
			}
			
			if(dayLastTweet < status.getCreatedAt().getTime()){
				dayLastTweet = status.getCreatedAt().getTime();
			}
			
		}
		
		// calcolo retweet e favorite giornaliero
		this.hIndexRetweetDaily = this.hIndexRetweet/daysUser;
		this.hIndexFavoriteDaily = this.hIndexFavorite/daysUser;
		
		int tweet = statuses.size();
		int follower = statuses.get(0).getUser().getFollowersCount();
		int following = statuses.get(0).getUser().getFriendsCount();
		
		// calcolo reply ratio
		this.replayRatio = replyCount/tweet;
		
		// calcolo giorni dall'ultimo tweet effettuato
		float daysTweet = fromMillisecondsToDay(System.currentTimeMillis() - dayLastTweet);
		if(daysTweet == 0){
			daysTweet = 1;
		}
		// calcolo dell'influence metric
		this.influenceMetric = InfluenceMetric(tweet, daysTweet, follower, following);
		
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
		
		float im = (float)((tweet/day)*(OOM(follower))*(Math.log10((double)((follower/(following+1))))));
		return im;
	
	}
	
}
