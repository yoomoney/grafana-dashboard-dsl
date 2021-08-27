package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class AbsentOverTimeTest {
    @Test
    fun `should apply absent_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().absentOverTime()

        // then
        metric.asString() shouldBeEqualTo  "absent_over_time(metric_name[5s])"
    }
}