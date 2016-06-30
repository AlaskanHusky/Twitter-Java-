package obj;

import java.util.ArrayList;
import java.util.List;

public class Sentiments {

    private List<Sentiment> listOfSentiments = new ArrayList<Sentiment>();

    public Sentiments() {}
    public Sentiments (List<Sentiment> listOfSentiments) {
        this.listOfSentiments = listOfSentiments;
    }

    public void print() {
        for(Sentiment st : listOfSentiments) {
            System.out.println(st);
        }
    }
    public void add(Sentiment tweetSentiment) {
        listOfSentiments.add(tweetSentiment);
    }
    public List<Sentiment> getSentiments() {
        return listOfSentiments;
    }
}
