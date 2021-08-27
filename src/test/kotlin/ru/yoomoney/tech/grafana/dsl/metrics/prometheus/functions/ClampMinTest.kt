package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class ClampMinTest {
    @Test
    fun `should apply clamp_min function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().clampMin(10.0)

        // then
        metric.asString() shouldBeEqualTo  "clamp_min(metric_name, 10.0)"
    }
}