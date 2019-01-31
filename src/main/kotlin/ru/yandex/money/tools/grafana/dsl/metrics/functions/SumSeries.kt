package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Generator for sumSeries function for graphite. sumSeries combines all metrics and returns sum at each point.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class SumSeries(private val metric: Metric) : Metric {
    override fun asString() = """sumSeries(${metric.asString()})"""
}

fun String.sumSeries() = SumSeries(StringMetric(this))

fun Metric.sumSeries() = SumSeries(this)
