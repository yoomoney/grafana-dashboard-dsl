package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * SortByTotal function for Graphite.
 * Sorts the list of metrics in descending order by the sum of values across the time period specified.
 *
 * @author Aleksey Antufev
 * @since 05.09.2019
 */
class SortByTotal(private val metric: Metric) : Metric {
    override fun asString() = "sortByTotal(${metric.asString()})"
}

fun Metric.sortByTotal() = SortByTotal(this)

fun String.sortByTotal() = SortByTotal(StringMetric(this))
