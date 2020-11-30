package ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

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