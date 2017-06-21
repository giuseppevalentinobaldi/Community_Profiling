package influenceOntology.twitter;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
@NodeEntity
public class Hashtag {
	@GraphId
	private Long graphId;
	@Property
	private String hashtag;
	
	public Hashtag(String hashtag){
		this.setHashtag(hashtag);
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	public boolean equals(String hashtag){
		return this.hashtag.equals(hashtag);
	}
	
	public String toString(){
		return "#"+this.getHashtag();
	}
}
