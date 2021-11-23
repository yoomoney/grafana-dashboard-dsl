package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.set
import ru.yoomoney.tech.grafana.dsl.time.Duration
import ru.yoomoney.tech.grafana.dsl.time.m

class Alert(
    private val name: String,
    private val message: String,
    private val handler: Int,
    private val frequency: Duration = 1.m,
    private val onNoData: AlertingState = Ok,
    private val onExecutionError: AlertingState = Alerting,
    private val notificationUids: List<String> = emptyList(),
    private val notificationIds: List<Long> = emptyList(),
    private val conditions: AlertingConditions,
    private val pendingFor: Duration = 0.m
) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["name"] = name
        json["message"] = message
        json["handler"] = handler
        json["frequency"] = frequency.toString()
        json["noDataState"] = onNoData.asState()
        json["executionErrorState"] = onExecutionError.asState()
        json["notifications"] = JSONArray(
            notificationIds.map { JSONObject().put("id", it) } +
                    notificationUids.map { JSONObject().put("uid", it) }
        )
        json["conditions"] = conditions.toJson()
        json["for"] = pendingFor.toString()

        return json
    }
}
