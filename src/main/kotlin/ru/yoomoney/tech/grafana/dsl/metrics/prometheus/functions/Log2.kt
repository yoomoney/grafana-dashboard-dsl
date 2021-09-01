package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `log2(v instant-vector)` calculates the binary logarithm for all elements in `v`.
 *
 * Special cases are:
 * * ln(+Inf) = +Inf
 * * ln(0) = -Inf
 * * ln(x < 0) = NaN
 * * ln(NaN) = NaN
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#log2)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Log2 internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "log2(${metric.asString()})"
}

/**
 * Calculate the binary logarithm for all elements in [this] vector
 */
fun InstantVectorTypedMetric.log2(): InstantVectorTypedMetric = Log2(this)