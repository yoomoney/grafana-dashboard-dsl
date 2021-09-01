package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `irate(v range-vector)` calculates the per-second instant rate of increase
 * of the time series in the range vector. This is based on the last two data points.
 * Breaks in monotonicity (such as counter resets due to target restarts) are automatically adjusted for.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#irate)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Irate internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "irate(${metric.asString()})"
}

/**
 * Calculate the per-second instant rate of increase of the time series in [this] range vector
 */
fun RangeVectorTypedMetric.irate(): InstantVectorTypedMetric = Irate(this)