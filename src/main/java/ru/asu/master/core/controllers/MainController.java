package ru.asu.master.core.controllers;

import org.apache.commons.logging.Log;
import ru.asu.master.calculate.MathProcessor;
import ru.asu.master.core.io.NotSupportedDataLoaderException;
import ru.asu.master.core.model.InputDataCreationException;
import ru.asu.master.core.model.ProjectSettings;
import ru.asu.master.core.utils.LoggerFactory;
import ru.asu.master.gui.Window;

import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class MainController {
    private static final Log logger = LoggerFactory.createLogger("MainController");
    private final Window window;

    public MainController() {
        this.window = new Window("Spectrum analyzer");
        init();
    }

    private void init() {
        window.getProjectPanel().getRunButton().addActionListener(event ->
                runCalculations());
    }

    private void runCalculations() {
        logger.info("Запуск вычислений");
        if (window.getProjectPanel().getDataFile() == null) {
            logger.error("Не выбран файл с данными");
            JOptionPane.showMessageDialog(window,
                    "Необходимо выбрать файл с данными",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        ProjectSettings settings;
        try {
            settings = extractProjectSetting();
        } catch (SettingsExtractionException e) {
            logger.error(e, e);
            JOptionPane.showMessageDialog(window,
                    "Неверно заполнено одно или несколько полей",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        MathProcessor processor;
        try {
            processor = new MathProcessor(settings, window.getProjectPanel().getDataFile());
        } catch (NotSupportedDataLoaderException | IOException | InputDataCreationException e) {
            logger.error(e, e);
            JOptionPane.showMessageDialog(window,
                    "Ошибка при запуске вычислений: " + e.getMessage(),
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        logger.info("Результат вычислений: " + Arrays.toString(processor.calculate()));
    }

    private ProjectSettings extractProjectSetting() throws SettingsExtractionException {
        if (!validateString(window.getProjectPanel().getT0textField().getText(),
                window.getProjectPanel().getT1textField().getText(),
                window.getProjectPanel().getT2textField().getText())) {
            throw new SettingsExtractionException("Неверно заполнено одно из полей");
        }
        double t0;
        double t1;
        double t2;
        try {
            t0 = Double.parseDouble(window.getProjectPanel().getT0textField().getText());
            t1 = Double.parseDouble(window.getProjectPanel().getT1textField().getText());
            t2 = Double.parseDouble(window.getProjectPanel().getT2textField().getText());
        } catch (NumberFormatException ex) {
            logger.error(ex, ex);
            throw new SettingsExtractionException("Неверно заполнено одно из полей");
        }
        String modelType = (String) window.getProjectPanel().getModelTypesComboBox().getSelectedItem();
        logger.info("Выбранный тип: " + modelType);

        return new ProjectSettings(
                modelType,
                t0,
                t1,
                t2,
                1.418439716//пока что константа
        );
    }

    private static boolean validateString(String... field) {
        for (String s : field) {
            if (s == null || s.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
