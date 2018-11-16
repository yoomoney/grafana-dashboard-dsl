package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Генератор функции alias для graphite. alias устанавливает имя для метрики,
 * которое отображается под графиком.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 16.11.2018
 */
class Alias(private val metric: Metric, private val aliasName: String) : Metric {
    override fun asString() = "alias(${metric.asString()}, '$aliasName')"
}

infix fun Metric.alias(aliasName: String) = Alias(this, aliasName)

infix fun String.alias(aliasName: String) = Alias(StringMetric(this), aliasName)
