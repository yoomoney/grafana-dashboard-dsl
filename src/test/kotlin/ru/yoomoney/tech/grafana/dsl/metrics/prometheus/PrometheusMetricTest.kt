package ru.yoomoney.tech.grafana.dsl.metrics.prometheus

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test

class PrometheusMetricTest {
    @Test
    fun `builder should create instantVector`() {
        val instantVector = instantVector(
            metricName = "metricName",
            labels = linkedMapOf("label_1" to "value_1", "label_2" to "value_2")
        )

        instantVector.asString() shouldBeEqualTo """metricName{label_1="value_1", label_2="value_2"}"""
    }

    @Test
    fun `builder should create instantVector with regex-match`() {
        val instantVector = instantVector(
                metricName = "metricName",
                labels = LableMatcher(equalsLabels = linkedMapOf("label_1" to "value_1", "label_2" to "value_2"),
                        regexMatchLabels = linkedMapOf("label_reg" to ".*"))
        )

        instantVector.asString() shouldBeEqualTo """metricName{label_1="value_1", label_2="value_2", label_reg=~".*"}"""
    }

    @Test
    fun `builder should create rangeVector`() {
        val instantVector = rangeVector(
            metricName = "metricName",
            labels = linkedMapOf("label_1" to "value_1", "label_2" to "value_2"),
            interval = "1h"
        )

        instantVector.asString() shouldBeEqualTo """metricName{label_1="value_1", label_2="value_2"}[1h]"""
    }

    @Test
    fun `builder should create rangeVector with regex-match`() {
        val instantVector = rangeVector(
                metricName = "metricName",
                labels = LableMatcher(equalsLabels = linkedMapOf("label_1" to "value_1", "label_2" to "value_2"),
                        regexMatchLabels = linkedMapOf("label_reg" to ".*")),
                interval = "1h"
        )

        instantVector.asString() shouldBeEqualTo """metricName{label_1="value_1", label_2="value_2", label_reg=~".*"}[1h]"""
    }

    @Test
    fun `builder should create rangeVector with some matchers`() {
        val instantVector = rangeVector(
                metricName = "metricName",
                labels = LableMatcher(equalsLabels = linkedMapOf("label_1" to "value_1", "label_2" to "value_2"),
                        regexMatchLabels = linkedMapOf("label_reg" to ".*"),
                        notEqualsLabels = linkedMapOf("label_not" to "value_3"),
                        notRegexMatchLabels = linkedMapOf("label_not_match" to ".*not.*")),
                interval = "1h"
        )

        instantVector.asString() shouldBeEqualTo
                """metricName{label_1="value_1", label_2="value_2", label_reg=~".*", label_not!="value_3", label_not_match!~".*not.*"}[1h]"""
    }
}