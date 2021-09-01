package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `clamp(v instant-vector, min scalar, max scalar)` clamps the sample values of all elements in `v` to have
 * a lower limit of `min` and an upper limit of `max`.
 *
 * Special cases:
 * * Return an empty vector if `min > max`
 * * Return `NaN` if `min` or `max` is `NaN`
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#clamp)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Clamp internal constructor(
    private val metric: InstantVectorTypedMetric,
    private val min: Double,
    private val max: Double
) : InstantVectorTypedMetric {
    override fun asString() = "clamp(${metric.asString()}, $min, $max)"
}

/**
 * Clamp the sample values of all elements in [this] vector to have a lower limit
 * of [min] and an upper limit of [max].
 */
fun InstantVectorTypedMetric.clamp(min: Double, max: Double): InstantVectorTypedMetric = Clamp(this, min, max)