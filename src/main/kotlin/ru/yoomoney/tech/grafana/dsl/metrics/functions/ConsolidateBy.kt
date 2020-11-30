package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * Takes one metric or a wildcard seriesList and a consolidation function name.
 * All Graphite metrics are consolidated so that Graphite doesn’t return more data points
 * than there are pixels in the graph.
 * By default, this consolidation is done using avg function.
 * You can control how Graphite consolidates metrics by adding the Graphite consolidateBy function.
 *
 * @author Aleksandr Korkin
 * @since 24.09.2019
 */
class ConsolidateBy internal constructor(
    private val metric: Metric,
    private val function: ConsolidationFunction = ConsolidationFunction.MAX
) : Metric {
    override fun asString() = "consolidateBy(${metric.asString()}, '${function.value}')"
}

infix fun Metric.consolidateBy(function: ConsolidationFunction) = ConsolidateBy(this, function)

infix fun String.consolidateBy(function: ConsolidationFunction) = ConsolidateBy(StringMetric(this), function)
