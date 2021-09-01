package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * For each input time series, `changes(v range-vector)` returns the number of times its value has changed within
 * the provided time range as an instant vector
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#changes)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Changes internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "changes(${metric.asString()})"
}

/**
 * Return the number of times [this] vector's value has changed within the provided time range as an instant vector
 */
fun RangeVectorTypedMetric.changes(): InstantVectorTypedMetric = Changes(this)