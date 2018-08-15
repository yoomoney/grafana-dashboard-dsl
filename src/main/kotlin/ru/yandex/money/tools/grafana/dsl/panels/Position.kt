package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Позиция элемента на дэшборде (вместе с его линейными размерами).
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Position(
    private val x: Int,
    private val y: Int,
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
