package ontology;

public class TwitterOntologyUser {
	
	private final String nameLabel = "tweet user";
	private final String nameDescription = "A data item about the user account which generated the associated tweet data item.";

	private final String screenNameLabel = "twitter user screen name";
	private final String screenNameDescricption = "";

	private final String idLabel = "twitter user id";
	private final String idDescricption = "";
	
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

	public String getScreenNameDescricption() {
		return screenNameDescricption;
	}

	public String getIdLabel() {
		return idLabel;
	}

	public String getIdDescricption() {
		return idDescricption;
	}

}
