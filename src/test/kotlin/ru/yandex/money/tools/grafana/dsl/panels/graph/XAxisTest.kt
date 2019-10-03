package ru.yandex.money.tools.grafana.dsl.panels.graph

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.Histogram
import ru.yandex.money.tools.grafana.dsl.panels.Series
import ru.yandex.money.tools.grafana.dsl.panels.XAxis
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

/**
 * [XAxis] Test
 * @author Aleksandr Korkin
 * @since 01.10.2019
 */
class XAxisTest {
    @Test
    fun `should create xAxis in time mode`() {

        val xAxis = XAxis()

        xAxis.toJson().toString() shouldEqualToJson jsonFile("XAxisTime.json")
    }

    @Test
    fun `should create xAxis in series mode`() {

        val xAxis = XAxis(show = false, mode = Series(value = Series.ValueType.COUNT))

        xAxis.toJson().toString() shouldEqualToJson jsonFile("XAxisSeries.json")
    }

    @Test
    fun `should create xAxis in histogram mode`() {

        val xAxis = XAxis(mode = Histogram(buckets = 10))

        xAxis.toJson().toString() shouldEqualToJson jsonFile("XAxisHistogram.json")
    }
}
