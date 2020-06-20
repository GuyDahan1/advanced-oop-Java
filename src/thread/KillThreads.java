package thread;

import java.util.Vector;

public class KillThreads {

    private Vector<Thread> threads = new Vector<>();

    public KillThreads(){

    }

    public void addThread(Thread t){
        threads.add(t);
    }

    public void killAllThreads(){
        for (int i = 0; i <threads.size() ; i++) {
            threads.get(i).stop();
        }
    }
}
