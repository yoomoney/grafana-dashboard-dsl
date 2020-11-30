package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.set

class AlertEvaluator(private val type: String, private vararg val params: Any) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["type"] = type
        json["params"] = JSONArray(params.toList())

        return json
    }
}
