package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `ceil(v instant-vector)` rounds the sample values of all elements in `v` up to the nearest integer.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#ceil)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Ceil internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "ceil(${metric.asString()})"
}

/**
 * Round the sample values of all elements in [this] vector up to the nearest integer
 */
fun InstantVectorTypedMetric.ceil(): InstantVectorTypedMetric = Ceil(this)