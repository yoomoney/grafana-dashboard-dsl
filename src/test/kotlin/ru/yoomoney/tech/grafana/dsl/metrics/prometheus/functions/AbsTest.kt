package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class AbsTest {
    @Test
    fun `should apply abs function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().abs()

        // then
        metric.asString() shouldBeEqualTo  "abs(metric_name)"
    }
}