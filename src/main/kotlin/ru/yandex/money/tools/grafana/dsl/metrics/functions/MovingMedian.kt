package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric
import ru.yandex.money.tools.grafana.dsl.time.Duration

/**
 * Генератор функции movingMedian для graphite. movingMedian принимает метрику и временной интервал
 * возвращает значения медианы за заданный интервал времени до текущего момента.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class MovingMedian(private val metric: Metric, private val duration: Duration) : Metric {
    override fun asString() = """movingMedian(${metric.asString()}, '$duration')"""
}

infix fun Metric.movingMedian(duration: Duration) = MovingMedian(this, duration)

infix fun String.movingMedian(duration: Duration) = MovingMedian(StringMetric(this), duration)
