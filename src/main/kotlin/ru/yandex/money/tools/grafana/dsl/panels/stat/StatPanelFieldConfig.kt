package ru.yandex.money.tools.grafana.dsl.panels.stat

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.panels.NullValue

/**
 * Used to change how the data is displayed in visualizations
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelFieldConfig(private val thresholds: Thresholds = Thresholds(),
                           private val mappings: List<Mapping> = emptyList(),
                           private val nullValueMode: NullValue = NullValue.NULL
) : Json<JSONObject> {
    override fun toJson(): JSONObject = jsonObject {
        "defaults" to jsonObject {
            "unit" to "none"
            "thresholds" to thresholds
            "mappings" to JsonArray(mappings)
            "nullValueMode" to nullValueMode.value
        }
    }
}

/**
 * Builder for StatPanelFieldConfiguration
 * @author Aleksey Matveev
 * @since 02.10.2020
 */
class StatPanelFieldConfigBuilder(private val nullValueMode: NullValue = NullValue.NULL) {
    var thresholds: Thresholds = Thresholds()
    var mappings: List<Mapping> = emptyList()

    internal fun createStatPanelFieldConfig(): StatPanelFieldConfig = StatPanelFieldConfig(thresholds, mappings, nullValueMode)

    fun thresholds(mode: ThresholdMode = ThresholdMode.ABSOLUTE, build: ThresholdsBuilder.() -> Unit) {
        val builder = ThresholdsBuilder(mode)
        builder.build()
        thresholds = builder.createThresholds()
    }

    fun mappings(build: MappingsBuilder.() -> Unit) {
        val builder = MappingsBuilder()
        builder.build()
        mappings = builder.mappings
    }
}