package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * Takes an arbitrary number of seriesLists and adds them to a single seriesList.
 * This is used to pass multiple seriesLists to a function which only takes one*
 * [group](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.group)
 *
 * @author Aleksandr Korkin (avkorkin@yamoney.ru)
 * @since 12.12.2019
 */
class Group internal constructor(private vararg var seriesList: Metric) : Metric {
    override fun asString() = "group(${seriesList.joinToString(",") { it.asString() }})"
}

fun String.group(vararg metrics: Metric) = Group(StringMetric(this), *metrics)

fun Metric.group(vararg metrics: Metric) = Group(this, *metrics)

fun Metric.group(vararg metrics: String) = Group(this, *metrics.map { i -> StringMetric(i) }.toTypedArray())
