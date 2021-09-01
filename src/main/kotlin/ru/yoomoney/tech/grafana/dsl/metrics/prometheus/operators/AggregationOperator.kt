package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.operators

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Aggregation operators that can be used to aggregate the elements of a single instant vector,
 * resulting in a new vector of fewer elements with aggregated values
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/operators/#aggregation-operators)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class AggregationOperator internal constructor(
    private val metric: InstantVectorTypedMetric,
    private val aggregationType: AggregationType,
    private var by: List<String> = emptyList(),
    private var without: List<String> = emptyList(),
    private val parameters: List<Any> = emptyList()
) : InstantVectorTypedMetric {

    override fun asString(): String {
        val operatorName = aggregationType.value

        val labelClause = when {
            by.isNotEmpty() -> " by (" + by.joinToString(", ") + ") "
            without.isNotEmpty() -> " without (" + without.joinToString(", ") + ") "
            else -> ""
        }

        return if (parameters.isEmpty()) {
            operatorName + labelClause + "(${metric.asString()})"
        } else {
            operatorName + labelClause + "(${parameters.joinToString(", ")}, ${metric.asString()})"
        }
    }
}

/**
 * Calculate sum over dimensions
 */
fun InstantVectorTypedMetric.sum(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.SUM, by, without)

/**
 * Select minimum over dimensions
 */
fun InstantVectorTypedMetric.min(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.MIN, by, without)

/**
 * Select maximum over dimensions
 */
fun InstantVectorTypedMetric.max(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.MAX, by, without)

/**
 * Calculate the average over dimensions
 */
fun InstantVectorTypedMetric.avg(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.AVG, by, without)

/**
 * All values in the resulting vector are 1
 */
fun InstantVectorTypedMetric.group(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.GROUP, by, without)

/**
 * Calculate population standard deviation over dimensions
 */
fun InstantVectorTypedMetric.stddev(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.STDDEV, by, without)

/**
 * Calculate population standard variance over dimensions
 */
fun InstantVectorTypedMetric.stdvar(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.STDVAR, by, without)

/**
 * Count number of elements in the vector
 */
fun InstantVectorTypedMetric.countValues(
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.COUNT_VALUES, by, without)

/**
 * Count number of elements with the same value
 */
fun InstantVectorTypedMetric.bottomk(
    k: Int,
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.BOTTOMK, by, without, listOf(k))

/**
 * Smallest k elements by sample value
 */
fun InstantVectorTypedMetric.topk(
    k: Int,
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.TOPK, by, without, listOf(k))

/**
 * Largest k elements by sample value
 */
fun InstantVectorTypedMetric.quantile(
    quantile: Double,
    by: List<String> = emptyList(),
    without: List<String> = emptyList()
): InstantVectorTypedMetric = AggregationOperator(this, AggregationType.QUANTILE, by, without, listOf(quantile))

internal enum class AggregationType(val value: String) {
    SUM("sum"),
    MIN("min"),
    MAX("max"),
    AVG("avg"),
    GROUP("group"),
    STDDEV("stddev"),
    STDVAR("stdvar"),
    COUNT_VALUES("count_values"),
    BOTTOMK("bottomk"),
    TOPK("topk"),
    QUANTILE("quantile")
    ;
}