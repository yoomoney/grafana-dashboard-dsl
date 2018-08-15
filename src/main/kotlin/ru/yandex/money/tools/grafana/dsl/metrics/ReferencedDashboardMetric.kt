package ru.yandex.money.tools.grafana.dsl.metrics

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

class ReferencedDashboardMetric(
    private val metric: Metric,
    val id: String,
    private val hidden: Boolean
) : DashboardMetric {

    override fun toJson() = jsonObject {
        "target" to metric.asString()
        "refId" to id
        "hide" to hidden
    }
}
