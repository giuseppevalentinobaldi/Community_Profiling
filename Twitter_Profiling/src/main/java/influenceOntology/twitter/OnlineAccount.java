package influenceOntology.twitter;

import org.neo4j.ogm.annotation.Property;

public abstract class OnlineAccount {
	@Property
	private long id;
	
	public OnlineAccount(long id){
		this.id=id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
