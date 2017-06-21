package influenceOntology.twitter;

public class URL {
	private String domain;
	private String url;
	private String fullurl;
	
	public URL(String domain, String url, String fullurl){
		this.domain=domain;
		this.url=url;
		this.fullurl=fullurl;
	}
	
	public URL(String fullurl){
		this.fullurl=fullurl;
	}
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFullurl() {
		return fullurl;
	}
	public void setFullurl(String fullurl) {
		this.fullurl = fullurl;
	}
}
