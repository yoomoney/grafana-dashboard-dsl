package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `absent_over_time(v range-vector)` returns an empty vector if the range vector passed to
 * it has any elements and a 1-element vector with the value 1 if the range vector passed to it has no elements.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#absent_over_time)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class AbsentOverTime internal constructor(
    private val metric: RangeVectorTypedMetric
) : InstantVectorTypedMetric {
    override fun asString() = "absent_over_time(${metric.asString()})"
}

/**
 * Convert to an empty vector if [this] has any elements and a 1-element vector
 * with the value 1 if the [this] has no elements
 */
fun RangeVectorTypedMetric.absentOverTime(): InstantVectorTypedMetric = AbsentOverTime(this)