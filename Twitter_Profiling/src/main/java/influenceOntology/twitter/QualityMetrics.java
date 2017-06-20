package influenceOntology.twitter;

public class QualityMetrics {
	private float replyRatio; // Provides the ratio of the user's latest tweets
								// which are used as replies to other users'
								// tweets.
	private float influenceMetric; // Indicates the value of the Influence
									// Metric measurement. Its aim is to
									// describe both the importance and impact
									// of an account in a social network.
	// h-index retweet daily
	// h-index retweet
	// h-index favorite daily
	// h-index favorite

	public float getReplyRatio() {
		return replyRatio;
	}

	public void setReplyRatio(float replyRatio) {
		this.replyRatio = replyRatio;
	}

	public float getInfluenceMetric() {
		return influenceMetric;
	}

	public void setInfluenceMetric(float influenceMetric) {
		this.influenceMetric = influenceMetric;
	}
}
