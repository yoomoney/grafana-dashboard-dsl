package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.set

class QueryCondition(
    private val evaluator: AlertEvaluator,
    private val query: AlertQuery,
    private val type: String = "query"
) : AlertingCondition {

    override fun toJson(): JSONObject {
        val json = JSONObject()

        json["evaluator"] = evaluator.toJson()
        json["type"] = type
        json["query"] = query.toJson()

        return json
    }
}
