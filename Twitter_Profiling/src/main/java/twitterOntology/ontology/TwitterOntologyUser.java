package twitterOntology.ontology;

public class TwitterOntologyUser {
	
	private final String nameLabel = "tweet user";
	private final String nameDescription = "A data item about the user account which generated the associated tweet data item.";

	private final String screenNameLabel = "twitter user screen name";
	private final String screenNameDescription = "";

	private final String idLabel = "twitter user id";
	private final String idDescription = "";
	
	//user data ontology 
	
	public String getNameLabel() {
		return nameLabel;
	}

	public String getNameDescription() {
		return nameDescription;
	}

	public String getScreenNameLabel() {
		return screenNameLabel;
	}

	public String getScreenNameDescription() {
		return screenNameDescription;
	}

	public String getIdLabel() {
		return idLabel;
	}

	public String getIdDescription() {
		return idDescription;
	}

}
