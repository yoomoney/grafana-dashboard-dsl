package ru.yandex.money.tools.grafana.dsl.metrics.functions

import ru.yandex.money.tools.grafana.dsl.metrics.Metric

/**
 * NonNegativeDerivative graphite function. Same as the derivative function, but ignores datapoints that trend down.
 *
 * [nonNegativeDerivative](https://graphite.readthedocs.io/en/latest/functions.html#graphite.render.functions.nonNegativeDerivative)
 *
 * @author Alexander Esipov (asesipov@yamoney.ru)
 * @since 02.12.2019
 */
class NonNegativeDerivative internal constructor(private val seriesList: Metric) : Metric {
    override fun asString() = "nonNegativeDerivative(${seriesList.asString()})"
}

fun String.nonNegativeDerivative() = NonNegativeDerivative(StringMetric(this))

fun Metric.nonNegativeDerivative() = NonNegativeDerivative(this)
