package thread;

import java.util.*;

public class Scores {

    private final Map<String, Date> scores;//queue that contain STRING<>DATE
    private Vector<String> stringVector = new Vector<>();
    private int index = 0;

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }

    public synchronized void addRegularTour(String name) {
        Date date = new Date();
        scores.put(name, date);
        if (index == 0)
            stringVector.add(name + " finished FIRST at " + date.toString());
        else if (index == 1)
            stringVector.add(name + " finished SECOND at " + date.toString());
        else if (index == 2)
            stringVector.add(name + " finished THIRD at " + date.toString());
        else
            stringVector.add(name + " finished at " + date.toString());

        index++;
    }

    public Map<String, Date> getScores() {
        return scores;
    }

    public Vector<String> getStringVector() {
        return stringVector;
    }

}
