package ru.asu.master.core.model;

/**
 * Заданные пользователем настройки
 */
public class ProjectSettings {
    private final String modelType;
    private final double t1;
    private final double t2;
    private final double t3;
    private final double k;

    public ProjectSettings(String modelType, double t1, double t2, double t3, double k) {
        this.modelType = modelType;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.k = k;
    }

    public String getModelType() {
        return modelType;
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

    public double getK() {
        return k;
    }

    @Override
    public String toString() {
        return "ProjectSettings{" +
                "modelType='" + modelType + '\'' +
                ", t0=" + t1 +
                ", t1=" + t1 +
                ", t2=" + t2 +
                ", k=" + k +
                '}';
    }
}
