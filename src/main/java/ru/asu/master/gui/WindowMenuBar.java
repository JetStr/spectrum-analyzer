package ru.asu.master.gui;

import javax.swing.*;

public class WindowMenuBar extends JMenuBar {
    private JMenu fileMenu;
    private JMenu projectMenu;

    private JMenuItem openItem;
    private JMenuItem newProjectItem;

    public WindowMenuBar() {
        fileMenu = new JMenu("Файл");
        projectMenu = new JMenu("Проект");

        openItem = new JMenuItem("Открыть");
        newProjectItem = new JMenuItem("Новый проект");

        initComponents();
    }

    private void initComponents() {
        fileMenu.add(openItem);
        projectMenu.add(newProjectItem);
        add(fileMenu);
        add(projectMenu);
    }
}
