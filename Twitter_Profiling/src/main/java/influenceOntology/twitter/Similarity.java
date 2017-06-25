package influenceOntology.twitter;

import java.util.Set;

public class Similarity {
	private EntitySimilarity url;
	private EntitySimilarity mentions;
	private EntitySimilarity hashtag;
	private TwitterUserAccount b;
	private float urlC;
	private float hashtagC;
	private float mentionsC;
	private float label;

	public Similarity(TwitterUserAccount a, TwitterUserAccount b, Set<Long> idMentions) {
		this.urlC = 0;
		this.hashtagC = 0;
		this.mentionsC = 0;
		this.label = 0;
		this.url = new EntitySimilarity();
		this.mentions = new EntitySimilarity();
		this.hashtag = new EntitySimilarity();
		float urlN = a.getUrl().size();
		float mentionsN = a.getMentions().size();
		float hashtagN = a.getHashtag().size();
		float sN = urlN + mentionsN + hashtagN;
		intersectURL(a.getUrl(), b.getUrl());
		intersectHashtag(a.getHashtag(), b.getHashtag());
		intersectMentions(a.getMentions(), idMentions);
		if (urlN > 0) {
			this.label++;
			this.url.init(this.urlC, urlN, sN);
		}
		if (mentionsN > 0) {
			this.label++;
			this.mentions.init(this.mentionsC, mentionsN, sN);
		}
		if (hashtagN > 0) {
			this.label++;
			this.hashtag.init(this.hashtagC, hashtagN, sN);
		}
		System.out.println(urlC+" "+hashtagC+" "+mentionsC+" "+label);
		this.b = b;
	}

	public void intersectURL(Set<URL> set, Set<URL> set2) {
		set.forEach(e1 -> {
			set2.forEach(e2 -> {
				if (e1.getUrl().equals(e2.getUrl())) {
					urlC++;
				}
			});
		});
	}

	public void intersectHashtag(Set<Hashtag> set, Set<Hashtag> set2) {
		set.forEach(e1 -> {
			set2.forEach(e2 -> {
				if (e1.getHashtag().equals(e2.getHashtag())) {
					hashtagC++;
				}
			});
		});
	}

	public void intersectMentions(Set<TwitterUserAccount> set, Set<Long> idMentions) {
		set.forEach(e1 -> {
			idMentions.forEach(e2 -> {
				if (e1.getId() == e2.longValue()) {
					mentionsC++;
				}
			});
		});
	}

	public float getSimilarity() {
		// similarity metric
		return (this.getHashtag().getEcwc() + this.getMentions().getEcwc() + this.getURL().getEcwc())
				* (this.getLabel() / 3);
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

	public float getLabel() {
		return label;
	}

	public void setLabel(float label) {
		this.label = label;
	}

	public TwitterUserAccount getB() {
		return b;
	}

	public void setB(TwitterUserAccount b) {
		this.b = b;
	}

}
