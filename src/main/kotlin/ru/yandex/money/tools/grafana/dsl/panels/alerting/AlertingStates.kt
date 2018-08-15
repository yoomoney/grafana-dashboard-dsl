package ru.yandex.money.tools.grafana.dsl.panels.alerting

object Alerting : AlertingState {
    override fun asState() = "alerting"
}

object Ok : AlertingState {
    override fun asState() = "ok"
}
