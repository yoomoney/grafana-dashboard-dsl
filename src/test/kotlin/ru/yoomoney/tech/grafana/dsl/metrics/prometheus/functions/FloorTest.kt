package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class FloorTest {
    @Test
    fun `should apply floor function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().floor()

        // then
        metric.asString() shouldBeEqualTo  "floor(metric_name)"
    }
}