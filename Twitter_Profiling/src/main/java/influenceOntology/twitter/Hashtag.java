package influenceOntology.twitter;

public class Hashtag {
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
