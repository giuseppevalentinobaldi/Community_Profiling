package influenceOntology.twitter;

public abstract class OnlineAccount {
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
