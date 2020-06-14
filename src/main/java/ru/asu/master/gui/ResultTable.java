package ru.asu.master.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author sineok.i
 */
public class ResultTable extends JFrame {
    private static final Object[] columnHeaders = new String[]{"Вид распределения", "t1", "t2", "t3", "Значение МНК"};
    private final JTable table;

    public ResultTable(String title) throws HeadlessException {
        table = new JTable();
        setTitle(title);
        setSize(new Dimension(550, 200));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(new JScrollPane(table));
    }

    public void refreshModel(List<List<String>> data) {
        DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
        data.forEach(item -> tableModel.addRow(item.toArray()));
        table.setModel(tableModel);
        repaint();
        setVisible(true);
    }
}
