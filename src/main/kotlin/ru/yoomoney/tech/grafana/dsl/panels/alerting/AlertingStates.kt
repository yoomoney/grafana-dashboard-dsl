package ru.yoomoney.tech.grafana.dsl.panels.alerting

object Alerting : AlertingState {
    override fun asState() = "alerting"
}

object Ok : AlertingState {
    override fun asState() = "ok"
}

object KeepLastState : AlertingState {
    override fun asState() = "keep_state"
}

object NoData : AlertingState {
    override fun asState() = "no_data"
}
