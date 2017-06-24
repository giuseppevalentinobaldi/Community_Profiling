package influenceOntology.twitter;

import java.util.Set;

import org.neo4j.ogm.annotation.Relationship;

public abstract class User extends OnlineAccount{
	@Relationship(type = "INCLUDED_HASHTAG", direction = "OUTGOING")
	private Set<Hashtag> hashtag;
	@Relationship(type = "INCLUDED_URL", direction = "OUTGOING")
	private Set<URL> url;
	@Relationship(type = "INCLUDED_IMAGE", direction = "OUTGOING")
	private Set<Image> image;
	@Relationship(type = "HAS_GENERAL_INFORMATION", direction = "OUTGOING")
	private GeneralInformation gi;
	@Relationship(type = "HAS_QUALITY_METRICS", direction = "OUTGOING")
	private QualityMetric qm;
	
	public User(long id) {
		super(id);
	}

	public Set<Hashtag> getHashtag() {
		return hashtag;
	}

	public void setHashtag(Set<Hashtag> hashtag) {
		this.hashtag = hashtag;
	}

	public Set<URL> getUrl() {
		return url;
	}

	public void setUrl(Set<URL> url) {
		this.url = url;
	}
	
	public Set<Image> getImage() {
		return image;
	}

	public void setImage(Set<Image> image) {
		this.image = image;
	}

	public GeneralInformation getGi() {
		return gi;
	}

	public void setGi(GeneralInformation gi) {
		this.gi = gi;
	}

	public QualityMetric getQm() {
		return qm;
	}

	public void setQm(QualityMetric qm) {
		this.qm = qm;
	}

}
