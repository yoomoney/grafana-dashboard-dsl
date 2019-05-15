package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class SumSeriesTest {
    @Test
    fun `should create metric that sum metrics at each datapoint`() {
        // given
        val metric = "*.*.count".sumSeries()

        // then
        metric.asString() shouldEqual "sumSeries(*.*.count)"
    }
}