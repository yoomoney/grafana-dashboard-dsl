package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

interface AlertingCondition : Json<JSONObject>
