package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class ScaleTest {
    @Test
    fun `should create metric that scales metric to constant`() {
        // given
        val metric = "*.*.count" scale 5.0

        // then
        metric.asString() shouldEqual "scale(*.*.count, 5.0)"
    }
}