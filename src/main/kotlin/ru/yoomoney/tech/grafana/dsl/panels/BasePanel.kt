package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * Base panel.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class BasePanel(
    private val id: Long,
    private val title: String,
    private val position: Position,
    private val description: String? = null
) : Panel {
    override fun toJson() = jsonObject {
        "id" to id
        "title" to title
        "gridPos" to position
        "description" to description
    }
}

fun PanelContainerBuilder.panel(title: String, build: BasePanelBuilder.() -> Unit) {
    val builder = BasePanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
