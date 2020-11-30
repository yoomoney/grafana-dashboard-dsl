package ru.yoomoney.tech.grafana.dsl.metrics

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