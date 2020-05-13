package ru.asu.master.core.model;

/**
 * Заданные пользователем настройки
 */
public class ProjectSettings {
    private final String modelType;
    private final double t0;
    private final double t1;
    private final double t2;
    private final double k;

    public ProjectSettings(String modelType, double t0, double t1, double t2, double k) {
        this.modelType = modelType;
        this.t0 = t0;
        this.t1 = t1;
        this.t2 = t2;
        this.k = k;
    }

    public String getModelType() {
        return modelType;
    }

    public double getT0() {
        return t0;
    }

    public double getT1() {
        return t1;
    }

    public double getT2() {
        return t2;
    }

    public double getK() {
        return k;
    }

    @Override
    public String toString() {
        return "ProjectSettings{" +
                "modelType='" + modelType + '\'' +
                ", t0=" + t0 +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", k=" + k +
                '}';
    }
}
