package ru.yandex.money.tools.grafana.dsl.panels.graph.display.drawoptions

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Hover Tooltip menu on graph panel
 *
 * @author Aleksey Antufev
 * @since 04.09.2019
 */
class HoverTooltip(
    private val mode: Mode = Mode.ALL_SERIES,
    private val sortOrder: SortOrder = SortOrder.NONE,
    private val stackedValue: StackedValue = StackedValue.INDIVIDUAL
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "shared" to mode.id
        "sort" to sortOrder.id
        "value_type" to stackedValue.id
    }

    enum class Mode(val id: Boolean) {
        ALL_SERIES(true),
        SINGLE(false)
    }

    enum class SortOrder(val id: Int) {
        NONE(0),
        INCREASING(1),
        DECREASING(2)
    }

    enum class StackedValue(val id: String) {
        INDIVIDUAL("individual"),
        CUMULATIVE("cumulative")
    }
}
