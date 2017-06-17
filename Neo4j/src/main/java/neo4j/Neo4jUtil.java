package neo4j;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

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
	
	

}
