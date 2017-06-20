package influenceOntology.twitter;

import java.util.List;

public class TwitterUserAccount extends OnlineAccount{
	private List<Hashtag> hashtag;
	private List<URL> url;
	private GeneralInformation gi;
	private QualityMetrics qm;
	private List<TwitterUserAccount> mentions;
	private List<TwitterUserAccount> replyTo;
	private List<TwitterUserAccount> hasFollower;
	private List<TwitterUserAccount> isFollowing;
	private List<TwitterUserAccount> hasSimilar;
	
}
