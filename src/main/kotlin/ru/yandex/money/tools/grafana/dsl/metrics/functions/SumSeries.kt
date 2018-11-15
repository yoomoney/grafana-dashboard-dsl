package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Генератор функции sumSeries для graphite. sumSeries объединяет все метрики и возвращает сумму в
 * каждой точке.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class SumSeries(private val metric: Metric) : Metric {
    override fun asString() = """sumSeries(${metric.asString()})"""
}

fun String.sumSeries() = SumSeries(StringMetric(this))
