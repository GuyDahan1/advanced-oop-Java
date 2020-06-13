package Graphics;

import javax.swing.*;

/**
 * Creates a JDialog object.
 */
public class ActionMessageDialog {

    //just a test message when competition is over
    public static void ShowVictoryDialog(CompetitionFrame frame, String goldName, String silverName, String bronzeName) {
        JOptionPane.showMessageDialog(frame,
                "first place\t" + goldName + "\nsecond place\t" + silverName + "\nfirst place\t" + bronzeName,
                "VICTORY MESSAGE",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Creates JDialog to get the user input whether or not a new competition should be chosen.
     *
     * @param frame -A given CompetitionFrame on which the JDialog will be created.
     * @return the chosen answer.
     **/
    public static int createClrCompetitionDialog(CompetitionFrame frame) {
        Object[] options = {"NO!", "Yes, please"};
        return JOptionPane.showOptionDialog(frame,
                "Are you sure you want to chose new competition ?",
                "NEW COMPETITION ?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
    }

    /**
     * Creates JDialog to get the chosen animal that should be cleared.
     *
     * @param frame   -A given CompetitionFrame on which the JDialog will be created.
     * @param options - A given list of existing Animal options.
     * @return The chosen Animal that will be cleared.
     */
    public static String createClrAnimalDialog(CompetitionFrame frame, Object[] options) {
        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose animal to delete :", "Clear",
                JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
    }

    /**
     * Creates JDialog to get the chosen competition type.
     *
     * @param frame -A given CompetitionFrame on which the JDialog will be created.
     * @return The chosen the chosen competition type.
     */
    public static String chooseCompTypeDialog(CompetitionFrame frame) {
        ImageIcon imageIcon = frame.getImageIcon("Icons/competition.jpg");
        Object[] options = {"Water animals", "Air animals", "Terrestrial animals"};

        return (String) JOptionPane.showInputDialog(
                frame, "Choose competition type", "Competition",
                JOptionPane.PLAIN_MESSAGE, imageIcon, options, "Terrestrial animals");
    }

}
