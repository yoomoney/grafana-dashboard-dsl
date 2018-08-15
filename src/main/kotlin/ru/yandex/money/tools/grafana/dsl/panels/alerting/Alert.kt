package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.time.m

class Alert(
    private val name: String,
    private val message: String,
    private val handler: Int,
    private val frequency: Duration = 1.m,
    private val onNoData: AlertingState = Ok,
    private val onExecutionError: AlertingState = Alerting,
    private val notificationIds: List<Long> = emptyList(),
    private val conditions: AlertingConditions
) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["name"] = name
        json["message"] = message
        json["handler"] = handler
        json["frequency"] = frequency.toString()
        json["noDataState"] = onNoData.asState()
        json["executionErrorState"] = onExecutionError.asState()
        json["notifications"] = JSONArray(notificationIds.map { JSONObject().put("id", it) })
        json["conditions"] = conditions.toJson()

        return json
    }
}
