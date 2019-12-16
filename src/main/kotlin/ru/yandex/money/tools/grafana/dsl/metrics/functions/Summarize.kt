package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.variables.Variable

class Summarize internal constructor(
    private val metric: Metric,
    private val interval: String,
    private val function: String = "sum",
    private val alignTo: Boolean? = null
) : Metric {
    override fun asString() = listOfNotNull(
            "'$interval'",
            "'$function'",
            alignTo).joinToString(", ", "summarize(${metric.asString()}, ", ")")
}

infix fun String.summarize(interval: Duration) = Summarize(StringMetric(this), interval.toString())

infix fun String.summarize(interval: Variable) = Summarize(StringMetric(this), interval.asVariable())

infix fun Metric.summarize(interval: Duration) = Summarize(this, interval.toString())

infix fun Metric.summarize(interval: Variable) = Summarize(this, interval.asVariable())

fun String.summarize(interval: Duration, function: String, alignTo: Boolean? = null) = Summarize(
        StringMetric(this),
        interval.toString(),
        function,
        alignTo
)

fun String.summarize(interval: Variable, function: String, alignTo: Boolean? = null) = Summarize(
        StringMetric(this),
        interval.asVariable(),
        function,
        alignTo
)

fun Metric.summarize(interval: Duration, function: String, alignTo: Boolean? = null) = Summarize(
        this, interval.toString(), function, alignTo)

fun Metric.summarize(interval: Variable, function: String, alignTo: Boolean? = null) = Summarize(
        this, interval.asVariable(), function, alignTo)
