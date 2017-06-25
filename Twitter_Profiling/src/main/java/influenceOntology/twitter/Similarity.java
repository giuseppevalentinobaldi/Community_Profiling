package influenceOntology.twitter;

import java.util.Set;

public class Similarity {
	private EntitySimilarity url;
	private EntitySimilarity mentions;
	private EntitySimilarity hashtag;
	private int label;
	private TwitterUserAccount b;

	public Similarity(TwitterUserAccount a, TwitterUserAccount b, Set<Long> idMentions) {
		this.b=b;
		float urlN = a.getUrl().size();
		float mentionsN = a.getMentions().size();
		float hashtagN = a.getHashtag().size();
		float sN = urlN + mentionsN + hashtagN;
	}
	
	private int urlc=0;
	public void intersectURL(Set<URL> set,Set<URL> set2){
		set.forEach(e1 -> {set2.forEach(e2-> {if(e1.getUrl().equals(e2.getUrl())){urlc++;}});});
	}
	
	private int hashtagc=0;
	public void intersectHashtag(Set<Hashtag> set,Set<Hashtag> set2){
		set.forEach(e1 -> {set2.forEach(e2-> {if(e1.getHashtag().equals(e2.getHashtag())){hashtagc++;}});});
	}
	
	private int mentionsc=0;
	public void intersectMentions(Set<TwitterUserAccount> set, Set<Long> idMentions){
		set.forEach(e1 -> {idMentions.forEach(e2-> {if(e1.getId()==e2.longValue()){mentionsc++;}});});
	}

	public float getSimilarity() {
		return (this.getHashtag().getEcwc() + this.getMentions().getEcwc() + this.getURL().getEcwc())
				* (this.getLabel() / 4);
	}

	public EntitySimilarity getURL() {
		return url;
	}

	public void setURL(EntitySimilarity url) {
		this.url = url;
	}

	public EntitySimilarity getMentions() {
		return mentions;
	}

	public void setMentions(EntitySimilarity mentions) {
		this.mentions = mentions;
	}

	public EntitySimilarity getHashtag() {
		return hashtag;
	}

	public void setHashtag(EntitySimilarity hashtag) {
		this.hashtag = hashtag;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public TwitterUserAccount getB() {
		return b;
	}

	public void setB(TwitterUserAccount b) {
		this.b = b;
	}

}
