package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Описывает блок "aliasColors" на панели
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
class AliasColors : Json<JSONObject> {

    private val aliasToColor: MutableMap<String, String> = mutableMapOf()

    operator fun set(key: String, value: Color) {
        aliasToColor[key] = value.asString()
    }

    override fun toJson() = JSONObject(aliasToColor)
}
