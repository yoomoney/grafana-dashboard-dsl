package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject

class AdditionalPropertiesPanel(
    private val panel: Panel,
    private val additionalPropertiesSetter: (JSONObject) -> Unit
) : Panel {

    override fun toJson(): JSONObject {
        val json = panel.toJson()
        additionalPropertiesSetter(json)
        return json
    }
}
