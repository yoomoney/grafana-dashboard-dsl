package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * A variable [value] with [name] assigned to this [value].
 *
 * @author Dmitry Komarov
 * @since 20.03.2019
 */
class VariableValue(private val value: String, private val name: String = value) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "text" to name
        "value" to value
    }
}
