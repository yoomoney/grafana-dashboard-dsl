package ru.yandex.money.tools.grafana.dsl

class DashboardBuilder {

    var style = Style.DARK
    var timezone = GrafanaTimezone.BROWSER
    lateinit var title: String
    lateinit var time: GrafanaTimeRange
    var refresh: Refresh = RefreshOff

    fun panels(build: PanelsBuilder.() -> Unit) {

    }
}