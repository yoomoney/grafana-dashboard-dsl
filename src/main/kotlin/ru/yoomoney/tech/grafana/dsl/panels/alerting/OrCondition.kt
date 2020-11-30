package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.set

class OrCondition(private val condition: AlertingCondition) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = condition.toJson()

        val operator = JSONObject()
        operator["type"] = "or"

        json["operator"] = operator

        return json
    }
}
