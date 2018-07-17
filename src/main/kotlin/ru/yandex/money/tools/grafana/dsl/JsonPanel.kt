package ru.yandex.money.tools.grafana.dsl

class JsonPanel(private val json: String) : Panel {
    override fun toString() = json
}

fun PanelsBuilder.jsonPanel(json: () -> String) {
    this.panels = this.panels + JsonPanel(json().trimIndent())
}
