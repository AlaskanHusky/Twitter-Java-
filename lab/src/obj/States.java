package obj;

import java.util.ArrayList;
import java.util.List;

public class States {

    private List<State> listOfStates = new ArrayList<>();

    public States() {}
    public States(List<State> listOfStates) {
        this.listOfStates = listOfStates;
    }

    public List<State> getStates() {
        return listOfStates;
    }

    public void add(State st)
    {
        listOfStates.add(st);
    }
    public void print() {
        for(State st : listOfStates)
        {
            st.output();
        }
    }
    public State getStateByName(String name) {
        for(State st : listOfStates)
        {
            if (st.getName().equals(name))
                return st;
        }
        return null;
    }
}
