package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class PredictLinearTest {
    @Test
    fun `should apply abs function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().predictLinear(15)

        // then
        metric.asString() shouldBeEqualTo  "predict_linear(metric_name[5s], 15)"
    }
}