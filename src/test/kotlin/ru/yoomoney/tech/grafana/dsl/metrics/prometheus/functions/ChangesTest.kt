package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class ChangesTest {
    @Test
    fun `should apply changes function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().changes()

        // then
        metric.asString() shouldBeEqualTo  "changes(metric_name[5s])"
    }
}