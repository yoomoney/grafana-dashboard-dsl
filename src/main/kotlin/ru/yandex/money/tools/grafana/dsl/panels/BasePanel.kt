package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Base panel.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class BasePanel(private val id: Long, private val title: String, private val position: Position) : Panel {

    override fun toJson() = jsonObject {
        "id" to id
        "title" to title
        "gridPos" to position
    }
}

fun PanelContainerBuilder.panel(title: String, build: BasePanelBuilder.() -> Unit) {
    val builder = BasePanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
