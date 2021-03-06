package ru.asu.master.calculate.model;

import ru.asu.master.core.model.InputData;

/**
 * Реализация треугольной модели распределения
 */
public class TriangleModel extends MathModel {
    private static final double STEP = 100;
    private final double k;
    private double[] result;

    public TriangleModel(String name, double t1, double t2, double t3, InputData inputData, double k) {
        super(name, t1, t2, t3, inputData);
        this.k = k;
    }

    @Override
    public CalculationResult calculate() {
        result = new double[inputData.getWaveLengths().length];
        double[] waveLengths = inputData.getWaveLengths();
        double lms = processIteration(waveLengths);
        double currentLms = lms;
        double previousLms = lms;
        do {
            lms = currentLms;
            t1 += STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t2 += STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t3 += STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t1 -= STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t2 -= STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t3 -= STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
            }
        } while (currentLms < lms);

        return new CalculationResult(previousLms, result, t1, t2, t3);
    }

    private double processIteration(double[] waveLengths) {
        result = new double[inputData.getWaveLengths().length];
        for (int i = 0; i < waveLengths.length; i++) {
            double waveLength = waveLengths[i];
            result[i] = k *
                    ((t3 - t1) * (Math.exp(-t1) / waveLength)
                            + (t2 - t1) * (Math.exp(-t3 / waveLength)
                            - (t3 - t1) * Math.exp(-t2 / waveLength)));
        }
        return getLSMValue(result);
    }
}
