package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.DashboardElement

@DashboardElement
interface PanelBuilder {

    var bounds: Pair<Int, Int>

    fun properties(propertiesSetter: (JSONObject) -> Unit)

    companion object {
        val DEFAULT_BOUNDS = 24 to 12
    }
}
