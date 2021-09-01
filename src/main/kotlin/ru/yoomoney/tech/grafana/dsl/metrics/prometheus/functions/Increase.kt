package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `increase(v range-vector)` calculates the increase in the time series in the range vector.
 * Breaks in monotonicity (such as counter resets due to target restarts) are automatically adjusted for.
 * The increase is extrapolated to cover the full time range as specified in the range vector selector,
 * so that it is possible to get a non-integer result even if a counter increases only by integer increments.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#increase)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Increase internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "increase(${metric.asString()})"
}

/**
 * Calculate the increase in time series in [this] range vector
 */
fun RangeVectorTypedMetric.increase(): InstantVectorTypedMetric = Increase(this)