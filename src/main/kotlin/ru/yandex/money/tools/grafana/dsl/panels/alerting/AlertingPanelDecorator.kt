package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.set

class AlertingPanelDecorator(
    private val alert: Alert,
    private val thresholds: Thresholds
) : (JSONObject) -> Unit {

    override fun invoke(json: JSONObject) {
        json["alert"] = alert.toJson()
        json["thresholds"] = thresholds.toJson()
    }
}
