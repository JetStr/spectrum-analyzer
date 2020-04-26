package ru.asu.master.core.model;

import java.util.Arrays;

/**
 * Входные данные
 */
public class InputData {
    /**
     * Массив длин волн
     */
    private final double[] waveLengths;
    /**
     * Массив значений сигналов
     */
    private final double[] signals;

    public InputData(double[] waveLengths, double[] signals) throws InputDataCreationException {
        if (waveLengths.length != signals.length) {
            throw new InputDataCreationException("Длина массива длин волн не равна длине массива сигналов");
        }
        this.waveLengths = waveLengths;
        this.signals = signals;
    }

    public double[] getWaveLengths() {
        return waveLengths;
    }

    public double[] getSignals() {
        return signals;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "waveLengths=" + Arrays.toString(waveLengths) +
                ", signals=" + Arrays.toString(signals) +
                '}';
    }
}
