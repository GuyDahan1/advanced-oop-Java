package Graphics;

import animals.*;
import designPatterns.*;
import mobility.Point;
import thread.Referee;
import thread.RegularTournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * CompetitionFrame
 */
public class CompetitionFrame extends JFrame implements ActionListener {
    private CompetitionPanel competitionPanel = new CompetitionPanel();
    private AddCompetition addCompetition;
    private final CompetitionMenu competitionMenu = new CompetitionMenu();
    private AddAnimalDialog addAnimalDialog;
    private final int maxNonAirAnimal = 4;
    private final int maxAirAnimal = 5;
    private Animal[][] animalsArray = null;

    static Point[] startPointWater;
    static Point[] endPointWater;
    static Point[] startPoint;
    static Point[] endPoint;

    private Vector<Animal> animalVector = new Vector<>();
    private Vector<Animal[]> animalGroupVector = new Vector<>();


    Vector<String> vectorString = new Vector<>();
    Vector<Object[]> tempData = new Vector<>();


    String[][] data;
    Vector<Object[]> competitionTableVector = new Vector<>();


    private int currentPosition = -1;
    private int currentTournament = -1;

    private String chosenCompetition = null;
    private String chosenTour = null;
    private String tourName = null;
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

        pointInit();

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
    public void pointInit() {
        int heightDiff = 150; //height difference
        int terStartY = 10; //terrestrial starting y
        int waterStartY = 90; //water starting y

        for (int i = 0; i < maxAirAnimal; i++) { ////set air & terrestrial animals points
            startPoint[i] = new Point(5, terStartY);
            endPoint[i] = new Point(840, terStartY);
            terStartY += heightDiff;
            if (i < maxNonAirAnimal) { //set water animals points
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
        Point animalPoint = null;
        if(chosenTour.contains("Regu")) {

            animalPoint = getRegularPoint(null);

        }else{
            animalPoint = getCourierPoint(null);

        }
        return animalPoint;
    }

    private Point getRegularPoint(Point animalPoint) {
        currentPosition++;
        if (chosenCompetition.contains("Air") && currentPosition < maxAirAnimal) {
            if (currentPosition == maxAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPoint[currentPosition];
        } else if (chosenCompetition.contains("Water") && currentPosition < maxNonAirAnimal) {
            if (currentPosition == maxNonAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPointWater[currentPosition];
        } else if (chosenCompetition.contains("Terr") && currentPosition < maxNonAirAnimal) {
            if (currentPosition == maxNonAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPoint[currentPosition];
        }
        return animalPoint;
    }

    private Point getCourierPoint(Point animalPoint) {
        currentPosition++;
        if (chosenCompetition.contains("Air") && currentPosition < maxAirAnimal) {
            if (currentPosition == maxAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPoint[currentPosition];
            if(currentPosition%2!=0){
                animalPoint = endPoint[currentPosition];
            }
        } else if (chosenCompetition.contains("Water") && currentPosition < maxNonAirAnimal) {
            if (currentPosition == maxNonAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPointWater[currentPosition];
            if(currentPosition%2!=0){
                animalPoint = endPointWater[currentPosition];
            }
        } else if (chosenCompetition.contains("Terr") && currentPosition < maxNonAirAnimal) {
            if (currentPosition == maxNonAirAnimal - 1) {
                addCompetition.getAddAnimalButton().setEnabled(false);
            }
            animalPoint = startPoint[currentPosition];
            if(currentPosition%2!=0){
                animalPoint = endPoint[currentPosition];
            }
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

        if (e.getSource() == competitionPanel.getCompetitionToolbar().getExitBtn()) // exit button chosen
            System.exit(0);
        else if (e.getSource() == competitionPanel.getCompetitionToolbar().getInfoBtn()) // info button chosen
            createInfoTable();
        else if (e.getSource() == competitionPanel.getCompetitionToolbar().getEatBtn()) // info button chosen
        {
            for (Animal animal : animalVector) {
                int newX = animal.getPosition().getX() + 30;
                int newY = animal.getPosition().getY();
                Point nextPoint = new Point(newX, newY);
                animal.move(nextPoint);
            }
        } else if (e.getSource() == competitionPanel.getCompetitionToolbar().getClearBtn()) {// clear button chosen
            clearCalled();

        } else if (e.getSource() == competitionPanel.getCompetitionToolbar().getCompetitionBtn()) {// competition button chosen
            competitionBtnAction();

        } else if (e.getSource() == addCompetition.getOkOrNewCompetitionBtn()) {//in add competitionFrame the OkBtn - when animalVector empty/ addNewCompetitionBtn - when animalVector empty > 0

            if (animalVector.isEmpty()) {
                addCompetitionOkBtn();
            } else {
                addCompetitionNewBtn();
            }

        } else if (e.getSource() == addCompetition.getAddAnimalButton()) {

            addAnimalDialog = new AddAnimalDialog(this, "Add Animal ");
            centreWindow(addAnimalDialog);
            addAnimalDialog.getOkButtonAddAnimal().addActionListener(this);

        } else if (e.getSource() == addAnimalDialog.getOkButtonAddAnimal()) {//if create button is activated

            addAnimalDialogOkBtnAction();

        } else if (e.getSource() == addCompetition.getNewCompetitionButton()) {

            newCompetition();

        } else if (e.getSource().equals(addCompetition.getTableButton())) {

            createCompetitionTable();

        } else if (e.getSource().equals(competitionPanel.getCompetitionToolbar().getStartBtn())) {

            animalsArray = new Animal[animalGroupVector.size()][];
            for (int i = 0; i < animalsArray.length; i++) {
                animalsArray[i] = new Animal[animalGroupVector.get(i).length];
                for (int j = 0; j < animalGroupVector.get(i).length; j++) {
                    animalsArray[i][j] = animalGroupVector.get(i)[j];
                }
            }
            new RegularTournament(animalsArray.clone(), this);
        }
        validate();
        repaint();
        if (currentPosition == maxAirAnimal - 1 || currentPosition == maxNonAirAnimal - 1 || currentPosition == maxNonAirAnimal - 1) {
            gameState = GameState.COMPETING;
            updateBtnStatus();
        }
    }

    private void competitionBtnAction() {
        addCompetition = CompetitioinSingelton.getInstance();

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
        chosenCompetition = chosenCompetition == null ? "Water animals" : chosenCompetition;


        AbstractAnimalFactory abstractFactory = new AbstractAnimalFactory();

        SpeciesFactory speciesFactory = abstractFactory.getSpeciesFactory(chosenCompetition);
        String name = addAnimalDialog.getAnimalNameTextField().getText();
        String imageChoice = addAnimalDialog.getAnimalKind();
        int speed = addAnimalDialog.getSlider1().getValue();
        int cons = addAnimalDialog.getSlider1().getValue() * 7;
        gen gender = addAnimalDialog.getAnimalGen();
        Point startPoint = getPositionIndex();

        animalVector.add(speciesFactory.getAnimal(name, speed, cons, startPoint, competitionPanel, imageChoice, gender));

        tempData.add(animalVector.get(currentPosition).getAnimalInfo());

        if (!animalVector.isEmpty()) {
            addCompetition.getNewCompetitionButton().setEnabled(true);
        }

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
        }
        String[] arrOfString = new String[8];
        arrOfString[0] = tourName;
        arrOfString[1] = chosenCompetition;
        arrOfString[2] = chosenTour;
        for (int i = 0; i < animalVector.size(); i++) {
            arrOfString[i + 3] = animalVector.get(i).getName();
        }

        competitionTableVector.add(arrOfString);
        appendVectorToVectorGroup();

        addCompetition.getTableButton().setEnabled(true);
    }

    private void addCompetitionOkBtn() {
        chosenCompetition = addCompetition.getCompetitionTypeComboBox().getSelectedItem().toString();
        addCompetition.getCompetitionTypeComboBox().setEnabled(false);
        vectorString.add(chosenCompetition);

        JRadioButton jRadioButton = addCompetition.getCourierTourRadioBox().isSelected() ? addCompetition.getCourierTourRadioBox() : addCompetition.getRegularTourRadioBox();

        addCompetition.getRegularTourRadioBox().setEnabled(false);
        addCompetition.getCourierTourRadioBox().setEnabled(false);
        chosenTour = jRadioButton.getText();
        vectorString.add(chosenTour);

        tourName = addCompetition.getTextField1().getText();
        addCompetition.getAddAnimalButton().setEnabled(true);
        addCompetition.getOkOrNewCompetitionBtn().setText("Add Competition");
        addCompetition.getOkOrNewCompetitionBtn().setEnabled(false);
        vectorString.add(tourName);
    }


    private void appendVectorToVectorGroup() {
        animalGroupVector.add(animalVector.toArray(Animal[]::new));

        newCompetition();
    }


    private void newCompetition() {

        chosenTour = null;
        tourName = null;
        chosenCompetition = null;
        currentPosition = -1;
        animalVector.clear();
        addCompetition.getRegularTourRadioBox().setEnabled(true);
        addCompetition.getCourierTourRadioBox().setEnabled(true);
        addCompetition.getOkOrNewCompetitionBtn().setText("Ok");
        addCompetition.getOkOrNewCompetitionBtn().setEnabled(true);
        addCompetition.getAddAnimalButton().setEnabled(false);
        addCompetition.getNewCompetitionButton().setEnabled(false);
        addCompetition.getCompetitionTypeComboBox().setEnabled(true);


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
        String animalsType = chosenCompetition.contains("Water") ? "waterAnimals" : "otherAnimals";
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


    public Vector<Animal> getAnimalVector() {
        return animalVector;
    }


    /**
     * Updates the relevant buttons' enabling/disabling status by the current game state.
     */
    private void updateBtnStatus() {
        System.out.println("******* " + gameState.toString() + " ******* ");
        switch (gameState) {
            case CHOOSING_COMP_TYPE: {
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            break;
            case CHOOSING_COMP_FIRST_ANIMAL: {
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            break;
            case CHOOSING_COMP_ANIMALS: {
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
            }
            break;
            case COMPETING: {
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(false);
            }
            break;
            case CLEARED: {
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getStartBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);

            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + gameState);
        }
    }

    public String getChosenCompetition() {
        return chosenCompetition;
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void main(String[] args) {
        MainFrameSingelton.getInstance();
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
        for (int i = 0; i < animals.length; i++) {
            this.animalVector.add(animals[i]);
        }
    }
}
