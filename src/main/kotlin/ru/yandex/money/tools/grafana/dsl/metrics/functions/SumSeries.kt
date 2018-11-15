package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

class SumSeries(private val metric: Metric) : Metric {
    override fun asString() = """sumSeries(${metric.asString()})"""
}

fun String.sumSeries() = SumSeries(StringMetric(this))
