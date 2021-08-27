package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class Log2Test {
    @Test
    fun `should apply log2 function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().log2()

        // then
        metric.asString() shouldBeEqualTo  "log2(metric_name)"
    }
}