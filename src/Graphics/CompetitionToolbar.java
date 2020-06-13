package Graphics;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * CompetitionToolbar
 */
public class CompetitionToolbar extends JPanel {

    private final JButton competitionBtn;
    private final JButton addAnimalBtn;
    private final JButton clearBtn;
    private final JButton eatBtn;
    private final JButton infoBtn;
    private final JButton exitBtn;

    /**
     * CompetitionToolbar constructor.
     */
    public CompetitionToolbar() {
        FlowLayout Layout = new FlowLayout();
        Layout.setHgap(0);
        setLayout(Layout);

        Border innedBorder = BorderFactory.createTitledBorder("");
        Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outBorder, innedBorder));

        Dimension btnSize = new Dimension(156, 30);

        competitionBtn = new JButton("Competition");
        competitionBtn.setPreferredSize(btnSize);
        addAnimalBtn = new JButton("Add Animal");
        addAnimalBtn.setPreferredSize(btnSize);
        clearBtn = new JButton("Clear");
        clearBtn.setPreferredSize(btnSize);
        eatBtn = new JButton("Eat");
        eatBtn.setPreferredSize(btnSize);
        infoBtn = new JButton("Info");
        infoBtn.setPreferredSize(btnSize);
        exitBtn = new JButton("Exit");
        exitBtn.setPreferredSize(btnSize);

        add(competitionBtn);
        add(addAnimalBtn);
        add(clearBtn);
        add(eatBtn);
        add(infoBtn);
        add(exitBtn);
    }

    public JButton getAddAnimalBtn() {
        return addAnimalBtn;
    }

    public JButton getClearBtn() {
        return clearBtn;
    }

    public JButton getCompetitionBtn() {
        return competitionBtn;
    }

    public JButton getEatBtn() {
        return eatBtn;
    }

    public JButton getExitBtn() {
        return exitBtn;
    }

    public JButton getInfoBtn() {
        return infoBtn;
    }


}
