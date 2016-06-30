package obj;

import java.util.ArrayList;
import java.util.List;

public class Tweets {

    private List<Tweet> listOfTweets = new ArrayList<>();

    public Tweets() {}
    public Tweets(List<Tweet> listOfTweets)
    {
        this.listOfTweets = listOfTweets;
    }

    public List<Tweet> getTweets() {
        return listOfTweets;
    }

    public void add(Tweet tweet)
    {
        listOfTweets.add(tweet);
    }
    public void print() {
        for(Tweet tweet : listOfTweets)
        {
            tweet.output();
        }
    }
    public Boolean contains(Tweet tweet)
    {
        return listOfTweets.contains(tweet);
    }

}
