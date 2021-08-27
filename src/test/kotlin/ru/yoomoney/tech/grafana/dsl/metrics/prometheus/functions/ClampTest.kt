package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class ClampTest {
    @Test
    fun `should apply clamp function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().clamp(10.0, 20.0)

        // then
        metric.asString() shouldBeEqualTo  "clamp(metric_name, 10.0, 20.0)"
    }
}