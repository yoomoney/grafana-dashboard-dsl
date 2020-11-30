package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * The Columns option allows you to select what columns you want in the table.
 *
 * @author Aleksandr Korkin
 * @since 16.12.2019
 */
class TableColumn(val value: String, private val name: String = value) : Json<JSONObject> {
    override fun toJson() = jsonObject {
        "text" to name
        "value" to value
    }
}
