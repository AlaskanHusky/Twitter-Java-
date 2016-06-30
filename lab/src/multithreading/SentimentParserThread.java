package multithreading;

import obj.Sentiment;
import obj.Sentiments;
import parsers.IParser;
import parsers.SentimentsParser;

import java.util.ArrayList;

public class SentimentParserThread implements Runnable {

    ArrayList<String> stringSentimentsList = new ArrayList<>();
    Sentiments tweetSentimentsList = new Sentiments();

    public SentimentParserThread(ArrayList<String> stringSentimentsList) {
        this.stringSentimentsList = stringSentimentsList;
    }

    @Override
    public void run() {
        IParser<Sentiment> sentimentsParser = new SentimentsParser(stringSentimentsList);
        for (int i = 0; i < stringSentimentsList.size(); i++) {
            tweetSentimentsList.add(sentimentsParser.parse(stringSentimentsList.get(i)));
        }
    }

    public Sentiments getValue() {
        return tweetSentimentsList;
    }
}
