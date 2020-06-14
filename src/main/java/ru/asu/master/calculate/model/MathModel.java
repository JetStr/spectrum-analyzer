package ru.asu.master.calculate.model;

import ru.asu.master.core.model.InputData;

public abstract class MathModel {
    private String name;
    protected double t1;
    protected double t2;
    protected double t3;
    protected InputData inputData;

    public MathModel(String name, double t1, double t2, double t3, InputData inputData) {
        this.name = name;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.inputData = inputData;
    }

    public abstract CalculationResult calculate();

    /**
     * Получение значения по методу наименьших квадратов
     *
     * @return значение МНК
     */
    protected double getLSMValue(double[] calculated) {
        double[] ideal = inputData.getIdealSpectrum();
        double sum = 0;
        for (int i = 0; i < calculated.length; i++) {
            sum += Math.pow(calculated[i] - ideal[i], 2);
        }
        return sum;
    }
}
