package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

class AliasByNode(private val metric: Metric, private val nodes: IntArray) : Metric {
    override fun asString() = "aliasByNode(${metric.asString()}, ${nodes.joinToString(", ")})"
}

infix fun String.aliasByNode(node: Int) = AliasByNode(StringMetric(this), intArrayOf(node))

fun String.aliasByNode(vararg nodes: Int) = AliasByNode(StringMetric(this), nodes)

infix fun Metric.aliasByNode(node: Int) = AliasByNode(this, intArrayOf(node))

fun Metric.aliasByNode(vararg nodes: Int) = AliasByNode(this, nodes)
