package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class LnTest {
    @Test
    fun `should apply ln function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().ln()

        // then
        metric.asString() shouldBeEqualTo  "ln(metric_name)"
    }
}