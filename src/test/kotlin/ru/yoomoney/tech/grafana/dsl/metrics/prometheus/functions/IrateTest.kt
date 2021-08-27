package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class IrateTest {
    @Test
    fun `should apply irate function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().irate()

        // then
        metric.asString() shouldBeEqualTo  "irate(metric_name[5s])"
    }
}