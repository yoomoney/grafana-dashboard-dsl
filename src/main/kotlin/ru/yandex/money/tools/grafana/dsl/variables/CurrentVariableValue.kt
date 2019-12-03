package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * A variable [value] with [text] assigned to this [value].
 * The default value for the variable
 * @author Ivan Razgulin
 * @since 03.12.2019
 */
class CurrentVariableValue(val value: String, private val text: String = value) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "text" to text
        "value" to value
    }
}
