package ru.asu.master.core.plot;

/**
 * @author sineok.i
 */
public final class PlotterHelper {
    private PlotterHelper() {
    }

    public static double[] getTemperatureRange(double t1, double t2, int range) {
        double step = (t2 - t1) / range;
        double[] array = new double[range];
        array[0] = t1;
        for (int i = 1; i < range; i++) {
            array[i] = array[i - 1] + step;
        }
        return array;
    }
}
