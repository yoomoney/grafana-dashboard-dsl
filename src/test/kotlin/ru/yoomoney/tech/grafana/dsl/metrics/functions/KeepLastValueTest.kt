package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class KeepLastValueTest {

    @Test
    fun `should create metric with keepLastValue function by raw string metric`() {
        // given
        val metric = "*.first.count" keepLastValue 10

        // then
        metric.asString() shouldEqual "keepLastValue(*.first.count, 10)"
    }

    @Test
    fun `should create metric with keepLastValue function by metric object`() {
        // given
        val metric = StringMetric("*.first.count") keepLastValue 10

        // then
        metric.asString() shouldEqual "keepLastValue(*.first.count, 10)"
    }
}