package influenceOntology.twitter;

import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

public abstract class User extends OnlineAccount{
	@Relationship(type = "INCLUDED_HASHTAG", direction = "OUTGOING")
	private List<Hashtag> hashtag;
	@Relationship(type = "INCLUDED_URL", direction = "OUTGOING")
	private List<URL> url;
	@Relationship(type = "HAS_GENERAL_INFORMATION", direction = "OUTGOING")
	private GeneralInformation gi;
	@Relationship(type = "HAS_QUALITY_METRICS", direction = "OUTGOING")
	private QualityMetric qm;
	
	public User(long id, List<Hashtag> hashtag, List<URL> url, GeneralInformation gi, QualityMetric qm) {
		super(id);
		this.hashtag=hashtag;
		this.url=url;
		this.gi=gi;
		this.qm=qm;
	}
	
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
