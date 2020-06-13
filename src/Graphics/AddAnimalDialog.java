package Graphics;

import animals.gen;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddAnimalDialog extends JDialog implements ActionListener, ChangeListener {

    String[] waterAnimals = {"dolphin1", "dolphin2", "dolphin3", "whale1", "whale2", "alligator1", "alligator2"};
    String[] airAnimals = {"eagle1", "eagle2", "eagle3", "pigeon1"};
    String[] terAnimals = {"dog1", "dog2", "dog3", "dog4", "dog5", "dog6", "cat1", "cat2", "snake1", "snake2", "snake3", "alligator3"};


    private JPanel addAnimalPanel;
    private JComboBox animalDesignJcb;

    private JRadioButton femaleRadioButton;
    private JRadioButton maleRadioButton;
    private JRadioButton hermaphroditeRadioButton;


    private JLabel speedLabel;
    private JSlider slider1;
    private JLabel Enr;

    private JLabel titleAddAnimalDialog;
    private JLabel animalNameLabel;
    private JLabel genderLabel;
    private JLabel energyConLabel;
    private JLabel imgLabelE;

    private JButton okButtonAddAnimal;
    private JButton cancelButtonAddAnimal;
    private JTextField animalNameTextField;


    private String animalFamilyType;
    private String animalKind;
    private String animalImgPath;

    private int speed;
    private int energyConsumpt;
    private String animalName;
    private gen animalGen;

    public AddAnimalDialog(Frame frame, String name) {
        super(frame, name);
        setContentPane(addAnimalPanel);
        CompetitionFrame mainFrame = (CompetitionFrame) SwingUtilities.getWindowAncestor(this);


        ///slider setup///
        slider1.addChangeListener(this);
        slider1.setMajorTickSpacing(14);
        slider1.setMinorTickSpacing(1);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        ///slider setup///


        animalDesignJcb.addActionListener(this);
        animalGen = gen.Male;


        animalFamilyType = animalFamilyType == null ? "Water animals" : mainFrame.getChosenCompetition();
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
            Image newImg = image.getScaledInstance(100, 80, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newImg);// transform it back
            imgLabelE.setIcon(imageIcon);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(cancelButtonAddAnimal))
            dispose();
        else if (e.getSource().equals(animalDesignJcb))
            showImage();
        else if (e.getSource().equals(femaleRadioButton))
            animalGen = gen.Female;
        else if (e.getSource().equals(maleRadioButton))
            animalGen = gen.Male;
        else if (e.getSource().equals(hermaphroditeRadioButton))
            animalGen = gen.Hermaphrodite;
        validate();
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            energyConsumpt = (int) source.getValue() * 10;
            speed = (int) source.getValue();
            speedLabel.setText(String.valueOf(speed +"Km/h"));
            energyConLabel.setText(String.valueOf(energyConsumpt+ "E/h"));
        }
    }

    public String getAnimalName() {
        return animalName;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEnergyConsumpt() {
        return energyConsumpt;
    }

    public String getAnimalKind() {
        return animalKind;
    }

    public JButton getOkButtonAddAnimal() {
        return okButtonAddAnimal;
    }

    public gen getAnimalGen() {
        return animalGen;
    }

    public JTextField getAnimalNameTextField() {
        return animalNameTextField;
    }
}
