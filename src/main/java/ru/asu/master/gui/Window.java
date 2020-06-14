package ru.asu.master.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 420;
    private ProjectPanel projectPanel;

    public Window(String title) throws HeadlessException {
        super(title);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        getContentPane().setLayout(new GridLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        projectPanel = new ProjectPanel(WINDOW_WIDTH, WINDOW_HEIGHT / 2);
        add(projectPanel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }
}
