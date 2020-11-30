package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

class Exclude(private val metric: Metric, private val exclusion: String) : Metric {
    override fun asString() = "exclude(${metric.asString()}, '$exclusion')"
}

infix fun String.exclude(exclusion: String) = Exclude(StringMetric(this), exclusion)

infix fun Metric.exclude(exclusion: String) = Exclude(this, exclusion)
