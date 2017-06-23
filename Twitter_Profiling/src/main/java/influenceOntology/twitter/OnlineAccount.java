package influenceOntology.twitter;

import org.neo4j.ogm.annotation.Property;

public abstract class OnlineAccount {
	@Property
	private long id;
	@Property
	private String accountName;

	public OnlineAccount(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
