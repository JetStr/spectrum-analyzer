package ru.asu.master.calculate.model;

import ru.asu.master.core.model.InputData;

public class TriangleModel extends MathModel {
    private final double k;

    public TriangleModel(String name, InputData inputData, double t1, double t0, double t2, double k) {
        super(name, inputData, t1, t0, t2);
        this.k = k;
    }

    @Override
    public double[] calculate() {
        double[] result = new double[inputData.getSignals().length];
        double[] signals = inputData.getSignals();
        double[] waveLengths = inputData.getWaveLengths();
        for (int i = 0; i < signals.length; i++) {
            double waveLength = waveLengths[i];
            result[i] = k * ((t2 - t1) * (Math.exp(-t1 / waveLength)
                    + Math.exp(-t2 / waveLength))
                    - 2 * waveLength * (Math.exp(-t1 / waveLength) - Math.exp(-t2 / waveLength)));
        }
        return result;
    }
}
