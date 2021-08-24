package ru.yoomoney.tech.grafana.dsl.metrics

import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * Metric for PromQL datasource that integrates for panels.
 *
 * @author lokshin (lokshin@yamoney.ru)
 * @since 08.08.2021
 */
class PromQlMetric (
    val metric: Metric,
    val format: String?,
    val instant: Boolean
) : DashboardMetric {

    override fun toJson() = jsonObject {
        "expr" to metric.asString()
        "format" to "time_series"
        "legendFormat" to format
        "instant" to instant
    }

}
