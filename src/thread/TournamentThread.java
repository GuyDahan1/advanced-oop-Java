package thread;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import static Graphics.CompetitionFrame.centreWindow;


public class TournamentThread implements Runnable {

    private final Scores scores;
    private final AtomicBoolean startSignal;
    AnimalThread[][] animalsArray;
    private static int count = 3;
    String[][] arrayOfScore;
    private final int index;

    public TournamentThread(AnimalThread[][] animalsThreads, Scores scores, AtomicBoolean startSignal, int index) {
        this.animalsArray = animalsThreads;
        this.scores = scores;
        this.startSignal = startSignal;
        this.index = index;
    }

    public void startCompetitionDialog() {
        JFrame f = new JFrame();
        f.setSize(new Dimension(150, 150));
        f.setUndecorated(true);
        centreWindow(f);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel();

        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                f.add(p);
                p.add(lbl);

                if (count >= 0) {
                    f.setVisible(true);
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/start" + count-- + ".png")); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it

                    Image newImg;
                    if (count + 1 != 0)
                        newImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH); // scale it the smooth way
                    else
                        newImg = image.getScaledInstance(220, 220, Image.SCALE_SMOOTH); // scale it the smooth way

                    imageIcon = new ImageIcon(newImg);// transform it back

                    lbl.setIcon(imageIcon);
                    p.repaint();
                } else {
                    cancel();
                    f.dispose();
                }
            }
        }, 1000, 1000);

        synchronized (this) {
            notify();
        }
    }

    public void run() {
        startCompetitionDialog();

        synchronized (this) {
            try {
                this.wait(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }  // todo -  fix bug
        System.out.println("here 85 line");

        synchronized (startSignal) {
            if (!startSignal.get()) {
                System.out.println("TourThread startSignal True");
                this.startSignal.set(true);
            }
        }

        System.out.println("here 94 line");

//        arrayOfScore = new String[animalsArray.length][];
        for (int j = 0; j < animalsArray[index].length; j++) {
            synchronized (animalsArray[index][j]) {
                animalsArray[index][j].notifyAll();
                System.out.println("Notify TourThread");
            }
        }

        System.out.println("here 105 line");

        arrayOfScore[index] = new String[animalsArray[index].length];
        for (int j = 0; j < animalsArray[index].length; j++)
            arrayOfScore[index][j] = scores.getScores().toString();
    }
}
