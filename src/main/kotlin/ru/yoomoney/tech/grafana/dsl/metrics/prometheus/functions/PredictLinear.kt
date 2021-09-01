package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.RangeVectorTypedMetric

/**
 * Function `predict_linear(v range-vector, t scalar)` predicts the value of time series `t` seconds from now,
 * based on the range vector `v`, using simple linear regression.
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/functions/#predict_linear)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 26.08.2021
 */
internal class PredictLinear internal constructor(
    private val metric: RangeVectorTypedMetric,
    private val secondsFromNow: Int
) : InstantVectorTypedMetric {
    override fun asString() = "predict_linear(${metric.asString()}, $secondsFromNow)"
}

/**
 * Predict the value of time series [secondsFromNow], based on [this] range vector, using simple linear regression
 */
fun RangeVectorTypedMetric.predictLinear(secondsFromNow: Int): InstantVectorTypedMetric = PredictLinear(this, secondsFromNow)