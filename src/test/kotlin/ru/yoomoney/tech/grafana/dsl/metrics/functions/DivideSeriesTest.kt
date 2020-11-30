package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class DivideSeriesTest {
    @Test
    fun `should create metric that divides two series`() {
        // given
        val metric = "*.first.count" divideSeries "*.second.count"

        // then
        metric.asString() shouldEqual "divideSeries(*.first.count, *.second.count)"
    }
}