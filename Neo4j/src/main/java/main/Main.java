package main;

import twitter.TwitterUtil;

public class Main{

	public static void main(String args[]) throws Exception {
		TwitterUtil twitter = new TwitterUtil();
		twitter.Tweets();
		System.exit(0);
	}
}