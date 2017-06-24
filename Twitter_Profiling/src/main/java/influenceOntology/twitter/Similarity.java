package influenceOntology.twitter;

public class Similarity {
	private EntitySimilarity URL;
	private EntitySimilarity mentions;
	private EntitySimilarity hashtag;
	
	public Similarity(TwitterUserAccount a,TwitterUserAccount b){
		
	}
	
	public EntitySimilarity getURL() {
		return URL;
	}
	public void setURL(EntitySimilarity uRL) {
		URL = uRL;
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

}
