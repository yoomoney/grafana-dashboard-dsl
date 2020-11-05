package ru.yandex.money.tools.grafana.dsl.panels.stat

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Options to refine visualization
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelDisplayOptions(
    private val colorMode: ColorMode = ColorMode.VALUE,
    private val orientation: Orientation = Orientation.HORIZONTAL,
    private val textMode: TextMode = TextMode.AUTO,
    private val graphMode: GraphMode = GraphMode.NONE,
    private val justifyMode: JustifyMode = JustifyMode.AUTO
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to StatPanelReduceOptions()
        "orientation" to orientation.value
        "textMode" to textMode.value
        "colorMode" to colorMode.value
        "graphMode" to graphMode.value
        "justifyMode" to justifyMode.value
    }
}

/**
 * Builder for StatPanelDisplayOptions
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelDisplayOptionsBuilder {
    var colorMode: ColorMode = ColorMode.VALUE

    fun createStatPanelDisplayOptions() = StatPanelDisplayOptions(colorMode)
}

/**
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelReduceOptions : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to false
        "calcs" to listOf("lastNotNull")
        "fields" to ""
    }
}
