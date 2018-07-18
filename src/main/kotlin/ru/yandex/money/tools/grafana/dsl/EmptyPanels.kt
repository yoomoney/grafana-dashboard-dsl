package ru.yandex.money.tools.grafana.dsl

class EmptyPanels : Panels {
    override fun add(panel: Panel) = PanelsWithElements(listOf(panel))

    override fun toString() = "[]"
}
