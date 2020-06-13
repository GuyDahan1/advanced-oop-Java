package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAnimalDialogT extends JDialog implements ActionListener {

    String[] waterAnimals = {"dolphin1", "dolphin2", "dolphin3", "whale1", "whale2", "alligator1", "alligator2"};
    String[] airAnimals = {"eagle1", "eagle2", "eagle3", "pigeon1"};
    String[] terAnimals = {"dog1", "dog2", "dog3", "dog4", "dog5", "dog6", "cat1", "cat2", "snake1", "snake2", "snake3", "alligator3"};


    private JPanel addAnimalPanel;
    private JTextField animalNameTextField;
    private JComboBox animalDesignJcb;
    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JSlider slider1;
    private JLabel Enr;
    private JRadioButton hermaphroditeRadioButton;
    private JLabel speedLabel;
    private JLabel addAnimalDialogLabel;
    private JLabel animalNameLabel;
    private JLabel genderLabel;
    private JLabel energyConLabel;
    private JLabel imgLabel;
    private JButton okButtonAddAnimal;
    private JButton cancelButtonAddAnimal;

    private String animalFamilyType;
    private String animalKind;
    private String animalImgPath;

    public AddAnimalDialogT(Frame frame , String name){
        super(frame,name);
        setContentPane(addAnimalPanel);
        CompetitionFrame mainFrame = (CompetitionFrame) SwingUtilities.getWindowAncestor(this);
        animalFamilyType = mainFrame.getChosenCompetition();

        chooseAddItemsToJcb();
        showImage();

        setVisible(true);
        pack();
    }



    /**
     * Adds the options to the JComboBox.
     *
     * @param arr - A given array of options.
     */
    public void addItemJcb(String[] arr) {
        for (String s : arr)
            animalDesignJcb.addItem(s);
    }

    private void chooseAddItemsToJcb() {
        if (animalFamilyType.contains("Terr")) {
            addItemJcb(terAnimals);
        } else if (animalFamilyType.contains("Air")) {
            addItemJcb(airAnimals);
        } else if (animalFamilyType.contains("Water")) {
            addItemJcb(waterAnimals);
        }
    }


    /**
     * Shows the image that match the user's choices.
     */
    private void showImage() {
        this.animalKind = (String) animalDesignJcb.getSelectedItem();
        this.animalImgPath = IDrawable.PICTURE_PATH + animalKind + "E.png";
        try {
            ImageIcon imageIcon = new ImageIcon(animalImgPath); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newImg);// transform it back
            imgLabel.setIcon(imageIcon);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
