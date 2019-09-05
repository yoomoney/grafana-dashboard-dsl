package ru.yandex.money.tools.grafana.dsl.panels.graph.display.seriesoverrides

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class SeriesOverrideTest {

    @Test
    fun `should create custom series override`() {

        val seriesOverride = SeriesOverride(
                alias = "total",
                bars = false,
                lines = true,
                stack = false,
                lineWidth = 2
        )
        seriesOverride.toJson().toString() shouldEqualToJson jsonFile("CustomSeriesOverride.json")
    }
}