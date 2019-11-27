package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.dashboard.Tags
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Feature to group the values into selectable tags
 * @author Aleksandr Korkin
 */
class VariableTags(
    private val tagsQuery: String,
    private val tagValuesQuery: String,
    private val tags: Tags? = null
) : Json<JSONObject> {
    override fun toJson() = jsonObject {
        "tagsQuery" to tagsQuery
        "tagValuesQuery" to tagValuesQuery
        "useTags" to true
    }
}
