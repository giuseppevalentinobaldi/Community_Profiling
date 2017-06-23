package influenceOntology.twitter;

import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

public abstract class User extends OnlineAccount{
	@Relationship(type = "INCLUDED_HASHTAG", direction = "OUTGOING")
	private List<Hashtag> hashtag;
	@Relationship(type = "INCLUDED_URL", direction = "OUTGOING")
	private List<URL> url;
	@Relationship(type = "INCLUDED_IMAGE", direction = "OUTGOING")
	private List<Image> image;
	@Relationship(type = "HAS_GENERAL_INFORMATION", direction = "OUTGOING")
	private GeneralInformation gi;
	@Relationship(type = "HAS_QUALITY_METRICS", direction = "OUTGOING")
	private QualityMetric qm;
	
	public User(long id) {
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
	
	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
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
