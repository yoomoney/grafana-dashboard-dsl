package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

class StringMetric(private val metric: String) : Metric {
    override fun asString() = metric
}
