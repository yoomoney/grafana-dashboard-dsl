package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Генератор функции scale для graphite. scale принимает метрику и значение, на которое
 * необходимо умножить каждое значение в метрике.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class Scale(private val metric: Metric, private val scaleValue: Double) : Metric {
    override fun asString() = """scale(${metric.asString()}, $scaleValue)"""
}

infix fun Metric.scale(scaleValue: Double) = Scale(this, scaleValue)

infix fun String.scale(scaleValue: Double) = Scale(StringMetric(this), scaleValue)
