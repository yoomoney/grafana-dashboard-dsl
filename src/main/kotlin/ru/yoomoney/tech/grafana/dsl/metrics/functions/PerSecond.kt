package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * Generator for perSecond function for Graphite.
 * Represents packets of metric per second if packets are higher.
 *
 * @author Aleksey Antufev
 * @since 05.09.2019
 */
class PerSecond(private val metric: Metric) : Metric {
    override fun asString() = "perSecond(${metric.asString()})"
}

fun String.perSecond() = PerSecond(StringMetric(this))

fun Metric.perSecond() = PerSecond(this)
