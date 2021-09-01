package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `idelta(v range-vector)` calculates the difference between the last two samples in the range vector `v`,
 * returning an instant vector with the given deltas and equivalent labels. idelta should only be used with gauges.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#idelta)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class Idelta internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "idelta(${metric.asString()})"
}

/**
 * Calculate the difference between the last two samples in [this] range vector, returning an instant vector with
 * the given deltas and equivalent labels
 */
fun RangeVectorTypedMetric.idelta(): InstantVectorTypedMetric = Idelta(this)