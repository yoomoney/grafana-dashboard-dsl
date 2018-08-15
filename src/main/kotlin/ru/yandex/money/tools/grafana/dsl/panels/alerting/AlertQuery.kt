package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.metrics.DashboardMetric

class AlertQuery(
    private val datasourceId: Long = 1,
    private val metric: DashboardMetric,
    private vararg val params: Any
) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["datasourceId"] = datasourceId
        json["model"] = metric.toJson()
        json["params"] = JSONArray(params.toList())

        return json
    }
}
