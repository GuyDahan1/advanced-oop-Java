package Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddCompetition extends JFrame implements ActionListener {


    private JLabel title;
    private JRadioButton courierTourRadioBox;
    private JComboBox<String> competitionTypeComboBox;
    private JRadioButton regularTourRadioBox;
    private JButton okBtn;
    private JButton cancelButton;
    private JPanel addCompetitionPanel;
    private JTextField tourTextField;
    private JLabel tournamentNameLabel;
    private JButton tableButton;
    private JButton addAnimalButton;
    private JButton newCompetitionButton;
    String randomTourName;

    public AddCompetition() {
        super("Add Competition");

        okBtn.addActionListener(this);
        cancelButton.addActionListener(this);

        Random randomNum = new Random();
        randomTourName = "EmptyName#" + randomNum.nextInt(1000);

        tourTextField.setText(randomTourName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(addCompetitionPanel);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(cancelButton)) {
            setVisible(false);
        }

    }

    public JComboBox<String> getCompetitionTypeComboBox() {
        return competitionTypeComboBox;
    }

    public JRadioButton getCourierTourRadioBox() {
        return courierTourRadioBox;
    }

    public JRadioButton getRegularTourRadioBox() {
        return regularTourRadioBox;
    }

    public JTextField getTextField1() {
        return tourTextField;
    }

    public JButton getOkOrNewCompetitionBtn() {
        return okBtn;
    }

    public JButton getNewCompetitionButton() {
        return newCompetitionButton;
    }

    public JButton getAddAnimalButton() {
        return addAnimalButton;
    }

    public JButton getTableButton() {
        return tableButton;
    }

    private void createUIComponents() {

    }

    public String getTitle() {
        return title.getText();
    }

    public JLabel getTournamentNameLabel() {
        return tournamentNameLabel;
    }

}
