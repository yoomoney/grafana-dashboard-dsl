package ru.yandex.money.tools.grafana.dsl

class PanelsWithElements(private val elements: List<Panel>) : Panels {
    override fun add(panel: Panel) = PanelsWithElements(elements + panel)

    override fun toString() = elements.joinToString(",", "[", "]")
}
