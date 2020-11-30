package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json

/**
 * Represents "aliasColors" block on a panel
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
class AliasColors : Json<JSONObject> {

    private val aliasToColor: MutableMap<String, String> = mutableMapOf()

    operator fun set(key: String, value: Color) {
        aliasToColor[key] = value.asHexString()
    }

    override fun toJson() = JSONObject(aliasToColor)
}
