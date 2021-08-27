package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Functions aggregate each series of a given range vector over time
 * and return an instant vector with per-series aggregation results.
 *
 * Note that all values in the specified interval have the same weight in the aggregation
 * even if the values are not equally spaced throughout the interval.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#aggregation_over_time)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class AggregationOverTime internal constructor(
    private val metric: RangeVectorTypedMetric,
    private val aggregationType: AggregationType,
    private val parameters: List<Any> = emptyList()
) : InstantVectorTypedMetric {
    override fun asString() = if (parameters.isNotEmpty()) {
        "${aggregationType.value}_over_time(${parameters.joinToString(",")}, ${metric.asString()})"
    } else {
        "${aggregationType.value}_over_time(${metric.asString()})"
    }
}

/**
 * Average value of all points in the specified interval
 */
fun RangeVectorTypedMetric.avgOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.AVG)
}

/**
 * Minimum value of all points in the specified interval
 */
fun RangeVectorTypedMetric.minOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.MIN)
}

/**
 * Maximum value of all points in the specified interval
 */
fun RangeVectorTypedMetric.maxOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.MAX)
}

/**
 * Sum of all values in the specified interval
 */
fun RangeVectorTypedMetric.sumOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.SUM)
}

/**
 * Count of all values in the specified interval
 */
fun RangeVectorTypedMetric.countOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.COUNT)
}

/**
 * Population standard deviation of the values in the specified interval
 */
fun RangeVectorTypedMetric.stddevOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.STDDEV)
}

/**
 * Population standard variance of the values in the specified interval
 */
fun RangeVectorTypedMetric.stdvarOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.STDVAR)
}

/**
 * Most recent point value in specified interval
 */
fun RangeVectorTypedMetric.lastOverTime(): InstantVectorTypedMetric {
    return AggregationOverTime(this, AggregationType.LAST)
}

/**
 * Value 1 for any series in the specified interval
 */
fun RangeVectorTypedMetric.presentOverTime(): InstantVectorTypedMetric{
    return AggregationOverTime(this, AggregationType.PRESENT)
}

/**
 * φ-quantile (0 ≤ φ ≤ 1) of the values in the specified interval
 */
fun RangeVectorTypedMetric.quantileOverTime(quantile: Double): InstantVectorTypedMetric {
    if (quantile < 0 || quantile > 1) {
        throw IllegalArgumentException("quantile must be greater than or equal to 0 and less than or equal to 1")
    }
    return AggregationOverTime(this, AggregationType.QUANTILE, listOf(quantile))
}

internal enum class AggregationType(val value: String) {
    AVG("avg"),
    MIN("min"),
    MAX("max"),
    SUM("sum"),
    COUNT("count"),
    QUANTILE("quantile"),
    STDDEV("stddev"),
    STDVAR("stdvar"),
    LAST("last"),
    PRESENT("present"),
    ;
}