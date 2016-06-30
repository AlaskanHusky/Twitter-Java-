package obj;

import java.util.ArrayList;
import java.util.List;

public class State {

    private String name;
    private List<Coordinates> location = new ArrayList<Coordinates>();

    public State() {}
    public State(String name, List<Coordinates> location) {
        this.name = name;
        this.location = location;
    }

    public List<Coordinates> getCoordinates() {
        return location;
    }
    public String getName() {
        return name;
    }

    public void output () {
        System.out.println("State: " + name);
        for(Coordinates tweetLocation : location)
        {
            System.out.println("[" + tweetLocation.getX() + ", " + tweetLocation.getY() + "]");
        }
    }
}
