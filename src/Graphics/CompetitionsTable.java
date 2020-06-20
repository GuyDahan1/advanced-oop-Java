package Graphics;

import javax.swing.*;
import java.awt.*;

public class CompetitionsTable extends JFrame {


    private static final String[] columnNames = {"Competition Name", "Competition Type", "Tournament Type",
            "Animal Name", " Animal Name", "Animal Name", "Animal Name" , "Animal Name"};

    /**
     * InfoTable constructor.
     *
     * @param data - A given matrix of data the will be inserted to the table.
     */
    public CompetitionsTable(String[][] data) {
        super("Competition Table");

        String[][] data1 = data.clone();

        JTable table = new JTable(data1, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));

        table.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        this.add(sp);

        // Frame Size
        this.setSize(700, 200);
    }

}

