package neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import twitter.TwitterUserData;

public class Neo4jUtil {

	private final Driver driver;
	private final Session session;

	public Neo4jUtil(String uri, String user, String password) {

		this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		this.session = this.driver.session();

	}

	public void close() {

		this.session.close();
		this.driver.close();

	}

	public void printUserData(TwitterUserData twitterUserData) {
		session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
				Values.parameters("name", twitterUserData.getIdLabel(),"value", twitterUserData.getId(), "description",
						twitterUserData.getIdDescription()));
		session.run("CREATE (a:TwitterUserGod {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUserData.getNameLabel(),"value", twitterUserData.getName(), "description",
						twitterUserData.getNameDescription(),"id",twitterUserData.getId()+twitterUserData.getNameLabel()));
		session.run("MATCH (a:TwitterUser),(b:TwitterUserGod) WHERE a.value = "+twitterUserData.getId()+" AND b.id = '"+twitterUserData.getId()+twitterUserData.getNameLabel()+"' CREATE (a)-[r:RELTYPE]->(b) RETURN r");
		session.run("CREATE (a:TwitterUserPArt {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", twitterUserData.getScreenNameLabel(),"value", twitterUserData.getScreenName(), "description",
						twitterUserData.getScreenNameDescription(),"id",twitterUserData.getId()+twitterUserData.getScreenNameLabel()));
		session.run("MATCH (a:TwitterUser),(b:TwitterUserPArt) WHERE a.value = "+twitterUserData.getId()+" AND b.id = '"+twitterUserData.getId()+twitterUserData.getScreenNameLabel()+"' CREATE (a)-[r:RELTYPE]->(b) RETURN r");

		// session.run("CREATE (a:Person {name: {name}, last_name:
		// {last_name}})", Values.parameters("name", "Maria", "last_name",
		// "Verdi"));

		// creazione archo
		// .session.run("MATCH (a:Person),(b:Person) WHERE a.name = 'Mario' AND
		// b.name = 'Maria' CREATE (a)-[r:RELTYPE]->(b)");
	}

}
