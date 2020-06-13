package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * InfoTable
 */
public class InfoTable extends JFrame {
    private static final String[] columnNames = {"Animal", "Category", "Type",
            "Speed", "Energy Amount", "Distance", "Energy Consumpt"};

    private static JTable table;
    private String[][] data;

    /**
     * InfoTable constructor.
     *
     * @param data - A given matrix of data the will be inserted to the table.
     */
    public InfoTable(String[][] data) {
        super("Info Table");

        this.data = data.clone();

        table = new JTable(this.data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 50));

        table.setFillsViewportHeight(true);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);
        this.add(sp);

        // Frame Size
        this.setSize(500, 200);
    }

    public static int getColumnSize() {
        return columnNames.length;
    }

}
