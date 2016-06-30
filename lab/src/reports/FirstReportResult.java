package reports;

import obj.Tweet;
import obj.Tweets;

public class FirstReportResult {
    private Tweets tweetsWithHashtag;

    public FirstReportResult() {}
    public FirstReportResult(Tweets tweetsWithHashtag) {
        this.tweetsWithHashtag = tweetsWithHashtag;
    }

    public void print() {
        for(Tweet tw : tweetsWithHashtag.getTweets()) {
            tw.output();
        }
    }
}
