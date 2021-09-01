package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `delta(v range-vector)` calculates the difference between the first and last value of each time series
 * element in a range vector `v`, returning an instant vector with the given deltas and equivalent labels.
 *
 * The delta is extrapolated to cover the full time range as specified in the range vector selector,
 * so that it is possible to get a non-integer result even if the sample values are all integers.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#delta)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Delta internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "delta(${metric.asString()})"
}

/**
 * Calculate the difference between the first and last value of each time series element in [this] vector
 */
fun RangeVectorTypedMetric.delta(): InstantVectorTypedMetric = Delta(this)