package multithreading;

import obj.Tweets;
import parsers.TweetsParser;

import java.util.ArrayList;

public class TweetParserThread implements Runnable {

    Tweets listOfTweets = new Tweets();
    ArrayList<String> lines = new ArrayList<>();
    TweetsParser twParser;

    public TweetParserThread(ArrayList<String> lines, TweetsParser twParser) {
        this.lines = lines;
        this.twParser = twParser;
    }

    public Tweets getValue() {
        return listOfTweets;
    }

    @Override
    public void run() {
        TweetsParser tweetsParser = new TweetsParser(lines);
        for (int i = 0; i < lines.size(); i++) {
            listOfTweets.add(tweetsParser.parse(lines.get(i)));
        }
    }
}
