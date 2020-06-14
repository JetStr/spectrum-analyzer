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
    private final double[] idealSpectrum;

    public InputData(double[] waveLengths, double[] idealSpectrum) throws InputDataCreationException {
        if (waveLengths == null || waveLengths.length == 0) {
            throw new InputDataCreationException("Неверное значение для массива длин волн");
        }
        if (idealSpectrum == null || idealSpectrum.length == 0) {
            throw new InputDataCreationException("Неверное значение для массива длин волн");
        }
        this.idealSpectrum = idealSpectrum;
        this.waveLengths = waveLengths;
    }

    public double[] getWaveLengths() {
        return waveLengths;
    }

    public double[] getIdealSpectrum() {
        return idealSpectrum;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "waveLengths=" + Arrays.toString(waveLengths) +
                ", idealSpectrum=" + Arrays.toString(idealSpectrum) +
                '}';
    }
}
