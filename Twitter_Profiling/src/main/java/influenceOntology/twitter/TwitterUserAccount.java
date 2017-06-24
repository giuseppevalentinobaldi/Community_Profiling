package influenceOntology.twitter;

import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class TwitterUserAccount extends User {
	@GraphId
	private Long graphId;
	@Relationship(type = "HAS_MENTIONED", direction = "OUTGOING")
	private Set<TwitterUserAccount> mentions;
	@Relationship(type = "HAS_REPLIED_TO", direction = "OUTGOING")
	private Set<TwitterUserAccount> replyTo;
	@Relationship(type = "HAS_FOLLOWER", direction = "INCOMING")
	private List<TwitterUserAccount> hasFollower;
	@Relationship(type = "IS_FOLLOWING", direction = "OUTGOING")
	private List<TwitterUserAccount> isFollowing;
	// private List<TwitterUserAccount> hasSimilar;

	public TwitterUserAccount(long id) {
		super(id);
	}

	public Set<TwitterUserAccount> getMentions() {
		return mentions;
	}

	public void setMentions(Set<TwitterUserAccount> mentions) {
		this.mentions = mentions;
	}

	public Set<TwitterUserAccount> getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(Set<TwitterUserAccount> replyTo) {
		this.replyTo = replyTo;
	}

	public List<TwitterUserAccount> getHasFollower() {
		return hasFollower;
	}

	public void setHasFollower(List<TwitterUserAccount> hasFollower) {
		this.hasFollower = hasFollower;
	}

	public List<TwitterUserAccount> getIsFollowing() {
		return isFollowing;
	}

	public void setIsFollowing(List<TwitterUserAccount> isFollowing) {
		this.isFollowing = isFollowing;
	}

	/*
	 * public List<TwitterUserAccount> getHasSimilar() { return hasSimilar; }
	 * public void setHasSimilar(List<TwitterUserAccount> hasSimilar) {
	 * this.hasSimilar = hasSimilar; }
	 */

}
