package neo4j;

import java.util.Iterator;
import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Values;

public class Neo4jUtil implements AutoCloseable {

	private final Driver driver;

	public Neo4jUtil(String uri, String user, String password) {
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
	/*provvisorio*/
	public void printTweets(List<String> tweetItem) {
		try (Session session = driver.session()) {
			Iterator<String> i = tweetItem.iterator();
			String value;
			boolean isFirst=true;
			while(i.hasNext()){
				value=i.next();
				if(isFirst){
					session.run("CREATE (a:TweetDataItem {label: 'Tweet', value: {value}})", Values.parameters("value", value));
					isFirst=false;
				}
				else{
					session.run("CREATE (a:TweetDataItemPart {label: 'TweetPart', value: {value}})", Values.parameters("value", value));
				}
			}
			session.run("MATCH (a:TweetDataItem),(b:TweetDataItemPart) WHERE a.label = 'Tweet' AND b.label = 'TweetPart' CREATE (a)-[r:hasAssertedValue]->(b)");
		}
	}
}

/*
 * public static void main( String[] args ) throws Exception { try ( Main
 * greeter = new Main( "bolt://localhost:7687", "neo4j", "neo4j" ) ) {
 * greeter.printGreeting( "hello, world" ); } }
 */
