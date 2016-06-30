package reports;

import obj.Coordinates;
import obj.IntegerPoint;
import obj.State;
import obj.Tweet;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdReport implements IReport<State> {

    private final Integer MULTI = 1000000;

    public ThirdReport() {}

    @Override
    public ThirdReportResult getReport(Parameters params)
    {
        Integer allTweetsCount = 0;
        Map<String, Integer> statesCounter = new HashMap<String, Integer>();

        for (State st : params.getStates().getStates()) {
            Integer count = 0;
            for(Tweet tw : params.getTweets().getTweets())
            {
                if(tw.getDateTime().after(params.getStartDate()) && tw.getDateTime().before(params.getEndDate())) {
                    Polygon polygon = new Polygon();

                    if (contains(st, toIntegerPoints(tw.getCoordinates()), polygon)) {
                        count++;
                        allTweetsCount++;
                    }
                }
            }
            statesCounter.put(st.getName(), count);
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String,Integer> entry : statesCounter.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        return new ThirdReportResult(params.getStates().getStateByName(maxEntry.getKey()));
    }
    private IntegerPoint toIntegerPoints(Coordinates doublePoints) {
        return (new IntegerPoint((int)(doublePoints.getX())*MULTI, (int) (doublePoints.getY()*MULTI)));
    }
    private List<IntegerPoint> getIntegerPoints(List<Coordinates> loc) {
        List<IntegerPoint> out = new ArrayList<IntegerPoint>();

        for(Coordinates doubleCoord : loc) {
            out.add(new IntegerPoint((int) doubleCoord.getX()*MULTI, (int) doubleCoord.getY()*MULTI));
        }

        return out;
    }
    public boolean contains(State st, IntegerPoint test, Polygon polygon) {

        for (IntegerPoint intPoint : getIntegerPoints(st.getCoordinates())) {
            polygon.addPoint(intPoint.getX(), intPoint.getY());
        }
        if (polygon.contains(test.getY(), test.getX())) {
            return true;
        }
        return false;
    }

}
