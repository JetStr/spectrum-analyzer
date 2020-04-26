package ru.asu.master

import ru.asu.master.calculate.model.TriangleModel
import ru.asu.master.core.io.IOLayer
import ru.asu.master.core.model.InputData
import spock.lang.Specification

import java.math.RoundingMode

class IOTest extends Specification {
    def "testLoader"() {
        setup:
        def layer = new IOLayer()
        print(layer.getInputData("testInputs.xlsx"))
    }

    def "triangle test"() {
        setup:
        double[] input = [447.3641185,
                          447.5459366,
                          447.7277673,
                          447.9096106,
                          448.0914665,
                          448.2733349,
                          448.455216,
                          448.6371096,
                          448.8190158,
                          449.0009346,
                          449.182866,
                          449.3648099,
                          449.5467664
        ]
        double[] signals = [
                5316.252579,
                5346.833929,
                5363.111706,
                5427.594246,
                5440.461905,
                5419.609722,
                5444.332143,
                5486.251389,
                5491.25119,
                5489.701786,
                5485.045238,
                5475.395833,
                5523.74504

        ]
        double[] expected = [0.011400206,
                             0.011434706,
                             0.01146928,
                             0.01150393,
                             0.011538654,
                             0.011573454,
                             0.011608329,
                             0.01164328,
                             0.011678306,
                             0.011713408,
                             0.011748585,
                             0.011783838,
                             0.011819167
        ]
        when:
        def model = new TriangleModel("triangle", new InputData(input, signals), 4000, 500, 4500, 1.418439716)
        def calculate = model.calculate()
        for (int i = 0; i < calculate.length; i++) {
            def of = BigDecimal.valueOf(calculate[i])
            of = of.setScale(9, RoundingMode.HALF_UP);
            calculate[i] = of.doubleValue();
        }
        print(calculate)
        then:
        calculate == expected
    }
}
