package ru.asu.master.core.plot;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;

/**
 * Реализация плоттера через либу XChart
 *
 * @author sineok.i
 */
public class XChartPlotter implements Plotter {
    private final double[] y;
    private final double[] x;
    private final String plotName;

    public XChartPlotter(double[] y, double[] x, String plotName) {
        this.y = y;
        this.x = x;
        this.plotName = plotName;
    }

    @Override
    public void plot() {
        XYChart chart = QuickChart.getChart("График распределения", "X", "Y", plotName, x, y);
        final JFrame frame = new JFrame("Cardio");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem saveItem = new JMenuItem("Сохранить");
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        XChartPanel<Chart> chartPanel = new XChartPanel<>(chart);
        frame.setJMenuBar(menuBar);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
