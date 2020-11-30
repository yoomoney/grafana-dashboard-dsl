package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * Generator for averageSeries function for graphite. Returns average value for metric for each point on X-axis
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
class AverageSeries(private val metric: Metric) : Metric {
    override fun asString() = """averageSeries(${metric.asString()})"""
}

fun String.averageSeries() = AverageSeries(StringMetric(this))

fun Metric.averageSeries() = AverageSeries(this)
