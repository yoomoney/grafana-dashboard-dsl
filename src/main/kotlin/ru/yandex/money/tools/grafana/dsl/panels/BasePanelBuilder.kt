package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject

class BasePanelBuilder(private val title: String) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
            BasePanel(id = idGenerator++, title = title, position = nextPosition(bounds.first, bounds.second)),
            propertiesSetter
    )
}
