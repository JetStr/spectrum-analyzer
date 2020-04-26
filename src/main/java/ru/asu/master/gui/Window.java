package ru.asu.master.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 800;
    private JPanel projectPanel;

    public Window(String title) throws HeadlessException {
        super(title);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        getContentPane().setLayout(new GridLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        projectPanel = new ProjectPanel();
        setJMenuBar(new WindowMenuBar());
        mainPanel.add(projectPanel);
        getContentPane().add(mainPanel);
    }

    public JPanel getProjectPanel() {
        return projectPanel;
    }
}
