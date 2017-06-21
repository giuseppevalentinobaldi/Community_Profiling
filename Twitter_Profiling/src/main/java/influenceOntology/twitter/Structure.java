package influenceOntology.twitter;

public class Structure {
	private TwitterUserAccount tua;
	private boolean isComplete;
	
	public Structure(TwitterUserAccount tua,boolean isComplete){
		this.tua=tua;
		this.isComplete=isComplete;
		
	}

	public TwitterUserAccount getTua() {
		return tua;
	}

	public void setTua(TwitterUserAccount tua) {
		this.tua = tua;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

}
