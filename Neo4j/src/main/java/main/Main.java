package main;

import java.util.List;

import twitter.TwitterUtil;

public class Main{

	public static void main(String args[]) throws Exception {
		TwitterUtil twitter = new TwitterUtil();
		List<List<String>> lls=twitter.getTweetDataItems(769181646176284672L);
		lls.forEach( l1 -> l1.forEach(action -> System.out.println(action)));
		System.exit(0);
	}
}