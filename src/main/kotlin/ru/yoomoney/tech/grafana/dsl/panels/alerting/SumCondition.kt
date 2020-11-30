package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.set

class SumCondition(private val condition: AlertingCondition) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = condition.toJson()

        json["reducer"] = AlertEvaluator("sum").toJson()

        val operator = JSONObject()
        operator["type"] = "and"

        json["operator"] = operator

        return json
    }
}
