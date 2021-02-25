package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric
import ru.yoomoney.tech.grafana.dsl.metrics.functions.StringMetric
import ru.yoomoney.tech.grafana.dsl.time.Duration
import ru.yoomoney.tech.grafana.dsl.variables.Variable

/**
 * Generator for movingMax function for graphite. movingMax accepts metrics and
 * time interval and returns max values from specified time interval to current moment.
 * @author Aleksey Matveev
 * @since 23.11.2020
 */
class MovingMax internal constructor(private val metric: Metric, private val duration: String) : Metric {
    override fun asString(): String = """movingMax(${metric.asString()}, '$duration')"""
}

infix fun Metric.movingMax(duration: Duration) = MovingMax(this, duration.toString())

infix fun String.movingMax(interval: Variable) = MovingMax(StringMetric(this), interval.asVariable())

infix fun String.movingMax(duration: Duration) = MovingMax(StringMetric(this), duration.toString())
