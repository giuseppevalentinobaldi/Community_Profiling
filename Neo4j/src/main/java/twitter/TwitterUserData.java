package twitter;

import java.util.LinkedList;
import java.util.List;

public class TwitterUserData {

	private long id;
	private String name;
	private String screenName;
	private List<TweetData> tweetDataList;
	
	public TwitterUserData(long userId){
		
		this.id = userId;
		this.name = null;
		this.screenName = null;
		this.tweetDataList = null;
		
	}
	
	public TwitterUserData(long userId, String userName, String userScreenName){
		
		this.id = userId;
		this.name = userName;
		this.screenName = userScreenName;
		this.tweetDataList = new LinkedList<TweetData>();
		
	}
	
	public void addTweetData(TweetData tweetData){
		
		this.tweetDataList.add(tweetData);
		
	}
	
	public long getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getScreenName(){
		return this.screenName;
	}
	
	public List<TweetData> getTweetDataList(){
		return this.tweetDataList;
	}
	
}
