package ru.yoomoney.tech.grafana.dsl.metrics.prometheus

import ru.yoomoney.tech.grafana.dsl.metrics.AnyMetric

/**
 * Prometheus metric displayed on dashboard panels
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
interface PrometheusMetric : AnyMetric

/**
 * Instant vector type - a set of time series containing a single sample for each time series,
 * all sharing the same timestamp
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/basics/#expression-language-data-types)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
interface InstantVectorTypedMetric : PrometheusMetric

/**
 * Range vector type - a set of time series containing a range of data points over time for each time series
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/basics/#expression-language-data-types)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
interface RangeVectorTypedMetric : PrometheusMetric

private class SimplePrometheusMetric(private val metric: String) : PrometheusMetric {
    override fun asString() = metric
}

private class SimpleInstantVectorTypedMetric(private val metric: String) : InstantVectorTypedMetric {
    override fun asString() = metric
}

private class SimpleRangeVectorTypedMetric(private val metric: String) : RangeVectorTypedMetric {
    override fun asString() = metric
}

/**
 * Convert string into [PrometheusMetric].
 */
fun String.asPrometheusMetric(): PrometheusMetric = SimplePrometheusMetric(this)

/**
 * Convert string into prometheus [InstantVectorTypedMetric]
 */
fun String.asInstantVector(): InstantVectorTypedMetric = SimpleInstantVectorTypedMetric(this)

/**
 * Create [InstantVectorTypedMetric]
 *
 * @param metricName metric name
 * @param aliases aliases
 */
fun instantVector(metricName: String, aliases: Map<String, String>): InstantVectorTypedMetric {
    val aliasesStr = aliases.entries.joinToString(", ") { """${it.key}="${it.value}"""" }
    return SimpleInstantVectorTypedMetric("$metricName{$aliasesStr}")
}

/**
 * Convert string into prometheus [RangeVectorTypedMetric]
 */
fun String.asRangeVector(): RangeVectorTypedMetric = SimpleRangeVectorTypedMetric(this)

/**
 * Create [RangeVectorTypedMetric]
 *
 * @param metricName metric name
 * @param aliases aliases
 * @param interval range interval
 */
fun rangeVector(metricName: String, aliases: Map<String, String>, interval: String): RangeVectorTypedMetric {
    val aliasesStr = aliases.entries.joinToString(", ") { """${it.key}="${it.value}"""" }
    return SimpleRangeVectorTypedMetric("$metricName{$aliasesStr}[$interval]")
}
