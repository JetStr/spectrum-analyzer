package ru.asu.master.calculate;

import ru.asu.master.calculate.model.MathModel;
import ru.asu.master.calculate.model.MathModelType;
import ru.asu.master.calculate.model.TriangleModel;
import ru.asu.master.core.io.IOLayer;
import ru.asu.master.core.io.NotSupportedDataLoaderException;
import ru.asu.master.core.model.InputDataCreationException;
import ru.asu.master.core.model.ProjectSettings;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MathProcessor {
    private final MathModel mathModel;
    private final IOLayer ioLayer;
    private final File dataFile;

    public MathProcessor(ProjectSettings settings, File dataFile) throws NotSupportedDataLoaderException, IOException, InputDataCreationException {
        ioLayer = new IOLayer();
        this.dataFile = dataFile;
        mathModel = getMathModel(settings);
    }

    public double[] calculate() {
        return mathModel.calculate();
    }

    private MathModel getMathModel(ProjectSettings settings) throws NotSupportedDataLoaderException, IOException, InputDataCreationException {
        String type = settings.getModelType();
        if (Objects.equals(MathModelType.TRIANGLE, type)) {
            return new TriangleModel("Треугольная",
                    ioLayer.getInputData(dataFile.getAbsolutePath()),
                    settings.getT1(),
                    settings.getT0(),
                    settings.getT2(),
                    settings.getK());
        } else {
            //todo пока что null, для параболы нет реализации
            return null;
        }
    }
}
