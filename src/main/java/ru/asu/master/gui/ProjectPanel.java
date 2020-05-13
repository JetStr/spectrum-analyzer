package ru.asu.master.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProjectPanel extends JPanel {
    private final JLabel chosenFileLabel;
    private final JLabel t0label;
    private final JLabel t1label;
    private final JLabel t2label;
    private final JTextField t0textField;
    private final JTextField t1textField;
    private final JTextField t2textField;
    private final JButton runButton;
    private final JButton chooseFileButton;
    private final JLabel modelTypesLabel;
    private final JComboBox modelTypesComboBox;
    private File dataFile;

    public ProjectPanel(int width, int height) {
        setSize(new Dimension(width, height));
        chosenFileLabel = new JLabel("Выбранный файл: ");
        t0label = new JLabel("t0:");
        t1label = new JLabel("t1:");
        t2label = new JLabel("t2:");
        t0textField = new JTextField();
        t1textField = new JTextField();
        t2textField = new JTextField();
        chooseFileButton = new JButton("Открыть файл");
        runButton = new JButton("Запуск вычислений");
        modelTypesLabel = new JLabel("Вид распределения: ");
        modelTypesComboBox = new JComboBox<>(new String[]{"Треугольное", "Параболическое"});
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        add(chosenFileLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        add(chooseFileButton, new GridBagConstraints(1, 0, 1, 1, 0, 0
                , GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        add(t0label, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        add(t0textField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        add(t1label, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        add(t1textField, new GridBagConstraints(1, 2, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        add(t2label, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
        add(t2textField, new GridBagConstraints(1, 3, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        add(modelTypesLabel, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        add(modelTypesComboBox, new GridBagConstraints(1, 4, 1, 1, 0, 0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        add(runButton, new GridBagConstraints(0, 5, 2, 1, 0, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
        chooseFileButton.addActionListener(event ->
        {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                dataFile = fileChooser.getSelectedFile();
                chosenFileLabel.setText("Выбранный файл: " + dataFile.getName());
            }
        });
    }

    public File getDataFile() {
        return dataFile;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JTextField getT0textField() {
        return t0textField;
    }

    public JTextField getT1textField() {
        return t1textField;
    }

    public JTextField getT2textField() {
        return t2textField;
    }

    public JComboBox getModelTypesComboBox() {
        return modelTypesComboBox;
    }
}
