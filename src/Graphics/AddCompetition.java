package Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class AddCompetition extends JFrame implements ActionListener {


    private JLabel title;
    private JRadioButton courierTourRadioBox;
    private JComboBox competitionTypeComboBox;
    private JRadioButton regularTourRadioBox;
    private JButton okBtn;
    private JButton cancelButton;
    private JPanel addCompetitionPanel;
    private JTextField tourTextField;
    private JLabel tournamentNameLabel;
    private JButton tableButton;
    private JButton addAnimalButton;


    public AddCompetition(){
        super("Add Competition");

        okBtn.addActionListener(this);
        cancelButton.addActionListener(this);



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(addCompetitionPanel);
        pack();
        setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(cancelButton)){
            setVisible(false);
        }

    }

    public JComboBox getCompetitionTypeComboBox() {
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

    public JButton getOkBtn() {
        return okBtn;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JButton getAddAnimalButton() {
        return addAnimalButton;
    }

    //
}
