package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a JDialog frame to add animal to the competition.
 */
public class AddAnimalDialog extends JDialog implements ActionListener {
    GridBagConstraints gbc = new GridBagConstraints();

    String[] waterAnimals = {"dolphin1", "dolphin2", "dolphin3", "whale1", "whale2", "alligator1", "alligator2"};
    String[] airAnimals = {"eagle1", "eagle2", "eagle3", "pigeon1"};
    String[] terAnimals = {"dog1", "dog2", "dog3", "dog4", "dog5", "dog6", "cat1", "cat2", "snake1", "snake2", "snake3", "alligator3"};

    private final JComboBox<String> animals_jcb = new JComboBox<>();

    private final JLabel imgLabel;
    JLabel nameLabel = new JLabel("Name :");

    JTextField nameTextField = new JTextField(20);

    private final JButton nameBtn = new JButton("Save Name");
    private final JButton startCompBtn = new JButton("Start Competition");
    private final JButton createBtn = new JButton("Add Animal");

    private String animalName; // name of animal , example:REX
    String animalFamilyType; // king of animal, example: Air Animal
    String animalKind; // kind of animal , example: Dog
    private String animalImgPath; //animal image path, example:

    /**
     * AddAnimalDialog constructor.
     *
     * @param frame - A given CompetitionFrame frame on which the JDialog is created.
     * @param name  - A given name to the JDialog's name.
     */
    public AddAnimalDialog(JFrame frame, String name) {
        super(frame, name);
        imgLabel = new JLabel();

        this.setLayout(new GridBagLayout());
        CompetitionFrame mainFrame = (CompetitionFrame) SwingUtilities.getWindowAncestor(this);
        animalFamilyType = mainFrame.getChosenCompetition();

        JLabel l2 = new JLabel("Choose from " + animalFamilyType + "s :");

        gbc.gridy = 0;
        this.add(l2, gbc);

        chooseAddItemsToJcb();
        gbc.gridy = 1;

        this.add(animals_jcb, gbc);

        animals_jcb.addActionListener(this);
        nameBtn.addActionListener(this);
        createBtn.addActionListener(this);

        this.setSize(300, 350);

        this.setVisible(true);
    }


    /**
     * Detects a preformed action on this frame's components.
     *
     * @param e - A given preformed action.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == animals_jcb)
            secondJcbActivated();
        else if (e.getSource() == nameBtn)  //if name button is activated
            lastState();

        this.validate();
    }

    /**
     * Resets the JDialog settings to the previous state.
     */
    public void lastState() {
        animalName = nameTextField.getText();
        gbc.gridy = 10;
        add(new JLabel(), gbc);

        gbc.gridy = 12;
        createBtn.setBackground(Color.CYAN);
        add(createBtn, gbc);

        gbc.gridy = 13;
        add(startCompBtn, gbc);
    }

    /**
     * Adds an image choice JComboBox.
     */
    private void secondJcbActivated() {
        showImage();
        gbc.gridy = 8;
        this.add(nameLabel, gbc);

        gbc.gridy = 9;
        this.add(nameTextField, gbc);

        gbc.gridy = 10;
        this.add(nameBtn, gbc);
    }

    /**
     * Create the JComboBox option according to user's choice.
     */
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
        this.animalKind = (String) animals_jcb.getSelectedItem();
        this.animalImgPath = IDrawable.PICTURE_PATH + animalKind + "E.png";
        try {
            ImageIcon imageIcon = new ImageIcon(animalImgPath); // load the image to a imageIcon
            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newImg);// transform it back
            imgLabel.setIcon(imageIcon);
            gbc.gridy = 5;
            this.add(imgLabel, gbc);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Adds the options to the JComboBox.
     *
     * @param arr - A given array of options.
     */
    public void addItemJcb(String[] arr) {
        for (String s : arr)
            animals_jcb.addItem(s);
    }

    public JButton getStartCompBtn() {
        return startCompBtn;
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public String getAnimalFamilyType() {
        return animalFamilyType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalKind() {
        return animalKind;
    }

    public String getAnimalImgPath() {
        return animalImgPath;
    }
}