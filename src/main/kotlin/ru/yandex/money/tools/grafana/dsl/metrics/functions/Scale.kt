package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Generator for scale function for graphite. scale accepts metric
 * and value, and scales metrics multiplying it values to scale.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class Scale(private val metric: Metric, private val scaleValue: Double) : Metric {
    override fun asString() = """scale(${metric.asString()}, $scaleValue)"""
}

infix fun Metric.scale(scaleValue: Double) = Scale(this, scaleValue)

infix fun String.scale(scaleValue: Double) = Scale(StringMetric(this), scaleValue)
