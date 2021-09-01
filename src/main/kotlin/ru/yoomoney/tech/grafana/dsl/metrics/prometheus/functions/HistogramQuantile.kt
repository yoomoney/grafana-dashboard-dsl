package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `histogram_quantile(φ scalar, b instant-vector)` calculates the `φ-quantile` (0 ≤ φ ≤ 1)
 * from the buckets `b` of a histogram.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#histogram_quantile)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class HistogramQuantile internal constructor(
    private val metric: InstantVectorTypedMetric,
    private val quantile: Double
) : InstantVectorTypedMetric {
    override fun asString() = "histogram_quantile($quantile, ${metric.asString()})"
}

/**
 * Calculate the φ-quantile (0 ≤ φ ≤ 1) from the [this] vector's buckets of a histogram
 */
fun InstantVectorTypedMetric.histogramQuantile(quantile: Double): InstantVectorTypedMetric {
    if (quantile < 0 || quantile > 1) {
        throw IllegalArgumentException("quantile must be greater than or equal to 0 and less than or equal to 1")
    }
    return HistogramQuantile(this, quantile)
}