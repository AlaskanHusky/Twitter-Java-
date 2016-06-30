package reports;

import obj.Tweet;
import obj.Tweets;

public class FirstReport implements IReport<Tweets> {

    private Tweets listOfTweets = new Tweets();
    private String hashtag;

    public FirstReport() {}
    public FirstReport(Tweets listOfTweets, String hashtag) {
        this.listOfTweets = listOfTweets;
        this.hashtag = hashtag;
    }

    @Override
    public FirstReportResult getReport(Parameters params) {
        Tweets tweetsWithHashtag = new Tweets();

        for (Tweet tw : params.getTweets().getTweets()) {
            for (String s : tw.getMessage().split("(\\?|\\.|,|!|\\s+)")) {
                if (s.contains(params.getHashtag())) {
                    if (!tweetsWithHashtag.contains(tw)) {
                        tweetsWithHashtag.add(tw);
                    }
                }
            }
        }
        return new FirstReportResult(tweetsWithHashtag);
    }
}
