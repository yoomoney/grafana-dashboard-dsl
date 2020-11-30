package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator

/**
 * Builder for a text panel.
 * @author Aleksandr Korkin
 * @since 27.09.2019
 */
class TextPanelBuilder(private val title: String, private val generator: PanelLayoutGenerator) : PanelBuilder {

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
                id = generator.nextId(),
                title = title,
                position = generator.nextPosition(bounds.first, bounds.second)
            ),
            content = content,
            transparent = transparent,
            mode = mode
        )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.textPanel(title: String = "", build: TextPanelBuilder.() -> Unit) {
    val builder = TextPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
