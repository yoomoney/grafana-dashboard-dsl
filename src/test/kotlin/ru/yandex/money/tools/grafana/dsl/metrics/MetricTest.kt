package ru.yandex.money.tools.grafana.dsl.metrics

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class MetricTest {

    @Test
    fun `should create empty JSON array`() {
        // given
        val metrics = Metrics(emptyList())

        // then
        metrics.toJson().toString() shouldEqual "[]"
    }
}