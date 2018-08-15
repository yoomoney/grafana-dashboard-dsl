package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

class AliasByNode(private val metric: Metric, private val node: Int = 0) : Metric {
    override fun asString() = "aliasByNode(${metric.asString()}, $node)"
}

infix fun String.aliasByNode(node: Int) = AliasByNode(StringMetric(this), node)

fun String.aliasByNode() = AliasByNode(StringMetric(this))

infix fun Metric.aliasByNode(node: Int) = AliasByNode(this, node)

fun Metric.aliasByNode() = AliasByNode(this)
