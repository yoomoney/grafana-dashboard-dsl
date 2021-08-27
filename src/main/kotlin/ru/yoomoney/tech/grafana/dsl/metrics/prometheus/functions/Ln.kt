package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Function `ln(v instant-vector)` calculates the natural logarithm for all elements in `v`.
 *
 * Special cases are:
 * * ln(+Inf) = +Inf
 * * ln(0) = -Inf
 * * ln(x < 0) = NaN
 * * ln(NaN) = NaN
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#ln)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Ln internal constructor(
    private val metric: InstantVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "ln(${metric.asString()})"
}

/**
 * Calculate the natural logarithm for all elements in [this] vector
 */
fun InstantVectorTypedMetric.ln(): InstantVectorTypedMetric = Ln(this)