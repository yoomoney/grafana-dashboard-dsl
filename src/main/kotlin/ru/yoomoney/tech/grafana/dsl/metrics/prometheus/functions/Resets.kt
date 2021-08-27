package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * For each input time series, resets(v range-vector) returns the number of counter resets within the provided time
 * range as an instant vector.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#resets)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Resets internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "resets(${metric.asString()})"
}

/**
 * Return the number of counter resets within [this] time range as an instant vector
 */
fun RangeVectorTypedMetric.resets(): InstantVectorTypedMetric = Resets(this)