package ru.asu.master.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ProjectPanel extends JPanel {
    private final JLabel chosenFileLabel;
    private final JButton chooseFileButton;
    private File dataFile;

    public ProjectPanel() {
        chosenFileLabel = new JLabel("Выбранный файл: ");
        chooseFileButton = new JButton("Открыть файл");
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        initComponents();
    }

    private void initComponents() {
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
}
