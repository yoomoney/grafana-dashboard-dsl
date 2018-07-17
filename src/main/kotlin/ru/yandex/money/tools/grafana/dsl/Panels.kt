package ru.yandex.money.tools.grafana.dsl

interface Panels {

    fun add(panel: Panel): Panels

    operator fun plus(panel: Panel) = add(panel)
}