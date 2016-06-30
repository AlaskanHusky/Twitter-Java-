package reports;

import obj.Sentiment;
import obj.Sentiments;
import obj.Tweet;
import obj.Tweets;

import java.util.Date;

public class SecondReport implements IReport<Double> {

    private Tweets listOfTweets = new Tweets();
    private Sentiments listOfSentiments = new Sentiments();
    private Date startDate;
    private Date endDate;

    public SecondReport() {}
    public SecondReport(Tweets listOfTweets, Sentiments listOfSentiments, Date startDate, Date endDate) {
        this.listOfTweets = listOfTweets;
        this.listOfSentiments = listOfSentiments;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public SecondReportResult getReport(Parameters params) {
        Double summaryWeight = 0.0;

        for (Tweet tweet : params.getTweets().getTweets()) {
            if(tweet.getDateTime().after(params.getStartDate()) && tweet.getDateTime().before(params.getEndDate())) {
                summaryWeight += getEmotionalWeightOfTweet(tweet, params);
            }
        }
        return new SecondReportResult(summaryWeight);
    }
    public Double getEmotionalWeightOfTweet(Tweet tweet, Parameters params) {
        Double weight = 0.0;

        String[] words = tweet.getMessage().split("(\\?|\\.|,|!|\\s+)");

        for (Sentiment st : params.getSentiments().getSentiments()) {
            for (String word : words) {
                if (st.getWord().equals(word)) { // reverse
                    weight += st.getValue();
                }
            }
        }
        return weight;
    }

}
