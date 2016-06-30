import draw.Frame;
import io.Reader;
import multithreading.*;
import obj.*;
import parsers.*;
import reports.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    private static String tweetsFilename = "all_tweets.txt";
    private static String sentimentsFilename = "sentiments.csv";
    private static String statesFilename = "states.json";
    private static int begin = 0;
    private static int end = 10;
    private static int report = 0;

    private static void executionWithoutMultiThreading () {
        //Time Counter
        long startTime = System.currentTimeMillis();
        // Tweets
        ArrayList<String> list = Reader.StringsReader(tweetsFilename, begin, end);
        TweetsParser tweetsParser = new TweetsParser(list);
        Tweets tweetList = new Tweets();
        for (int i = 0; i < list.size(); i++) {
            tweetList.add(tweetsParser.parse(list.get(i)));
        }
        tweetList.print();
        // Sentiments
        list = Reader.StringsReader(sentimentsFilename, begin, end);
        SentimentsParser sentimentsParser = new SentimentsParser(list);
        Sentiments sentimentList = new Sentiments();
        for (int i = 0; i < list.size(); i++) {
            sentimentList.add(sentimentsParser.parse(list.get(i)));
        }
        sentimentList.print();
        //States
        list = Reader.StringsReader(statesFilename, 0, 1);
        StateParser stateParser = new StateParser(list);
        States stateList;
        stateList = stateParser.parse(list.get(0));
        stateList.print();
        // Elapsed Time
        long endTime  = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Elapsed time: " + totalTime);
        // First Report
        IReport<Tweets> tweetReport = new FirstReport();
        FirstReportResult tweetsWithHashtag = tweetReport.getReport(new Parameters(tweetList, "nothingwrongwith"));
        System.out.println("##########Tweets with specified hashtag##########");
        tweetsWithHashtag.print();
        // Second report
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm kk:mm:ss");
        Date startDate = null, endDate = null;
        try {
            startDate = sdf.parse("2011-04-13 19:02:50");
            endDate = sdf.parse("2011-09-04 14:06:46");
        }
        catch (ParseException ex) {
            ex.printStackTrace();
        }
        IReport<Double> sentimentReport = new SecondReport();
        SecondReportResult sentimentReportResult = sentimentReport.getReport(new Parameters(tweetList, sentimentList, startDate, endDate));
        System.out.print("Emotional Coloring Tweets: ");
        sentimentReportResult.print();
        // Third report
        IReport<State> stateReport = new ThirdReport();
        ThirdReportResult stateReportResult = stateReport.getReport(new Parameters(stateList, tweetList, startDate, endDate));
        System.out.println(stateReportResult);
        // Map of states
        //Frame testWindow = new Frame(stateList);
    }
    private static void executionWithMultiThreading () {
        //Time Counter
        long startTime = System.currentTimeMillis();
        // Tweets
        ReaderThread readerThread1 = new ReaderThread(tweetsFilename, begin, end);
        // First thread - Read from file
        Thread th = new Thread(readerThread1);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> tweetsList = readerThread1.getValue();
        TweetsParser tweetsParser = new TweetsParser(tweetsList);
        TweetParserThread tweetParserThread = new TweetParserThread(tweetsList, tweetsParser);
        // Second thread - Parse lines
        Thread th2 = new Thread(tweetParserThread);
        th2.start();
        try {
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Tweets tweetList = tweetParserThread.getValue();
        tweetList.print();
        // Sentiments
        ReaderThread readerThread2 = new ReaderThread(sentimentsFilename, begin, end);
        // First thread - Read from file
        Thread th3 = new Thread(readerThread2);
        th3.start();
        try {
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> stringSentimentsList = readerThread2.getValue();
        SentimentParserThread sentimentParserThread = new SentimentParserThread(stringSentimentsList);
        // Second thread - Parse lines
        Thread th4 = new Thread(sentimentParserThread);
        th4.start();
        try {
            th4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Sentiments tweetSentimentsList = sentimentParserThread.getValue();
        tweetSentimentsList.print();
        // States
        ReaderThread readerThread3 = new ReaderThread(statesFilename, begin, 1);
        // First thread - Read from file
        Thread t5 = new Thread(readerThread3);
        t5.start();
        try {
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<String> stringStatesList = readerThread3.getValue();
        StateParserThread stateParserThread = new StateParserThread(stringStatesList);
        // Second thread - Parse lines
        Thread t6 = new Thread(stateParserThread);
        t6.start();
        try {
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        States stateList = stateParserThread.getValue();
        stateList.print();
        // Elapsed Time
        long endTime  = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Elapsed time: " + totalTime);
    }
    public static void main(String[] args) {
        /*
        String filename = args[0]; //This file is read out
        int begin = Integer.parseInt(args[1]); //The string, which we start reading
        int end = Integer.parseInt(args[2]); // Counts of string
        int report = Integer.parseInt(args[3]); // Number of report
        */
        executionWithoutMultiThreading();
        //executionWithMultiThreading();
    }
}