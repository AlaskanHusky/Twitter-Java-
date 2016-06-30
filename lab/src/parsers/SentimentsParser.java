package parsers;

import obj.Sentiment;
import obj.Sentiments;

import java.util.ArrayList;

public class SentimentsParser implements IParser<Sentiment> {

    private ArrayList<String> lines;
    private Sentiments listOfSentiments = new Sentiments();

    public SentimentsParser(ArrayList<String> lines) {
        this.lines = lines;
    }

    public Sentiments getSentiments()
    {
        return listOfSentiments;
    }

    @Override
    public Sentiment parse(String str) {
        String[] result = str.split(",");
        Sentiment st = new Sentiment(result[0], Double.parseDouble(result[1]));
        return st;
    }
}