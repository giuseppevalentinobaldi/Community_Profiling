package influenceOntology.neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import influenceOntology.twitter.TwitterUserAccount;

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
	
	public void printTwitterUserAccount(TwitterUserAccount twitterUser){
		
		String sID = "";
		String name = "";
		String property = "";
		
		
		// CREAZIONE NODO UTENTE
		
		// creazione del nodo utente (TwitterUser)
		session.run("CREATE (a:TwitterUser {name : {name}, value: {value}, description: {description}})",
				Values.parameters("name", twitterUser.getAccountName(), "value", twitterUser.getId(), "description", ""));
		
		//creazione nodo account name (literal)
		name = "Account Name";
		sID = twitterUser.getId()+name.replace(" ", "");
		session.run("CREATE (a:Literal {name : {name}, value: {value}, description: {description}, id: {id}})",
				Values.parameters("name", name, "value", twitterUser.getAccountName(), "description", "", "id", sID));
		
		property = "account name";
		session.run("MATCH (a:TwitterUser),(b:Literal) WHERE a.value = "+twitterUser.getId()+
				" AND b.id = '"+sID+
				"' CREATE (a)-[r:"+property+"]->(b)");
		
		
		// CREAZIONE NODO GENERAL INFORMATION
		
		// creazione nodo general information
		name = "General Information";
		sID = twitterUser.getId()+name.replace(" ", "");
		session.run("CREATE (a:TwitterUserInformation {name : {name}, description: {description}, id: {id})",
				Values.parameters("name", name, "description", "", "id", sID));
		
	}
	
}







































