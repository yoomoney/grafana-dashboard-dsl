package ru.yandex.money.tools.grafana.dsl

interface Refresh

object RefreshOff : Refresh {
    override fun toString() = "off"
}

class PeriodicRefresh(private val refreshPeriod: GrafanaDuration) : Refresh {
    override fun toString() = refreshPeriod.toString()
}
