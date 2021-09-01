package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class DeltaTest {
    @Test
    fun `should apply delta function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().delta()

        // then
        metric.asString() shouldBeEqualTo  "delta(metric_name[5s])"
    }
}