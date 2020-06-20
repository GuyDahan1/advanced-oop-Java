package Graphics;

import animals.Animal;
import animals.gen;
import designPatterns.AbstractAnimalFactory;
import designPatterns.CompetitionSingleton;
import designPatterns.MainFrameSingelton;
import designPatterns.SpeciesFactory;
import mobility.Point;
import thread.CourierTournament;
import thread.RegularTournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * CompetitionFrame
 */
public class CompetitionFrame extends JFrame implements ActionListener {
    private final CompetitionPanel competitionPanel = new CompetitionPanel();
    private AddCompetition addCompetition;
    private static final CompetitionMenu competitionMenu = new CompetitionMenu();
    private AddAnimalDialog addAnimalDialog;
    private final int maxNonAirAnimal = 4;
    private final int maxAirAnimal = 5;

    static Point[] startPointWater;
    static Point[] endPointWater;
    static Point[] startPoint;
    static Point[] endPoint;

    private final Vector<Animal> animalVector = new Vector<>();
    private final Vector<Animal[]> animalGroupVector = new Vector<>();

    Vector<String> vectorString = new Vector<>();
    Vector<Object[]> tempData = new Vector<>();

    String[][] data;
    Vector<Object[]> competitionTableVector = new Vector<>();

    private int currentPosition = -1;
    private int currentTournament = 0;
    private int userChosenTour = 0;

    private Vector<String> chosenCompetition = new Vector<>();
    private final Vector<String> chosenTournament = new Vector<>();
    private final Vector<String> tourName = new Vector<>();
    private GameState gameState;
    private boolean firstTime = true;

