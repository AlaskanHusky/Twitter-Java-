package reports;

import obj.State;

public class ThirdReportResult {
    State maxState;

    public ThirdReportResult(State maxState) {
        this.maxState = maxState;
    }

    public void print() {
        System.out.println(maxState.getName());
    }

    @Override
    public String toString() {
        return "Name of state: " + maxState + "\n";
    }
}
