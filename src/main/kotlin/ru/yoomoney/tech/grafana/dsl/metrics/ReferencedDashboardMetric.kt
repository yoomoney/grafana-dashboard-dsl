package ru.yoomoney.tech.grafana.dsl.metrics

import ru.yoomoney.tech.grafana.dsl.json.jsonObject

class ReferencedDashboardMetric(
    private val metric: Metric,
    override val id: String,
    private val hidden: Boolean
) : DashboardMetric {

    override fun toJson() = jsonObject {
        "target" to metric.asString()
        "refId" to id
        "hide" to hidden
    }
}
