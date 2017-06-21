package influenceOntology.neo4j;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.service.Components;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import influenceOntology.twitter.TwitterUserAccount;
import twitterOntology.twitter.TwitterUserData;

public class Neo4jOGM {
	private Session session;
	
	public Neo4jOGM(String uri, String user, String password){
		Configuration cfg = Components.getConfiguration();
		cfg.driverConfiguration()
	        .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
	        .setURI("bolt://"+user+":"+password+"@"+uri);
		SessionFactory sessionFactory = new SessionFactory("influenceOntology.twitter");
		this.setSession(sessionFactory.openSession());
	}
	
	public void printCompactUserData(TwitterUserAccount twitterUserAccount){
		this.getSession().save(twitterUserAccount);
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(TwitterUserData twitterUserData) {
		this.getSession().save(twitterUserData);
		
	}
}
