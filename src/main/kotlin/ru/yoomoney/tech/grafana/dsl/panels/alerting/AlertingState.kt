package ru.yoomoney.tech.grafana.dsl.panels.alerting

interface AlertingState {

    fun asState(): String
}
