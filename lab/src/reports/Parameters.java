package reports;

import obj.Sentiments;
import obj.States;
import obj.Tweets;

import java.util.Date;

public class Parameters {

    private Tweets listOfTweets;
    private Sentiments listOfSentiments;
    private States listOgStates;
    private Date startDate;
    private Date endDate;
    private String hashtag;

    public Parameters() {}
    public Parameters(Tweets listOfTweets, String hashtag) {
        this.listOfTweets = listOfTweets;
        this.hashtag = hashtag;
    }
    public Parameters(Tweets listOfTweets, Sentiments listOfSentiments, Date startDate, Date endDate) {
        this.listOfTweets = listOfTweets;
        this.listOfSentiments = listOfSentiments;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Parameters(States listOgStates, Tweets listOfTweets, Date startDate, Date endDate) {
        this.listOgStates = listOgStates;
        this.listOfTweets = listOfTweets;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Tweets getTweets() {
        return listOfTweets;
    }
    public States getStates() {
        return listOgStates;
    }
    public Sentiments getSentiments() {
        return listOfSentiments;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public String getHashtag() {
        return hashtag;
    }


}
