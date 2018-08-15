package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

class AlertingConditions(conditions: Collection<AlertingCondition>) : Json<JSONArray> by JsonArray(conditions)
