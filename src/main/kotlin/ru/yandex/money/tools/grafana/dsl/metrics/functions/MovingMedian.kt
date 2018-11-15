package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Генератор функции movingMedian для graphite. movingMedian принимает метрику и временной интервал
 * возвращает значения медианы за заданный интервал времени до текущего момента.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class MovingMedian(private val metric: Metric, private val minutes: Int) : Metric {
    override fun asString() = """movingMedian(${metric.asString()}, '${minutes}min')"""
}

infix fun Metric.movingMedian(minutes: Int) = MovingMedian(this, minutes)

infix fun String.movingMedian(minutes: Int) = MovingMedian(StringMetric(this), minutes)
