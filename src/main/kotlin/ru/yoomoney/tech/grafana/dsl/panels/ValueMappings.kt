package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.JsonArray
import ru.yoomoney.tech.grafana.dsl.json.jsonArray
import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * Value mappings tab on panel sets type and mappings that shows in panel
 * @author Aleksey Antufev
 * @since 24.09.2019
 * */
class ValueMappings(
    private val valueMappingType: ValueMappingType,
    private val valueMappings: List<ValueMapping> = emptyList()
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "mappingType" to valueMappingType.id
        when (valueMappingType) {
            is ValueToTextType -> {
                "valueMaps" to JsonArray(valueMappings)
            }
            is RangeToTextType -> {
                "rangeMaps" to JsonArray(valueMappings)
            }
        }
        "mappingTypes" to jsonArray[valueMappingType]
    }
}
