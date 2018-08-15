package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.set

class OrCondition(private val condition: AlertingCondition) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = condition.toJson()

        val operator = JSONObject()
        operator["type"] = "or"

        json["operator"] = operator

        return json
    }
}
