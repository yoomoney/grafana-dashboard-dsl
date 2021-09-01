package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `floor(v instant-vector)` rounds the sample values of all elements in `v` down to the nearest integer.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#floor)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Floor internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "floor(${metric.asString()})"
}

/**
 * Round the sample values of all elements in [this] vector down to the nearest integer
 */
fun InstantVectorTypedMetric.floor(): InstantVectorTypedMetric = Floor(this)