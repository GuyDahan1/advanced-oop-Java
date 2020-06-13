package designPatterns;

import Graphics.CompetitionFrame;

public class MainFrameSingelton {
    private static CompetitionFrame frame=null;

    private MainFrameSingelton(){
        System.out.println("Creating The Main Frame");
    }

    public static synchronized CompetitionFrame getInstance(){
        if(frame==null){
            frame = new CompetitionFrame();
        }
        return frame;
    }
}
