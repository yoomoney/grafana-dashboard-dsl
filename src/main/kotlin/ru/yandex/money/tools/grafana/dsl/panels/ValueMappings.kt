package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

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
