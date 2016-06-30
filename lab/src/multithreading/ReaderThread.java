package multithreading;

import java.util.ArrayList;

import static io.Reader.StringsReader;

public class ReaderThread implements Runnable {
    ArrayList<String> lines = new ArrayList<String>();
    private String filename;
    private int begin;
    private int end;

    public ReaderThread(String filename, int begin, int end) {
        this.filename = filename;
        this.begin = begin;
        this.end = end;
    }

    public ArrayList<String> getValue() {
        return lines;
    }

    @Override
    public void run() {
        lines = StringsReader(filename, begin, end);
    }
}
