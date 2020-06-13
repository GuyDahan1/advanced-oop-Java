package Graphics;

import javax.swing.*;

/**
 * CompetitionMenu
 */
public class CompetitionMenu extends JMenuBar {

    private JMenu file;
    private JMenu help;

    /**
     * CompetitionMenu constructor.
     */
    public CompetitionMenu() {
        file = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        file.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        help = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        help.add(helpItem);
        helpItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Home Work 2 \nGUI"));

        add(file);
        add(help);

    }
}
