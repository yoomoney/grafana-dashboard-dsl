package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Position of an element on the dashboard (with it's size).
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Position(
    internal val x: Int,
    internal val y: Int,
    private val width: Int,
    private val height: Int
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "x" to x
        "y" to y
        "w" to width
        "h" to height
    }
}
