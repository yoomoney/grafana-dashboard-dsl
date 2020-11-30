package ru.yoomoney.tech.grafana.dsl.metrics.functions

import ru.yoomoney.tech.grafana.dsl.metrics.Metric

/**
 * Generator for transformNull function for graphite. transformNull replaces all NULL values with specified constant.
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
class TransformNull(private val metric: Metric, private val transformValue: Double) : Metric {
    override fun asString() = """transformNull(${metric.asString()}, $transformValue)"""
}

infix fun Metric.transformNull(transformValue: Double) = TransformNull(this, transformValue)

infix fun String.transformNull(transformValue: Double) = TransformNull(StringMetric(this), transformValue)
