package ru.yandex.money.tools.grafana.dsl.panels.alerting

interface AlertingState {

    fun asState(): String
}
