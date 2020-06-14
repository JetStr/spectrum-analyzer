package ru.asu.master.calculate.model;

import java.util.Arrays;

/**
 * @author sineok.i
 */
public class CalculationResult {
    /**
     * Значение МНК
     */
    private final double mnk;
    /**
     * Посчитанное температурное распределение
     */
    private final double[] data;

    private final double t1;
    private final double t2;
    private final double t3;

    public CalculationResult(double mnk, double[] data, double t1, double t2) {
        this(mnk, data, t1, t2, 0);
    }

    public CalculationResult(double mnk, double[] data, double t1, double t2, double t3) {
        this.mnk = mnk;
        this.data = data;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public double getMnk() {
        return mnk;
    }

    public double[] getData() {
        return data;
    }

    public double getT1() {
        return t1;
    }

    public double getT2() {
        return t2;
    }

    public double getT3() {
        return t3;
    }

    @Override
    public String toString() {
        return "CalculationResult{" +
                "mnk=" + mnk +
                ", data=" + Arrays.toString(data) +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                '}';
    }
}
