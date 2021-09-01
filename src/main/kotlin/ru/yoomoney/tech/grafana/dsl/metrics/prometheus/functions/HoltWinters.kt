package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `holt_winters(v range-vector, sf scalar, tf scalar)` produces a smoothed value for time series based on
 * the range in `v`. The lower the smoothing factor `sf`, the more importance is given to old data.
 * The higher the trend factor `tf`, the more trends in the data is considered.
 * Both `sf` and `tf` must be between 0 and 1.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#holt_winters)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class HoltWinters internal constructor(
    private val metric: RangeVectorTypedMetric,
    private val smoothingFactor: Double,
    private val trendFactor: Double
) : InstantVectorTypedMetric {
    override fun asString() = "holt_winters(${metric.asString()}, $smoothingFactor, $trendFactor)"
}

/**
 * Produce a smoothed value for time series based on the range in [this] vector.
 *
 * The lower the [smoothingFactor], the more importance is given to old data.
 * The higher the [trendFactor], the more trends in the data is considered.
 * Both [smoothingFactor] and [trendFactor] must be between 0 and 1
 */
fun RangeVectorTypedMetric.holtWinters(smoothingFactor: Double, trendFactor: Double): InstantVectorTypedMetric {
    if (smoothingFactor < 0 || smoothingFactor > 1) {
        throw IllegalArgumentException("smoothingFactor must be greater than or equal to 0 and less than or equal to 1")
    }
    if (trendFactor < 0 || trendFactor > 1) {
        throw IllegalArgumentException("trendFactor be greater than or equal to 0 and less than or equal to 1")
    }
    return HoltWinters(this, smoothingFactor, trendFactor)
}