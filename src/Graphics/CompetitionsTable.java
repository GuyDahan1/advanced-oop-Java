package Graphics;

import javax.swing.*;
import java.awt.*;

public class CompetitionsTable extends JFrame {


    private static final String[] columnNames = {"Competition Name", "Competition Type", "Tournament Type",
            "Animal Name", " Animal Name", "Animal Name", "Animal Name" , "Animal Name"};

    private static JTable table;
    private String[][] data;

    /**
     * InfoTable constructor.
     *
     * @param data - A given matrix of data the will be inserted to the table.
     */
    public CompetitionsTable(String[][] data) {
        super("Competition Table");

        this.data = data.clone();

        table = new JTable(this.data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));

        table.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        this.add(sp);

        // Frame Size
        this.setSize(700, 200);
    }

    public static int getColumnSize() {
        return columnNames.length;
    }

}

