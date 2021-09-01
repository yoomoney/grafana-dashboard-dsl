package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `abs(v instant-vector)` returns the input vector with all sample values converted to their absolute value.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#abs)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Abs internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "abs(${metric.asString()})"
}

/**
 * Convert [this] vector with all sample values to their absolute value
 */
fun InstantVectorTypedMetric.abs(): InstantVectorTypedMetric = Abs(this)