package ru.asu.master.core.plot;

/**
 * @author sineok.i
 */
public final class PlotterFactory {
    public static final String X_CHART_PLOTTER = "xchart-plotter";

    private PlotterFactory() {
    }

    public static Plotter getPlotter(String plotterType, String plotName, double[] y, double[] x) {
        if (X_CHART_PLOTTER.equals(plotterType)) {
            return new XChartPlotter(y, x, plotName);
        }
        throw new IllegalArgumentException("Нет реализации плоттера для типа " + plotterType);
    }
}
