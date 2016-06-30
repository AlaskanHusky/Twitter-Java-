package parsers;

import obj.Coordinates;
import obj.State;
import obj.States;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateParser implements IParser<State> {

    private ArrayList<String> lines;
    private ArrayList<State> listOfStates = new ArrayList<State>();

    public StateParser(ArrayList<String> lines) {
        this.lines = lines;
    }

    public static ArrayList<Coordinates> splitCoordinates(String input) {
        Coordinates location;
        ArrayList<Coordinates> coords = new ArrayList<>();

        double x, y;

        String[] result = input.split(",");
        for (int i = 0; i < result.length; i += 2) {
            if (i != result.length - 1) {
                x = Double.parseDouble(result[i].replaceAll("\"[A-Z]{2}\":", "").replaceAll("\\[", ""));
                y = Double.parseDouble(result[i + 1].replaceAll("\\s", "").replaceAll("\\]", ""));
                location = new Coordinates(x, y);
                coords.add(location);
            }
            else {
                break;
            }
        }
        return coords;
    }

    @Override
    public States parse(String str) {
        State st;
        States tweetsLocation = new States();
        ArrayList<Coordinates> coords;

        String name;
        String result;

        String nRegex = "[A-Z]{2}";
        String regex = "\"[A-Z]{2}\":[0-9\\.\\[\\],\\s\\-]*";
        Matcher nMatcher = Pattern.compile(nRegex).matcher(str);
        Matcher matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find() && nMatcher.find()) {
            result = matcher.group();
            name = nMatcher.group();
            coords = splitCoordinates(result);
            st = new State(name, coords);
            tweetsLocation.add(st);
        }
        return tweetsLocation;
    }
}
