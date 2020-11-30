package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * AsPercent function for Graphite. Represents the ratio of one metric to another as a percentage.
 *
 * @author Aleksey Antufev
 * @since 05.09.2019
 */
class AsPercent(
    /**
     * Metric that is used as the numerator.
     */
    private val percentageMetric: Metric,
    /**
     * Metric that is used as the denominator.
     */
    private val baseMetric: Metric
) : Metric {
    override fun asString(): String {
        return "asPercent(${percentageMetric.asString()}, ${baseMetric.asString()})"
    }
}

infix fun Metric.asPercent(metric: Metric): Metric = AsPercent(this, metric)
