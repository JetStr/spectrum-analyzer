package ru.asu.master.calculate;

import ru.asu.master.calculate.model.*;
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

    public CalculationResult calculate() {
        return mathModel.calculate();
    }

    private MathModel getMathModel(ProjectSettings settings) throws NotSupportedDataLoaderException, IOException, InputDataCreationException {
        String type = settings.getModelType();
        if (Objects.equals(MathModelType.TRIANGLE, type)) {
            return new TriangleModel("Треугольная",
                    settings.getT1(),
                    settings.getT2(),
                    settings.getT3(),
                    ioLayer.getInputData(dataFile.getAbsolutePath(), "idealSpectrum.xlsx"),
                    settings.getK());
        } else {
            return new ParabolaModel(
                    "Параболическая",
                    settings.getT1(),
                    settings.getT2(),
                    settings.getT3(),
                    ioLayer.getInputData(dataFile.getAbsolutePath(), "idealSpectrum.xlsx"),
                    settings.getK());
        }
    }
}
