package thread;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;

public class Scores {

    private Map<String, Date> scores;//queue that contain STRING<>DATE

    public Scores() {
        scores = Collections.synchronizedMap(new HashMap<String, Date>());
    }

    public synchronized void add(String name) {
        Date date = new Date();
        scores.put(name, date);
        System.out.println(name + " " + date.toString() + " Scored added" );
    }

    public Map<String, Date> getScores() {
        return scores;
    }

}
