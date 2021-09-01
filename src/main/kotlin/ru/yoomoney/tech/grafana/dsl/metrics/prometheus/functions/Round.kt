package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `round(v instant-vector, to_nearest=1 scalar)` rounds the sample values of all elements
 * in `v` to the nearest integer. Ties are resolved by rounding up.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#round)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Round internal constructor(
    private val metric: InstantVectorTypedMetric,
    private val nearestMultiple: Double
) : InstantVectorTypedMetric {
    override fun asString() = "round(${metric.asString()}, $nearestMultiple)"
}

/**
 * Round the sample values of all elements in [this] vector to the nearest integer
 */
fun InstantVectorTypedMetric.round(nearestMultiple: Double): InstantVectorTypedMetric = Round(this, nearestMultiple)