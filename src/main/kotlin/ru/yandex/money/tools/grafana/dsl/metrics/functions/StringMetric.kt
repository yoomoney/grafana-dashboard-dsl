package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

class StringMetric(private val metric: String) : Metric {
    override fun asString() = metric
}