    /**
     * CompetitionFrame constructor.
     */
    public CompetitionFrame() {
        super("CompetitionFrame");

        setLayout(new BorderLayout());

        startPointWater = new Point[maxNonAirAnimal];
        endPointWater = new Point[maxNonAirAnimal];
        startPoint = new Point[maxAirAnimal];
        endPoint = new Point[maxAirAnimal];

        initiateStartPoints();

        addButtonsToActionListener();

        add(competitionMenu, BorderLayout.PAGE_START);
        add(competitionPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(980, 800);
    }

    /**
     * Actives this CompetitionFrame's buttons to the addActionListener
     */
    private void addButtonsToActionListener() {
        competitionPanel.getCompetitionToolbar().getExitBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getInfoBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getEatBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getClearBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getStartBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getCompetitionBtn().addActionListener(this);

        gameState = GameState.CHOOSING_COMP_TYPE;
        updateBtnStatus();
    }

    /**
     * Sets the starting points of created animals.
     */
    public void initiateStartPoints() {
        int heightDiff = 150; //screen height difference
        int terStartY = 10; //terrestrial starting y
        int waterStartY = 90; //water starting y

        for (int i = 0; i < maxAirAnimal; i++) { //sets air & terrestrial animals points
            startPoint[i] = new Point(5, terStartY);
            endPoint[i] = new Point(840, terStartY);
            terStartY += heightDiff;
            if (i < maxNonAirAnimal) { //sets water animals points
                startPointWater[i] = new Point(90, waterStartY);
                endPointWater[i] = new Point(750, waterStartY);
                waterStartY += heightDiff - i * 2;
            }
        }
    }

    /**
     * @return the current index that the new animal should be positioned on.
     */
    public Point getPositionIndex() {
        Point animalPoint;

        if (chosenTournament.get(currentTournament).contains("Regular"))
            animalPoint = getRegularTourStartPoints();
        else
            animalPoint = getCourierTourStartPoints();

        return animalPoint;
    }

    private Point getRegularTourStartPoints() {
        Point animalPoint = null;

        currentPosition++;
        if (chosenCompetition.get(currentTournament).contains("Air") && currentPosition < maxAirAnimal) {
            if (currentPosition == maxAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPoint[currentPosition];

        } else if (chosenCompetition.get(currentTournament).contains("Water") && currentPosition < maxNonAirAnimal) {
            if (currentPosition == maxNonAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPointWater[currentPosition];

        } else if (chosenCompetition.get(currentTournament).contains("Terr") && currentPosition < maxNonAirAnimal) {
            if (currentPosition == maxNonAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPoint[currentPosition];
        }
        return animalPoint;
    }

    private Point getCourierTourStartPoints() {
        Point animalPoint = null;

        currentPosition++;
        if (chosenCompetition.get(currentTournament).contains("Air") && currentPosition < maxAirAnimal) {

            if (currentPosition == maxAirAnimal - 1)
                addCompetition.getAddAnimalButton().setEnabled(false);

            animalPoint = startPoint[currentPosition];

            if (currentPosition % 2 != 0)
                animalPoint = endPoint[currentPosition];

        } else if (chosenCompetition.get(currentTournament).contains("Water") && currentPosition < maxNonAirAnimal) {

            if (currentPosition == maxNonAirAnimal - 1)
                addCompetition.getAddAnimalButton().setEnabled(false);

            animalPoint = startPointWater[currentPosition];

            if (currentPosition % 2 != 0)
                animalPoint = endPointWater[currentPosition];


        } else if (chosenCompetition.get(currentTournament).contains("Terr") && currentPosition < maxNonAirAnimal) {

            if (currentPosition == maxNonAirAnimal - 1)
                addCompetition.getAddAnimalButton().setEnabled(false);

            animalPoint = startPoint[currentPosition];
            if (currentPosition % 2 != 0)
                animalPoint = endPoint[currentPosition];
        }

        return animalPoint;
    }

    /**
     * Detects a preformed action on this frame's components.
     *
     * @param e - A given preformed action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // exit button chosen
        if (e.getSource() == competitionPanel.getCompetitionToolbar().getExitBtn())
            System.exit(0);

            // info button chosen
        else if (e.getSource() == competitionPanel.getCompetitionToolbar().getInfoBtn())
            createInfoTable();

            // eat button chosen
        else if (e.getSource() == competitionPanel.getCompetitionToolbar().getEatBtn())
            feedChosenAnimal();

            // clear button chosen
        else if (e.getSource() == competitionPanel.getCompetitionToolbar().getClearBtn()) {
            clearCalled();

            // competition button chosen
        } else if (e.getSource() == competitionPanel.getCompetitionToolbar().getCompetitionBtn())
            competitionBtnAction();

            //create the Ok button when animalVector is empty / competition chosen
        else if (e.getSource() == addCompetition.getOkOrNewCompetitionBtn())
            if (animalVector.isEmpty())
                addCompetitionOkBtn();
            else
                addCompetitionNewBtn();

            //add animal button chosen
        else if (e.getSource() == addCompetition.getAddAnimalButton()) {
            addAnimalDialog = new AddAnimalDialog(this, "Add Animal ");
            centreWindow(addAnimalDialog);
            addAnimalDialog.getOkButtonAddAnimal().addActionListener(this);

            //if create button chosen
        } else if (e.getSource() == addAnimalDialog.getOkButtonAddAnimal())
            addAnimalDialogOkBtnAction();

            //if new competition button chosen
        else if (e.getSource() == addCompetition.getNewCompetitionButton())
            newCompetition();

            //if table button chosen
        else if (e.getSource().equals(addCompetition.getTableButton()))
            createCompetitionTable();

            //if start button chosen
        else if (e.getSource().equals(competitionPanel.getCompetitionToolbar().getStartBtn()))
            startTournament();

        validate();
        repaint();

        if (currentPosition == maxAirAnimal - 1 || currentPosition == maxNonAirAnimal - 1) {
            gameState = GameState.COMPETING;
            updateBtnStatus();
        }

    }

    private void feedChosenAnimal() {
        Object[] possibilities = new Object[animalVector.size()];
        for (int i = 0; i < animalVector.size(); ++i)
            possibilities[i] = animalVector.get(i).getName() + " the " + animalVector.get(i).getType(); //convert animal vector to user - optional data

        String chosenEater = (String) JOptionPane.showInputDialog(this, "Choose an animal to feed : ",
                "FEED ME :(", JOptionPane.PLAIN_MESSAGE, null, possibilities, "1");

        if (chosenEater != null) {
            for (Animal animal : animalVector) {
                String[] words = chosenEater.split(" the ");// example : 'name' the 'animalType'-> ['name' , 'animalType']

                if (animal.getName().contains(words[0]) && animal.getType().contains(words[1]))
                    animal.eat(animal.getMaxEnergy()); //feeds the chosen animal with the max energy possible
            }
        }
    }

    private void startTournament() {
        gameState = GameState.COMPETING;
        updateBtnStatus();

        if (animalGroupVector.size() > 1)
            pickATournamentToStart(); //displays "choose a competition " dialog

        Animal[][] AnimalCompetitions = ConvertGroupVecToArrays(); //converts vector of animal vectors to Arrays of animals

        if (chosenTournament.get(userChosenTour).contains("Reg")) {
            new RegularTournament(AnimalCompetitions.clone(), this, userChosenTour);
        } else {
            new CourierTournament(AnimalCompetitions.clone(), this, userChosenTour);
        }
        System.out.println("userChosenTour in CompetitionFrame is index " + userChosenTour);
        tourName.remove(userChosenTour);
    }

    private Animal[][] ConvertGroupVecToArrays() { //converts a animal vector of vector to arrays of animals
        Animal[][] AnimalCompetitions = new Animal[animalGroupVector.size()][];

        System.out.println("AnimalCompetitions in competitionFrame size = " + animalGroupVector.size());

        for (int i = 0; i < AnimalCompetitions.length; i++) {
            AnimalCompetitions[i] = new Animal[animalGroupVector.get(i).length];
            System.arraycopy(animalGroupVector.get(i), 0, AnimalCompetitions[i], 0, animalGroupVector.get(i).length);
        }

        return AnimalCompetitions;
    }

    private void pickATournamentToStart() {//displays a "choose a competition" dialog
        Object[] possibilities = tourName.toArray();

        String s = (String) JOptionPane.showInputDialog(
                this,
                "Choose Tournament from above",
                "Choose Tournament",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                chosenTournament.get(0));

        if (s != null) {
            for (int i = 0; i < tourName.size(); i++)
                if (tourName.get(i).equals(s))
                    userChosenTour = i;
        }
    }

    private void competitionBtnAction() {
        addCompetition = CompetitionSingleton.getInstance();

        if (firstTime) {
            addCompetition.getAddAnimalButton().addActionListener(this);
            addCompetition.getOkOrNewCompetitionBtn().addActionListener(this);
            addCompetition.getNewCompetitionButton().addActionListener(this);
            addCompetition.getTableButton().addActionListener(this);
        }
        addCompetition.setVisible(true);
        centreWindow(addCompetition);
        firstTime = false;
    }

    private void addAnimalDialogOkBtnAction() {
        chosenCompetition.add(chosenCompetition.get(currentTournament) == null ? "Water animals" : chosenCompetition.get(currentTournament));

        AbstractAnimalFactory abstractFactory = new AbstractAnimalFactory();
        SpeciesFactory speciesFactory = abstractFactory.getSpeciesFactory(chosenCompetition.get(currentTournament));

        String name = addAnimalDialog.getAnimalNameTextField().getText();
        String imageChoice = addAnimalDialog.getAnimalKind();
        int speed = addAnimalDialog.getSlider1().getValue();
        int cons = addAnimalDialog.getSlider1().getValue() * 7;
        gen gender = addAnimalDialog.getAnimalGen();
        Point startPoint = getPositionIndex();

        animalVector.add(speciesFactory.getAnimal(name, speed, cons, startPoint, competitionPanel, imageChoice, gender));
        tempData.add(animalVector.get(currentPosition).getAnimalInfo());

        if (!animalVector.isEmpty())
            addCompetition.getNewCompetitionButton().setEnabled(true);

        gameState = GameState.CHOOSING_COMP_ANIMALS;
        updateBtnStatus();

        addCompetition.requestFocus();
        addAnimalDialog.dispose();
        addCompetition.getOkOrNewCompetitionBtn().setEnabled(true);
    }

    private void addCompetitionNewBtn() {
        if (chosenCompetition != null) {

            gameState = GameState.CHOOSING_COMP_FIRST_ANIMAL;
            updateBtnStatus();

            String[] arrOfString = new String[8];

            arrOfString[0] = tourName.get(currentTournament);
            arrOfString[1] = chosenCompetition.get(currentTournament);
            arrOfString[2] = chosenTournament.get(currentTournament);

            for (int i = 0; i < animalVector.size(); i++)
                arrOfString[i + 3] = animalVector.get(i).getName();

            competitionTableVector.add(arrOfString);
            appendVectorToVectorGroup();

            addCompetition.getTableButton().setEnabled(true);
            currentTournament++;

        } else
            throw new NullPointerException() {
                @Override
                public void printStackTrace() {
                    super.printStackTrace();
                }
            };
    }

    private void addCompetitionOkBtn() {
        if (addCompetition.getCompetitionTypeComboBox().getSelectedItem() != null) {
            chosenCompetition.add(addCompetition.getCompetitionTypeComboBox().getSelectedItem().toString());
            addCompetition.getCompetitionTypeComboBox().setEnabled(false);
            vectorString.add(chosenCompetition.get(currentTournament));
        }

        JRadioButton jRadioButton = addCompetition.getCourierTourRadioBox().isSelected() ? addCompetition.getCourierTourRadioBox() : addCompetition.getRegularTourRadioBox();

        addCompetition.getRegularTourRadioBox().setEnabled(false);
        addCompetition.getCourierTourRadioBox().setEnabled(false);
        chosenTournament.add(jRadioButton.getText());
        vectorString.add(chosenTournament.get(currentTournament));

        tourName.add(addCompetition.getTextField1().getText());

        addCompetition.getAddAnimalButton().setEnabled(true);
        addCompetition.getOkOrNewCompetitionBtn().setText("Add Competition");
        addCompetition.getOkOrNewCompetitionBtn().setEnabled(false);
        vectorString.add(tourName.get(currentTournament));
    }

    private void appendVectorToVectorGroup() {
        System.out.println("animalVector size in CompetitionFrame = " + animalVector.size());
        animalGroupVector.add(animalVector.toArray(Animal[]::new));
        System.out.println("animalGroupVector size in CompetitionFrame = " + animalGroupVector.size());

        newCompetition();
    }

    private void newCompetition() {
        currentPosition = -1;
        animalVector.clear();

        addCompetition.getRegularTourRadioBox().setEnabled(true);
        addCompetition.getCourierTourRadioBox().setEnabled(true);
        addCompetition.getOkOrNewCompetitionBtn().setText("Ok");
        addCompetition.getOkOrNewCompetitionBtn().setEnabled(true);
        addCompetition.getAddAnimalButton().setEnabled(false);
        addCompetition.getNewCompetitionButton().setEnabled(false);
        addCompetition.getCompetitionTypeComboBox().setEnabled(true);

        Random randomNum = new Random();
        String randomTourName = "EmptyName#" + randomNum.nextInt(1000);

        addCompetition.getTextField1().setText(randomTourName);
    }

    /**
     * Preforms the relevant function call , depending on the current state of the program.
     */
    private void clearCalled() {
        if (animalVector.size() > 0) {
            ChooseAnimalToClr();
            addCompetition.getAddAnimalButton().setEnabled(true);
        } else
            clearCompetitionDialog();
        newCompetition();

        updateBtnStatus();
    }

    /**
     * Changes the game state according to the user's choice of clearing.
     */
    private void clearCompetitionDialog() {
        int choice = ActionMessageDialog.createClrCompetitionDialog(this);
        chosenCompetition = null;
        gameState = (choice == 0 ? GameState.CHOOSING_COMP_FIRST_ANIMAL : GameState.CHOOSING_COMP_TYPE);
    }


    /**
     * Clears the chosen animal from the frame and updated the game state accordingly.
     */
    private void ChooseAnimalToClr() {
        if (displayClrOptions()) {
            updateAnimalLocationPostClr();

            if (animalVector.isEmpty())
                gameState = GameState.CHOOSING_COMP_FIRST_ANIMAL;
            else
                gameState = GameState.CHOOSING_COMP_ANIMALS;
        }
    }

    /**
     * Display the animals' names that can be chose to delete the animal.
     *
     * @return true if animal was chosen by user, else if exited- return false.
     */

    private boolean displayClrOptions() {
        Vector<String> animalNames = new Vector<>();
        for (Animal animal : animalVector) animalNames.add(animal.getName());

        Object[] options = animalNames.toArray();
        String nameToClear = ActionMessageDialog.createClrAnimalDialog(this, options);

        if (nameToClear != null) {
            for (int i = 0; i < animalVector.size(); ++i)
                if (animalVector.get(i).getName().equals(nameToClear)) {
                    animalVector.remove(i);
                    break;
                }
            return true;
        } else
            return false;
    }

    /**
     * Updates all animals' location after an animal was cleared from the competition .S
     */
    private void updateAnimalLocationPostClr() {
        String animalsType = chosenCompetition.get(currentTournament).contains("Water") ? "waterAnimals" : "otherAnimals";
        currentPosition--;
        switch (animalsType) {
            case "waterAnimals":

                for (int i = 0; i < animalVector.size(); i++)
                    animalVector.get(i).setPosition(startPointWater[i]);
                break;

            case "otherAnimals":

                for (int i = 0; i < animalVector.size(); i++)
                    animalVector.get(i).setPosition(startPoint[i]);
                break;
        }
    }

    /**
     * Creates the Information table when the "Info" button is clicked.
     */
    private void createInfoTable() {
        data = new String[tempData.size()][];
        for (int i = 0; i < tempData.size(); i++)
            data[i] = (String[]) tempData.get(i);

        InfoTable table = new InfoTable(data);
        table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setSize(600, 200);
        table.setVisible(true);
        table.setTitle("InfoTable");
    }

    private void createCompetitionTable() {
        String[][] competitionTable = new String[competitionTableVector.size()][];
        for (int i = 0; i < competitionTableVector.size(); i++)
            competitionTable[i] = (String[]) competitionTableVector.get(i);

        CompetitionsTable table = new CompetitionsTable(competitionTable);
        table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setSize(1000, 200);
        table.setVisible(true);
        table.setTitle("Competition Table");
    }


    /**
     * Updates the relevant buttons' enabling/disabling status by the current game state.
     */
    private void updateBtnStatus() {
        System.out.println("******* " + gameState.toString() + " ******* ");
        switch (gameState) {
            case CHOOSING_COMP_TYPE -> {  // relevant state =   CHOOSING_COMP_TYPE
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            case CHOOSING_COMP_FIRST_ANIMAL -> { // relevant state =   CHOOSING_COMP_FIRST_ANIMAL
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            case CHOOSING_COMP_ANIMALS -> { // relevant state =    CHOOSING_COMP_ANIMALS
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
            }
            case COMPETING -> { // relevant state =    COMPETING
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(false);
            }
            case CLEARED -> { // relevant state =    CLEARED
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);

            } // invalid state
            default -> throw new IllegalStateException("Unexpected value: " + gameState);
        }
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public Vector<Animal> getAnimalVector() {
        return animalVector;
    }

    public String getChosenCompetition() {
        return chosenCompetition.get(currentTournament);
    }

    public CompetitionPanel getCompetitionPanel() {
        return competitionPanel;
    }

    public static Point[] getStartPointWater() {
        return startPointWater;
    }

    public static Point[] getStartPoint() {
        return startPoint;
    }

    public static Point[] getEndPoint() {
        return endPoint;
    }

    public static Point[] getEndPointWater() {
        return endPointWater;
    }

    public void setAnimalVector(Animal[] animals) {
        this.animalVector.clear();
        Collections.addAll(this.animalVector, animals);
    }


    public static void main(String[] args) {
        MainFrameSingelton.getInstance();
    }


}

