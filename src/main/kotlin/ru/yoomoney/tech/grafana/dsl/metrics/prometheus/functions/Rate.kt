package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `rate(v range-vector)` calculates the per-second average rate of increase of the time series
 * in the range vector.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#rate)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Rate internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "rate(${metric.asString()})"
}

/**
 * Calculate the per-second average rate of increase of [this] time series in the range vector
 */
fun RangeVectorTypedMetric.rate(): InstantVectorTypedMetric = Rate(this)