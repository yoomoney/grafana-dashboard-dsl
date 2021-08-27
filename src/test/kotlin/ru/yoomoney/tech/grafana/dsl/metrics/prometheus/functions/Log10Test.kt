package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class Log10Test {
    @Test
    fun `should apply log10 function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().log10()

        // then
        metric.asString() shouldBeEqualTo  "log10(metric_name)"
    }
}