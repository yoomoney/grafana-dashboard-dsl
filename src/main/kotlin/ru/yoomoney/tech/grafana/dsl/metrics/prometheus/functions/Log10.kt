package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `log10(v instant-vector)` calculates the decimal logarithm for all elements in `v`.
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
internal class Log10 internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "log10(${metric.asString()})"
}

/**
 * Calculate the decimal logarithm for all elements in [this] vector
 */
fun InstantVectorTypedMetric.log10(): InstantVectorTypedMetric = Log10(this)