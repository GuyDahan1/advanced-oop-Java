package thread;

public class Referee implements Runnable{

    public Referee(String name , Scores scores){
        scores.add(name);
    }

    @Override
    public void run() {

    }
}
