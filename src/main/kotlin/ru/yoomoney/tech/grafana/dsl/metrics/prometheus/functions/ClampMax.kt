package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `clamp_max(v instant-vector, max scalar)` clamps the sample values of
 * all elements in `v` to have an upper limit of `max`.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#clamp_max)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class ClampMax internal constructor(
    private val metric: InstantVectorTypedMetric,
    private val max: Double
) : InstantVectorTypedMetric {
    override fun asString() = "clamp_max(${metric.asString()}, $max)"
}

/**
 * Clamp the sample values of all elements in [this] vector to have an upper limit of [max]
 */
fun InstantVectorTypedMetric.clampMax(max: Double): InstantVectorTypedMetric = ClampMax(this, max)