package ru.yandex.money.tools.grafana.dsl

fun main(args: Array<String>) {
    dashboard {
        title = "SomeComponent common info"
        time = now - 6.h..now
        refresh = PeriodicRefresh(5.s)

        panels {
        }
    }
}
