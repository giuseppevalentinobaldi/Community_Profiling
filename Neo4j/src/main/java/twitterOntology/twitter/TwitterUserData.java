package twitterOntology.twitter;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import twitterOntology.ontology.TwitterOntologyUser;

@NodeEntity
public class TwitterUserData extends TwitterOntologyUser {
	
	@GraphId private Long graphId;
	@Property private long id;
	@Property private String name;
	@Property private String screenName;
	@Relationship(type = "HAS_ASSERTED_VALUE", direction = "OUTGOING")
	private List<TweetData> tweetDataList;

	public TwitterUserData(long userId) {

		this.id = userId;
		this.name = "";
		this.screenName = "";
		this.tweetDataList = null;

	}

	public TwitterUserData(long userId, String userName, String userScreenName) {

		this.id = userId;
		this.name = userName;
		this.screenName = userScreenName;
		this.tweetDataList = new LinkedList<TweetData>();

	}

	public void addTweetData(TweetData tweetData) {

		this.tweetDataList.add(tweetData);

	}

	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public List<TweetData> getTweetDataList() {
		return this.tweetDataList;
	}

}
