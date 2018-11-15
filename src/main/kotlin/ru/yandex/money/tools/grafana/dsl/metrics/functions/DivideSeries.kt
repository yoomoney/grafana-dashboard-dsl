package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

class DivideSeries(private val firstMetric: Metric, private val secondMetric: Metric) : Metric {
    override fun asString() = """divideSeries(${firstMetric.asString()}, ${secondMetric.asString()})"""
}

infix fun Metric.divideSeries(anotherMetric: Metric) = DivideSeries(this, anotherMetric)

infix fun String.divideSeries(anotherMetric: String) = DivideSeries(StringMetric(this), StringMetric(anotherMetric))
