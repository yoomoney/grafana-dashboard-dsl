package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class AbsentTest {
    @Test
    fun `should apply absent function to prometheusMetric`() {
        // given
        val metric = "metric_name".asInstantVector().absent()

        // then
        metric.asString() shouldBeEqualTo  "absent(metric_name)"
    }
}