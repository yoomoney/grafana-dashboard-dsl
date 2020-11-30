package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * KeepLastValue graphite function.
 *
 * Takes [seriesList], and a [limit] to the number of ‘None’ values to skip over.
 * Continues the line with the last received value when gaps (‘None’ values) appear in your data,
 * rather than breaking your line.
 *
 * **See Also:** [keepLastValue](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.keepLastValue)
 *
 * @author Ilya Doroshenko
 * @since 23.04.2020
 */
class KeepLastValue internal constructor(private val seriesList: Metric, private val limit: Int) : Metric {
    override fun asString() = "keepLastValue(${seriesList.asString()}, $limit)"
}

infix fun String.keepLastValue(limit: Int) = KeepLastValue(StringMetric(this), limit)

infix fun Metric.keepLastValue(limit: Int) = KeepLastValue(this, limit)
