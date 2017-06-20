package influenceOntology.twitter;

import java.util.List;

public class TwitterUserAccount extends OnlineAccount {
	private List<Hashtag> hashtag;
	private List<URL> url;
	private GeneralInformation gi;
	private QualityMetrics qm;
	private List<TwitterUserAccount> mentions;
	private List<TwitterUserAccount> replyTo;
	private List<TwitterUserAccount> hasFollower;
	private List<TwitterUserAccount> isFollowing;
	private List<TwitterUserAccount> hasSimilar;

	public TwitterUserAccount(String id, List<Hashtag> hashtag, List<URL> url, GeneralInformation gi, QualityMetrics qm,
			List<TwitterUserAccount> mentions, List<TwitterUserAccount> replyTo, List<TwitterUserAccount> hasFollower,
			List<TwitterUserAccount> isFollowing, List<TwitterUserAccount> hasSimilar) {
		super(id);
		this.hashtag = hashtag;
		this.url = url;
		this.gi = gi;
		this.qm = qm;
		this.mentions = mentions;
		this.replyTo = replyTo;
		this.hasFollower = hasFollower;
		this.isFollowing = isFollowing;
		this.hasSimilar = hasSimilar;
	}
	
	public TwitterUserAccount(String id) {
		super(id);
	}
	
	public List<Hashtag> getHashtag() {
		return hashtag;
	}

	public void setHashtag(List<Hashtag> hashtag) {
		this.hashtag = hashtag;
	}

	public List<URL> getUrl() {
		return url;
	}

	public void setUrl(List<URL> url) {
		this.url = url;
	}

	public GeneralInformation getGi() {
		return gi;
	}

	public void setGi(GeneralInformation gi) {
		this.gi = gi;
	}

	public QualityMetrics getQm() {
		return qm;
	}

	public void setQm(QualityMetrics qm) {
		this.qm = qm;
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

	public List<TwitterUserAccount> getHasSimilar() {
		return hasSimilar;
	}

	public void setHasSimilar(List<TwitterUserAccount> hasSimilar) {
		this.hasSimilar = hasSimilar;
	}

}
