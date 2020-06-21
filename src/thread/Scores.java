package thread;

import java.util.*;

public class Scores {

    private final Map<String, Date> scores;//queue that contain STRING<>DATE
    private Vector<String> stringVector = new Vector<>();

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<>());
    }

    public synchronized void addRegularTour(String name) {
        Date date = new Date();
        scores.put(name, date);
        stringVector.add(name + " at " + date.toString());
//        scoresArray.add(scores.toString().replace("="," finished at "));
//        System.out.println(scores.toString().replace("="," finished at "));
    }

    public Map<String, Date> getScores() {
        return scores;
    }

    public Vector<String> getStringVector() {
        return stringVector;
    }

}
