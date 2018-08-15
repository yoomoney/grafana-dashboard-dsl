package ru.yandex.money.tools.grafana.dsl.json

import org.json.JSONArray
import org.json.JSONObject

/**
 * Массив JSON-объектов.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class JsonArray(private val objects: Collection<Json<JSONObject>>) : Json<JSONArray> {
    override fun toJson() = JSONArray(objects.map { it.toJson() })
}
