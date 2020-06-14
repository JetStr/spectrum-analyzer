package ru.asu.master.calculate.model;

import ru.asu.master.core.model.InputData;

/**
 * @author sineok.i
 */
public class ParabolaModel extends MathModel {
    private static final double STEP = 100;
    private final double k;
    private double[] result;

    public ParabolaModel(String name, double t1, double t2, double t3, InputData inputData, double k) {
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
        double exactT1 = t1;
        double exactT2 = t2;
        do {
            lms = currentLms;
            t1 += STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
                exactT1 = t1;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t2 += STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
                exactT2 = t2;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t1 -= STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
                exactT1 = t1;
            }
        } while (currentLms < lms);

        do {
            lms = previousLms;
            t2 -= STEP;
            currentLms = processIteration(waveLengths);
            if (currentLms < lms) {
                previousLms = currentLms;
                exactT2 = t2;
            }
        } while (currentLms < lms);

        return new CalculationResult(previousLms, result, exactT1, exactT2);
    }

    private double processIteration(double[] waveLengths) {
        result = new double[inputData.getWaveLengths().length];
        for (int i = 0; i < waveLengths.length; i++) {
            double waveLength = waveLengths[i];
            result[i] = k * ((t2 - t1) * (Math.exp(-t1 / waveLength)
                    + Math.exp(-t2 / waveLength))
                    - 2 * waveLength * (Math.exp(-t1 / waveLength) - Math.exp(-t2 / waveLength)));
        }
        return getLSMValue(result);
    }
}
