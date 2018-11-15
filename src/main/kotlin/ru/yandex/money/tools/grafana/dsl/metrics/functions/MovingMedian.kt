package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

class MovingMedian(private val metric: Metric, private val minutes: Int) : Metric {
    override fun asString() = """movingMedian(${metric.asString()}, '${minutes}min')"""
}

infix fun Metric.movingMedian(minutes: Int) = MovingMedian(this, minutes)

infix fun String.movingMedian(minutes: Int) = MovingMedian(StringMetric(this), minutes)
