package Graphics;

import javax.swing.*;

/**
 * CompetitionMenu
 */
public class CompetitionMenu extends JMenuBar {

    /**
     * CompetitionMenu constructor.
     */
    public CompetitionMenu() {
        JMenu file = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        file.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        JMenu help = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");
        help.add(helpItem);
        helpItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Home Work 2 \nGUI"));

        add(file);
        add(help);

    }
}
