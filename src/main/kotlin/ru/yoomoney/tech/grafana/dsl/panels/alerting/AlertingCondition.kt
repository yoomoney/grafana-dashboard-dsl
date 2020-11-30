package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json

interface AlertingCondition : Json<JSONObject>
