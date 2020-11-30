package ru.yoomoney.tech.grafana.dsl.json

import org.json.JSONArray
import org.json.JSONObject

/**
 * Array of JSON objects.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class JsonArray(private val objects: Collection<Json<JSONObject>>) : Json<JSONArray> {
    override fun toJson() = JSONArray(objects.map { it.toJson() })
}
