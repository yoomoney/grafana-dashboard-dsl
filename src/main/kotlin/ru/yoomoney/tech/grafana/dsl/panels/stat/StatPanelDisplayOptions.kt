package ru.yoomoney.tech.grafana.dsl.panels.stat

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.panels.Orientation

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
    private val justifyMode: JustifyMode = JustifyMode.AUTO,
    private val reduceOptions: StatPanelReduceOptions = StatPanelReduceOptions()
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "reduceOptions" to reduceOptions
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
    private var reduceOptions: StatPanelReduceOptions = StatPanelReduceOptions()
    fun reduceOptions(build: StatPanelReduceOptionsBuilder.() -> Unit) {
        val builder = StatPanelReduceOptionsBuilder()
        builder.build()
        reduceOptions = builder.createReduceOptions()
    }

    fun createStatPanelDisplayOptions() = StatPanelDisplayOptions(colorMode, reduceOptions = reduceOptions)
}

/**
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelReduceOptions(
    private val fields: String = ""
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "values" to false
        "calcs" to listOf("lastNotNull")
        "fields" to fields
    }
}

class StatPanelReduceOptionsBuilder() {
    var fields: String = ""
    fun createReduceOptions(): StatPanelReduceOptions {
        return StatPanelReduceOptions(fields = fields)
    }
}

