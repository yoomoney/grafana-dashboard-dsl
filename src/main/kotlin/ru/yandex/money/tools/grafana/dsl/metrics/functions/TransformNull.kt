package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Генератор функции transformNull для graphite. transformNull заменяет все NULL значения на
 * заданную костанту
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class TransformNull(private val metric: Metric, private val transformValue: Int) : Metric {
    override fun asString() = """transformNull(${metric.asString()}, $transformValue)"""
}

infix fun Metric.transformNull(transformValue: Int) = TransformNull(this, transformValue)

infix fun String.transformNull(transformValue: Int) = TransformNull(StringMetric(this), transformValue)
