package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class ResetsTest {
    @Test
    fun `should apply resets function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().resets()

        // then
        metric.asString() shouldBeEqualTo  "resets(metric_name[5s])"
    }
}