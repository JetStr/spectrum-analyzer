package ru.asu.master.core.controllers;

import org.apache.commons.logging.Log;
import ru.asu.master.calculate.MathProcessor;
import ru.asu.master.calculate.model.CalculationResult;
import ru.asu.master.calculate.model.MathModelType;
import ru.asu.master.core.io.IOLayer;
import ru.asu.master.core.io.NotSupportedDataLoaderException;
import ru.asu.master.core.model.InputDataCreationException;
import ru.asu.master.core.model.ProjectSettings;
import ru.asu.master.core.plot.Plotter;
import ru.asu.master.core.plot.PlotterFactory;
import ru.asu.master.core.plot.PlotterHelper;
import ru.asu.master.core.utils.LoggerFactory;
import ru.asu.master.gui.ResultTable;
import ru.asu.master.gui.Window;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainController {
    private static final Log logger = LoggerFactory.createLogger("MainController");
    private final Window window;
    private final List<List<String>> results;
    private final ResultTable table;
    private final IOLayer ioLayer;
    private double[] data;
    private CalculationResult calculate;

    public MainController() {
        this.window = new Window("Spectrum analyzer");
        table = new ResultTable("Таблица результатов");
        results = new ArrayList<>();
        ioLayer = new IOLayer();
        results.addAll(ioLayer.parseSavedFiles());
        init();
    }

    private void init() {
        window.getProjectPanel().getRunButton().addActionListener(event ->
                runCalculations());
        window.getProjectPanel().getPlotButton().addActionListener(event ->
                plot());
        window.getProjectPanel().getResultsButton().addActionListener(event ->
                showResultPanel());
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
        calculate = processor.calculate();
        data = calculate.getData();
        double mnk = calculate.getMnk();
        logger.info("МНК: " + mnk);
        List<String> row = new ArrayList<>();
        BigDecimal bigDecimal = BigDecimal.valueOf(mnk).setScale(2, RoundingMode.HALF_UP);
        row.add(settings.getModelType());
        row.add(String.valueOf(calculate.getT1()));
        row.add(String.valueOf(calculate.getT2()));
        row.add(String.valueOf(calculate.getT3() == 0 ? "" : calculate.getT3()));
        row.add(String.valueOf(bigDecimal.doubleValue()));
        results.add(row);
        ioLayer.saveData(settings.getModelType()
                + addIfNotBlank("_t1", String.valueOf(calculate.getT1()))
                + addIfNotBlank("_t2", String.valueOf(calculate.getT2()))
                + addIfNotBlank("_t3", String.valueOf(calculate.getT3() == 0 ? "" : calculate.getT3()))
                + "_mnk" + bigDecimal.doubleValue(), data);
    }

    private void plot() {
        if (calculate == null) {
            JOptionPane.showMessageDialog(window,
                    "Необходимо запустить расчёты",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        double[] y;
        double[] x;
        String plotName;
        if (calculate.getT3() == 0) {
            y = new double[]{0, 0.157437757, 0.253889924, 0.298819145, 0.300384697, 0.265623279, 0.200603601, 0.110558795, 0};
            x = new double[9];
            double step = Math.abs(calculate.getT2() - calculate.getT1()) / 9;
            x[0] = calculate.getT1();
            x[8] = calculate.getT2();
            for (int i = 1; i < 9; i++) {
                x[i] = x[i - 1] + step;
            }
            plotName = "Вид распределения: Параболическое\n" +
                    "t1: " + calculate.getT1() + "\n" +
                    "t2: " + calculate.getT2() + "\n" +
                    "МНК: " + calculate.getMnk();
        } else {
            y = new double[]{0, 0.107421943, 0.205268334, 0.294380208, 0.225310627, 0.161772742, 0.103311298, 0.04951254, 0};
            x = new double[9];
            double step = Math.abs(calculate.getT3() - calculate.getT1()) / 9;
            x[0] = calculate.getT1();
            x[8] = calculate.getT3();
            for (int i = 1; i < 9; i++) {
                x[i] = x[i - 1] + step;
            }
            plotName = "Вид распределения: Треугольное\n" +
                    "t1: " + calculate.getT1() + "\n" +
                    "t2: " + calculate.getT2() + "\n" +
                    "t3: " + calculate.getT3() + "\n" +
                    "МНК: " + calculate.getMnk();
        }
        Plotter plotter = PlotterFactory.getPlotter(PlotterFactory.X_CHART_PLOTTER, plotName, y, x);
        plotter.plot();
    }

    private ProjectSettings extractProjectSetting() throws SettingsExtractionException {
        if (!validateString(window.getProjectPanel().getT1textField().getText(),
                window.getProjectPanel().getT2textField().getText())) {
            throw new SettingsExtractionException("Неверно заполнено одно из полей");
        }
        String modelType = (String) window.getProjectPanel().getModelTypesComboBox().getSelectedItem();
        logger.info("Выбранный тип: " + modelType);
        double t1;
        double t2;
        double t3;
        try {
            t1 = Double.parseDouble(window.getProjectPanel().getT1textField().getText());
            t2 = Double.parseDouble(window.getProjectPanel().getT2textField().getText());
            t3 = Double.parseDouble(
                    Objects.equals(MathModelType.TRIANGLE, modelType) ?
                            window.getProjectPanel().getT3textField().getText() : "0");
        } catch (NumberFormatException ex) {
            logger.error(ex, ex);
            throw new SettingsExtractionException("Неверно заполнено одно из полей");
        }

        return new ProjectSettings(
                modelType,
                t1,
                t2,
                t3,
                1.418439716//пока что константа
        );
    }

    private void showResultPanel() {
        if (results.isEmpty()) {
            logger.info("Таблица результатов пуста");
            JOptionPane.showMessageDialog(window,
                    "Таблица результатов пуста, необходимо запустить вычисления",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        table.refreshModel(results);
    }

    private static boolean validateString(String... field) {
        for (String s : field) {
            if (s == null || s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static String addIfNotBlank(String prefix, String string) {
        if (string == null || string.isEmpty()) {
            return "";
        }
        return prefix + string;
    }
}
