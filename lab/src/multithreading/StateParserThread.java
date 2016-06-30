package multithreading;

import obj.State;
import obj.States;
import parsers.IParser;
import parsers.StateParser;

import java.util.ArrayList;

public class StateParserThread implements Runnable {

    ArrayList<String> stringStatesList = new ArrayList<>();
    States stateList = new States();

    public StateParserThread(ArrayList<String> stringStatesList) {
        this.stringStatesList = stringStatesList;
    }

    public States getValue() {
        return stateList;
    }

    @Override
    public void run() {
        IParser<State> stateParser = new StateParser(stringStatesList);
        stateList = stateParser.parse(stringStatesList.get(0));
    }
}

