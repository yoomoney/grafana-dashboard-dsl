package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class DerivTest {
    @Test
    fun `should apply deriv function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().deriv()

        // then
        metric.asString() shouldBeEqualTo  "deriv(metric_name[5s])"
    }
}