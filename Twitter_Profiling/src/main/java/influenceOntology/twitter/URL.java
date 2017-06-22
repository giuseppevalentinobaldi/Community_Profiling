package influenceOntology.twitter;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class URL {
	@GraphId
	private Long graphId;
	@Property
	private String domain;
	@Property
	private String url;
	@Property
	private String fullurl;

	public URL(String domain, String url, String fullurl) {
		this.domain = domain;
		this.url = url;
		this.fullurl = fullurl;
	}

	public URL(String url, String fullurl) {
		this.domain = getDomain(fullurl);
		this.url = url;
		this.fullurl = fullurl;
	}

	private String getDomain(String fullurl) {
		String output = "";
		int count = 0;
		for (int i = 0; i < fullurl.length() && count < 3; i++) {
			char lettera = fullurl.charAt(i);
			if (lettera == '/')
				count++;
			if (count == 3 && lettera == '/')
				output += "";
			if(count<2  || lettera == '/')
				output += "";
			else
				output += lettera;
		}
		return output;
	}

	public URL(String fullurl) {
		this.fullurl = fullurl;
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
