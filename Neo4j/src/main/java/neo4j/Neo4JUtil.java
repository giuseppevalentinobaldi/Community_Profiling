package neo4j;

import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Values;

public class Neo4JUtil implements AutoCloseable {

	private final Driver driver;

	public Neo4JUtil(String uri, String user, String password) {
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	@Override
	public void close() throws Exception {
		driver.close();
	}

	public void printGreeting(final String message) {
		try (Session session = driver.session()) {
			String greeting = session.writeTransaction(new TransactionWork<String>() {
				@Override
				public String execute(Transaction tx) {
					StatementResult result = tx.run(
							"CREATE (a:Greeting) " + "SET a.message = $message "
									+ "RETURN a.message + ', from node ' + id(a)",
							Values.parameters("message", message));
					return result.single().get(0).asString();
				}
			});
			System.out.println(greeting);
		}
	}
	
	public void printTweets(List<String> tweetItem){
		// crazione nodi
		driver.session().run("CREATE (a:Person {name: {name}, last_name: {last_name}})", Values.parameters("name", "Mario", "last_name", "Rossi"));
		driver.session().run("CREATE (a:Person {name: {name}, last_name: {last_name}})", Values.parameters("name", "Maria", "last_name", "Verdi"));
		// creazione archo
		driver.session().run("MATCH (a:Person),(b:Person) WHERE a.name = 'Mario' AND b.name = 'Maria' CREATE (a)-[r:RELTYPE]->(b)");
	}
}

	/*
	 * public static void main( String[] args ) throws Exception { try ( Main
	 * greeter = new Main( "bolt://localhost:7687", "neo4j", "neo4j" ) ) {
	 * greeter.printGreeting( "hello, world" ); } }
	 */

