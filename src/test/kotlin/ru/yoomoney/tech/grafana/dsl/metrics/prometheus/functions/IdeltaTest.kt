package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class IdeltaTest {
    @Test
    fun `should apply idelta function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().idelta()

        // then
        metric.asString() shouldBeEqualTo  "idelta(metric_name[5s])"
    }
}