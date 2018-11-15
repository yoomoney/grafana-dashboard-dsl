package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

class Scale(private val metric: Metric, private val scaleValue: Int) : Metric {
    override fun asString() = """scale(${metric.asString()}, $scaleValue)"""
}

infix fun Metric.scale(scaleValue: Int) = Scale(this, scaleValue)

infix fun String.scale(scaleValue: Int) = Scale(StringMetric(this), scaleValue)
