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
	
	public Neo4jUtil(String uri, String user, String password){
		
		this.driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		this.session = this.driver.session();
		
	}
	
	public void close(){
		
		this.session.close();
		this.driver.close();
		
	}
	
	public void printUserData (TwitterUserData twitterUserData){
		//creo nodo utente
		//session.run("CREATE (a:Person {name: {name}, last_name: {last_name}})", Values.parameters("name", "Mario", "last_name", "Rossi"));
		//session.run("CREATE (a:Person {name: {name}, last_name: {last_name}})", Values.parameters("name", "Maria", "last_name", "Verdi"));
		
		// creazione archo
		//.session.run("MATCH (a:Person),(b:Person) WHERE a.name = 'Mario' AND b.name = 'Maria' CREATE (a)-[r:RELTYPE]->(b)");		
	}	

}
