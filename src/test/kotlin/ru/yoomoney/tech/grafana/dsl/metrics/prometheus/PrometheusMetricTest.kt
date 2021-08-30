package ru.yoomoney.tech.grafana.dsl.metrics.prometheus

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class PrometheusMetricTest {
    @Test
    fun `builder should create instantVector`() {
        val instantVector = instantVector(
            metricName = "metricName",
            aliases = linkedMapOf("label_1" to "value_1", "label_2" to "value_2")
        )

        instantVector.asString() shouldBeEqualTo """metricName{label_1="value_1", label_2="value_2"}"""
    }

    @Test
    fun `builder should create rangeVector`() {
        val instantVector = rangeVector(
            metricName = "metricName",
            aliases = linkedMapOf("label_1" to "value_1", "label_2" to "value_2"),
            interval = "1h"
        )

        instantVector.asString() shouldBeEqualTo """metricName{label_1="value_1", label_2="value_2"}[1h]"""
    }
}