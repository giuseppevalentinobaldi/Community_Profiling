package influenceOntology.twitter;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class GeneralInformation {
	@GraphId
	private Long graphId;
	@Property
	private String description; // Provides the description of the account, as
								// set by its owner.
	@Property
	private int followers; // Provides the number of the followers of the
							// account.
	@Property
	private String displayName; // Provides the name displayed at the web page
								// of the account, as set by its owner.
	@Property
	private int following; // Provides the number of the accounts that the
							// account follows.
	@Property
	private float tweetsPerDay;
	@Property
	private boolean activeAccount;
	// profile locked
	// retrieved on
	// retweet percentage
	// number of tweets

	public GeneralInformation(String description, int followers, String displayName, int following, float tweetsPerDay,
			boolean activeAccount) {
		this.description = description;
		this.followers = followers;
		this.displayName = displayName;
		this.following = following;
		this.tweetsPerDay = tweetsPerDay;
		this.activeAccount=activeAccount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public float getTweetsPerDay() {
		return tweetsPerDay;
	}

	public void setTweetsPerDay(float tweetsPerDay) {
		this.tweetsPerDay = tweetsPerDay;
	}

	public boolean isActiveAccount() {
		return activeAccount;
	}

	public void setActiveAccount(boolean activeAccount) {
		this.activeAccount = activeAccount;
	}

}
