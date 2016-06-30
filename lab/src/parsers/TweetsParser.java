package parsers;

import obj.Coordinates;
import obj.Tweet;
import obj.Tweets;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TweetsParser implements IParser<Tweet> {

    private ArrayList<String> lines;
    private Tweets listOfTweets = new Tweets();

    public TweetsParser(ArrayList<String> lines) {
        this.lines = lines;
    }

    public Tweets getList()
    {
        return listOfTweets;
    }

    @Override
    public Tweet parse(String str) {

        Coordinates loc;
        StringBuffer buff = new StringBuffer();

        double x = 0;
        double y = 0;
        Date dateTime = null;
        String text;

        String regex = "\\d{2}\\.\\d+,";
        Matcher matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find()) {
            buff.append(str.substring(matcher.start(), matcher.end() - 1));
            x = Double.parseDouble(buff.toString());
            buff.delete(0, buff.length());
        }

        regex = "\\-?\\d{2,3}\\.\\d+";
        matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find()) {
            buff.append(str.substring(matcher.start(), matcher.end()));
            y = Double.parseDouble(buff.toString());
            buff.delete(0, buff.length());
        }

        loc = new Coordinates(x, y);

        regex = "(\\d{4}(\\-\\d{2}){2}) (\\d{2}(:\\d{2}){2})";
        matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find()) {
            buff.append(str.substring(matcher.start(), matcher.end()));
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm kk:mm:ss");
        try {
            dateTime = dateFormat.parse(buff.toString());
        } catch (ParseException e) {
            e.getMessage();
        }
        buff.delete(0, buff.length());
        regex = "[a-zA-Z#@].*";
        matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find()) {
            buff.append(str.substring(matcher.start(), matcher.end()));
        }

        text = buff.toString();
        buff.delete(0, buff.length());

        return new Tweet(loc, dateTime, text);
    }
}
