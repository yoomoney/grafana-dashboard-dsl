package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

class Thresholds(thresholds: Collection<Threshold>) : Json<JSONArray> by JsonArray(thresholds)
