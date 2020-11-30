package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * Derivative graphite function. This is the opposite of the integral function.
 * This is useful for taking a running total metric and calculating the delta between subsequent data points.
 *
 * [derivative](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.derivative)
 *
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class Derivative internal constructor(private val seriesList: Metric) : Metric {
    override fun asString() = "derivative(${seriesList.asString()})"
}

fun String.derivative() = Derivative(StringMetric(this))

fun Metric.derivative() = Derivative(this)
