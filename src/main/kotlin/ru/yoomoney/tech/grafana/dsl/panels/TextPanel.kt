package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.json.emptyJsonArray
import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * The text panel lets you make information and description panels etc.
 * @author Aleksandr Korkin
 * @since 27.09.2019
 */
class TextPanel(
    private val basePanel: Panel,
    private val content: String,
    private val transparent: Boolean = false,
    private val mode: ContentMode = ContentMode.MARKDOWN

) : Panel {
    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "text"
        "links" to emptyJsonArray()
        "transparent" to transparent
        "content" to content
        "mode" to mode.value
    }
}
