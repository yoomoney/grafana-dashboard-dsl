package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class GroupTest {
    @Test
    fun `should create metric that group metrics`() {
        // given
        val metric = "*.request.count".group(StringMetric("*.response.upper"))

        // then
        metric.asString() shouldEqual "group(*.request.count,*.response.upper)"
    }
}