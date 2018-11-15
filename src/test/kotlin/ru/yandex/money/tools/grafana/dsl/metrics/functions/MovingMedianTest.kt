package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test


class MovingMedianTest {
    @Test
    fun `should create metric that gets median of metric`() {
        // given
        val metric = "*.*.count"  movingMedian 30

        // then
        metric.asString() shouldEqual "movingMedian(*.*.count, '30min')"
    }
}