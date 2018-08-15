package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.variables.Variable

class Summarize internal constructor(
    private val metric: Metric,
    private val interval: String,
    private val function: String = "sum"
) : Metric {
    override fun asString() = "summarize(${metric.asString()}, '$interval', '$function')"
}

fun String.summarize(interval: Duration, function: String) = Summarize(
        StringMetric(this),
        interval.toString(),
        function
)

infix fun String.summarize(interval: Duration) = Summarize(StringMetric(this), interval.toString())

fun String.summarize(interval: Variable, function: String) = Summarize(
        StringMetric(this),
        interval.asVariable(),
        function
)

infix fun String.summarize(interval: Variable) = Summarize(StringMetric(this), interval.asVariable())

fun Metric.summarize(interval: Duration, function: String) = Summarize(this, interval.toString(), function)

infix fun Metric.summarize(interval: Duration) = Summarize(this, interval.toString())

fun Metric.summarize(interval: Variable, function: String) = Summarize(this, interval.asVariable(), function)

infix fun Metric.summarize(interval: Variable) = Summarize(this, interval.asVariable())
