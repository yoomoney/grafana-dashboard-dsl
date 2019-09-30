package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject

/**
 * Builder for a text panel.
 * @author Aleksandr Korkin
 * @since 27.09.2019
 */

class TextPanelBuilder(private val title: String) : PanelBuilder {

    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var mode: ContentMode = ContentMode.MARKDOWN

    var content: String = ""

    var transparent: Boolean = false

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        propertiesSetters += propertiesSetter
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
        TextPanel(
            BasePanel(
                id = idGenerator++,
                title = title,
                position = nextPosition(bounds.first, bounds.second)
            ),
            content = content,
            transparent = transparent,
            mode = mode
        )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.textPanel(title: String, build: TextPanelBuilder.() -> Unit) {
    val builder = TextPanelBuilder(title)
    builder.build()
    panels += builder.createPanel()
}
