package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Takes one [metric] followed by aggregation functions and list of nodes' numbers.
 *
 * Renders multiple series which are each the result of applying passed aggregation functions
 * to groups joined on the nodes’ list (zero indexed).
 *
 * @author Konstantin Novokreshchenov (knovokresch@yamoney.ru)
 * @since 20.03.2019
 */
class GroupByNodes internal constructor(
    private val metric: Metric,
    private val function: String = "sum",
    private val nodes: IntArray
) : Metric {

    init {
        require(nodes.isNotEmpty()) { "nodes must be not empty" }
    }

    override fun asString() = "groupByNodes(${metric.asString()}, '$function', ${nodes.joinToString(", ")})"
}

fun String.groupByNodes(function: String, vararg nodes: Int) = GroupByNodes(StringMetric(this), function, nodes)

fun String.groupByNodes(vararg nodes: Int) = GroupByNodes(StringMetric(this), nodes = nodes)

fun Metric.groupByNodes(function: String, vararg nodes: Int) = GroupByNodes(this, function, nodes)

fun Metric.groupByNodes(vararg nodes: Int) = GroupByNodes(this, nodes = nodes)
