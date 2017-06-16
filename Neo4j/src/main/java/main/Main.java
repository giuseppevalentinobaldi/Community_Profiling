package main;

import java.util.List;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.Values;

import twitter.TwitterUtil;
import twitter4j.Status;

public class Main implements AutoCloseable {
	private final Driver driver;

	public Main(String uri, String user, String password) {
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

	public static void main(String args[]) throws Exception {
		TwitterUtil twitter = new TwitterUtil();
		List<Status> statuses = twitter.getTwitter().getUserTimeline(769181646176284672L);
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser()+""+status.getUser().getName() + ":" +
	                           status.getText());
	    }
		//Status status = twitter.updateStatus("prova");
		//System.out.println("Successfully updated the status to [" + status.getText() + "].");
		System.exit(0);
	}

	/*
	 * public static void main( String[] args ) throws Exception { try (
	 * HelloWorldExample greeter = new HelloWorldExample(
	 * "bolt://localhost:7687", "neo4j", "neo4j" ) ) { greeter.printGreeting(
	 * "hello, world" ); } }
	 */
}