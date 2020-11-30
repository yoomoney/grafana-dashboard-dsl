package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric
import ru.yoomoney.tech.grafana.dsl.time.Duration
import ru.yoomoney.tech.grafana.dsl.variables.Variable

/**
 * Generator for movingMedian function for graphite. movingMedian accepts metrics and
 * time interval and returns median values from specified time interval to current moment.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class MovingMedian(private val metric: Metric, private val duration: String) : Metric {
    override fun asString() = """movingMedian(${metric.asString()}, '$duration')"""
}

infix fun Metric.movingMedian(duration: Duration) = MovingMedian(this, duration.toString())

infix fun String.movingMedian(interval: Variable) = MovingMedian(StringMetric(this), interval.asVariable())

infix fun String.movingMedian(duration: Duration) = MovingMedian(StringMetric(this), duration.toString())
