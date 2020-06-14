package ru.asu.master

import ru.asu.master.calculate.model.ParabolaModel
import ru.asu.master.calculate.model.TriangleModel
import ru.asu.master.core.io.IOLayer
import ru.asu.master.core.model.InputData
import ru.asu.master.core.plot.Plotter
import ru.asu.master.core.plot.PlotterFactory
import ru.asu.master.core.plot.PlotterHelper
import ru.asu.master.gui.ResultTable
import spock.lang.Ignore
import spock.lang.Specification

import java.math.RoundingMode

@Ignore
class IOTest extends Specification {
    def "testLoader"() {
        setup:
        def layer = new IOLayer()
        print(layer.getInputData("testInputs.xlsx"))
    }

    def "triangle test"() {
        setup:
        println(new ParabolaModel("parabola",
                3000, 700, 0,
                new IOLayer().getInputData("testInputs.xlsx", "idealSpectrum.xlsx"),
                1.418439716).calculate())
    }

    @Ignore
    def "test plot"() {
        setup:
        def layer = new IOLayer()
        InputData inputData = layer.getInputData("fulltestInputs.xlsx", "idealParabola.xlsx")
        double[] data = new TriangleModel("tri", inputData, 4000, 500, 4500, 1.418439716).calculate()
        Plotter plotter = PlotterFactory.getPlotter(PlotterFactory.X_CHART_PLOTTER, data, PlotterHelper.getTemperatureRange(
                4000, 4500, data.length
        ));
        plotter.plot();
        Thread.sleep(5000)
    }

    def "test table"() {
        setup:
        ResultTable resultTable = new ResultTable("res")
        resultTable.refreshModel([["Треугольное", "1", "2", "3", "345"], ["парабола", "2", "3", "5", "666"]])
        Thread.sleep(5000)
    }
}
