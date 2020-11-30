package ru.yoomoney.tech.grafana.dsl.panels.alerting

object Alerting : AlertingState {
    override fun asState() = "alerting"
}

object Ok : AlertingState {
    override fun asState() = "ok"
}
