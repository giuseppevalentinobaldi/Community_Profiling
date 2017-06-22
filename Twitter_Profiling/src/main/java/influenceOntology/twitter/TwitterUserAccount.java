package influenceOntology.twitter;

import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class TwitterUserAccount extends User {
	@GraphId
	private Long graphId;
	@Relationship(type = "HAS_MENTIONED", direction = "OUTGOING")
	private List<TwitterUserAccount> mentions;
	@Relationship(type = "HAS_REPLIED_TO", direction = "OUTGOING")
	private List<TwitterUserAccount> replyTo;
	@Relationship(type = "HAS_FOLLOWER", direction = "OUTGOING")
	private List<TwitterUserAccount> hasFollower;
	@Relationship(type = "IS_FOLLOWING", direction = "OUTGOING")
	private List<TwitterUserAccount> isFollowing;
	// private List<TwitterUserAccount> hasSimilar;

	public TwitterUserAccount(long id) {
		super(id);
	}

	public TwitterUserAccount(long id, List<Hashtag> hashtag, List<URL> url, GeneralInformation gi, QualityMetric qm) {
		super(id, hashtag, url, gi, qm);
	}

	public TwitterUserAccount(long id, List<TwitterUserAccount> mentions, List<TwitterUserAccount> replyTo,
			List<TwitterUserAccount> hasFollower, List<TwitterUserAccount> isFollowing) {
		super(id);
		this.setMentions(mentions);
		this.setReplyTo(replyTo);
		this.setHasFollower(hasFollower);
		this.setIsFollowing(isFollowing);
		// this.hasSimilar = hasSimilar;
	}

	public TwitterUserAccount(long id, List<Hashtag> hashtag, List<URL> url, GeneralInformation gi, QualityMetric qm,
			List<TwitterUserAccount> mentions, List<TwitterUserAccount> replyTo, List<TwitterUserAccount> hasFollower,
			List<TwitterUserAccount> isFollowing) {
		super(id, hashtag, url, gi, qm);
		this.setMentions(mentions);
		this.setReplyTo(replyTo);
		this.setHasFollower(hasFollower);
		this.setIsFollowing(isFollowing);
		// this.hasSimilar = hasSimilar;
	}

	public List<TwitterUserAccount> getMentions() {
		return mentions;
	}

	public void setMentions(List<TwitterUserAccount> mentions) {
		this.mentions = mentions;
	}

	public List<TwitterUserAccount> getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(List<TwitterUserAccount> replyTo) {
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
