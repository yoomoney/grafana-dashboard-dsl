package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.set

/**
 * Generator for min alert condition.
 * @author vchurkin
 * @since 24.03.2022
 */
class MinCondition(private val condition: AlertingCondition) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = condition.toJson()

        json["reducer"] = AlertEvaluator("min").toJson()

        val operator = JSONObject()
        operator["type"] = "and"

        json["operator"] = operator

        return json
    }
}
