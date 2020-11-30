package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONArray
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.JsonArray

class AlertingConditions(conditions: Collection<AlertingCondition>) : Json<JSONArray> by JsonArray(conditions)
