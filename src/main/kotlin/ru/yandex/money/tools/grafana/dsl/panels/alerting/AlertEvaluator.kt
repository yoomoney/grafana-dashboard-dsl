package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.set

class AlertEvaluator(private val type: String, private vararg val params: Any) : Json<JSONObject> {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["type"] = type
        json["params"] = JSONArray(params.toList())

        return json
    }
}
