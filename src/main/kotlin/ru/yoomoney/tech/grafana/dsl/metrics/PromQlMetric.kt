package ru.yoomoney.tech.grafana.dsl.metrics

import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.PrometheusMetric

/**
 * Metric for PromQL datasource that integrates for panels.
 *
 * @author lokshin (lokshin@yamoney.ru)
 * @since 08.08.2021
 */
class PromQlMetric (
    val metric: PrometheusMetric,
    val legendFormat: String?,
    val instant: Boolean,
    override val id: String? = null,
    val hidden: Boolean = false
) : DashboardMetric {

    override fun toJson() = jsonObject {
        "refId" to id
        "hide" to hidden
        "expr" to metric.asString()
        "format" to "time_series"
        "legendFormat" to legendFormat
        "instant" to instant
    }

}
