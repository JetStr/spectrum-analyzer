package ru.asu.master.calculate.model;

import ru.asu.master.core.model.InputData;

public abstract class MathModel {
    private String name;
    protected double t1;
    protected double t0;
    protected double t2;
    protected InputData inputData;

    public MathModel(String name, InputData inputData, double t1, double t0, double t2) {
        this.name = name;
        this.inputData = inputData;
        this.t1 = t1;
        this.t0 = t0;
        this.t2 = t2;
    }

    public abstract double[] calculate();
}
